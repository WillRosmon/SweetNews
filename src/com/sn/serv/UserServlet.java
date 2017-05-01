package com.sn.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sn.api.util.ArticleRequest;
import com.sn.api.util.SourceRequest;
import com.sn.create.bean.RetrieveArticlesBean;
import com.sn.create.bean.RetrieveSourcesBean;
import com.sn.database.accessors.CategoryAccessor;
import com.sn.database.accessors.SourceAccessor;
import com.sn.database.accessors.UserAccessor;
import com.sn.database.objects.*;
import com.sn.database.utilities.ConnectionPool;
import com.sn.user.bean.UserBean;

/**
 * Servlet implementation class UserServlet
 */
//@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered User Servlet");
		
		HttpSession session = request.getSession();
		String url = "/index.jsp";
		String action = request.getParameter("action");
		
		//Proper way to instantiate?
		User user = new User();
		User index = new User();
		
		List<Article> articles =  new ArrayList<Article>();
		
		String [] general = {"general","politics","sport","entertainment","science-and-nature"};
		
		index.setInterests(new ArrayList<String>(Arrays.asList(general)));
		
		System.out.println(action);
		
		RetrieveSourcesBean retrivesource = new RetrieveSourcesBean();
		
		//retrivesource.initialiseSources();  //run this code the first time you are running the server, 
		                                     //check the db for table source, if the sources are added, then comment it later
		
		
		
		RetrieveArticlesBean retrievearticle = new RetrieveArticlesBean();
		
		//retrievearticle.initaliseArticles();   //run this code the after the sources are loaded, 
        									     //check the db for table article, if the articles are added, then comment it later
		 
		
		
		
		
		if(action == null){
			action = "join";
		}
		if(action.equals("join")){
			session.setAttribute("index", index);
			articles.addAll(retrievearticle.retrieveArticlesByTopic(index.getInterests()));
			session.setAttribute("indexlist", articles);
			url = "/index.jsp";
		}
	
		
		//Handle Login Form..
		if(action.equals("login")){
			String msg = "";
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			user.setEmail(email);
			user.setPassword(password);
			
			List<Article> articleList =  new ArrayList<Article>();
			List<Source> pendingSources =  new ArrayList<Source>();
			
			UserBean userbean = new UserBean();
			
			if(userbean.login(user))
			{
				System.out.println("valid credentials");
				String check = userbean.checkUserType(user);
				if(check.equals("admin"))
				{
					System.out.println("admin");
					user=userbean.getPreference(user);
					System.out.println(user.getInterests());
					session.setAttribute("theUser", user);
					articleList.addAll(retrievearticle.retrieveArticlesByTopic(user.getInterests()));
					session.setAttribute("articles", articleList);
					pendingSources.addAll(retrivesource.selectSourceForApproval(user.getEmail()));
					session.setAttribute("pendingSources", pendingSources);
		            url="/admin.jsp";
				}
				else
				{
					System.out.println("user");
					user=userbean.getPreference(user);
					System.out.println(user.getInterests());
					session.setAttribute("theUser", user);
					articleList.addAll(retrievearticle.retrieveArticlesByTopic(user.getInterests()));
					session.setAttribute("articles", articleList);
		            url="/main.jsp";
				}
				
			}
			else
			{
				System.out.println("Invalid Credentials");
				msg = "Invalid Credentials";
				request.setAttribute("msg", msg);
				url = "/index.jsp";
			}
			
		}
		
		
		
		//Handle Sign Up Form...
		if(action.equals("create")){
			
			String msg= "Sorry, the email you entered already exists. <br/>" + "Please enter another email address.";
			
			String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            UserBean userbean = new UserBean();
            try {
				User testuser = userbean.getUser(email);
            	if (testuser != null)
				{
					request.setAttribute("msg", msg);
					url = "/signup.jsp";
				}
				
				else
				{
				user.setFirstName(name); 
				user.setEmail(email);
				user.setPassword(password);
				
				userbean.addUser(user);
				
				session.setAttribute("theUser", user);
				url="/userpreference.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
          }
		
        
		
		
		if(action.equals("preference")){
			
		   String[] preference = request.getParameterValues("preference");
	       user=(User) session.getAttribute("theUser");
	       List<Article> articleList =  new ArrayList<Article>();

	       
	       UserBean userbean =new UserBean();
	 
	       userbean.addPreference(user, preference);
	       
	       user.setInterests(new ArrayList<String>(Arrays.asList(preference)));
	       session.setAttribute("theUser", user);
			
	       articleList.addAll(retrievearticle.retrieveArticlesByTopic(user.getInterests()));
		   session.setAttribute("articles", articleList);
	       url="/main.jsp";
			
		}
		
		//TODO: Handle displaying sources/articles based on user preferences
		//ONLY 3 FOR EACH PREFERENCE!
			
			
		//Handle source addition
		if(action.equals("add")){
			String sourcename = request.getParameter("source");
			String sourceUrl = request.getParameter("url");
			String sourceCategory = request.getParameter("category");
			
			User use = (User)session.getAttribute("theUser");
			
			Source source = new Source();
			source.setId(sourcename);
			source.setName(sourcename);
			source.setCategory(sourceCategory);
			source.setUrl(sourceUrl);
			source.setUserId(use.getEmail());
			
			retrivesource.addNewSource(source);
			
			url="/admin.jsp";
			
			
		}
		
		//Handle admin approval
		if(action.equals("approve")){
			String sourceid = (String) request.getParameter("sourcename");
			System.out.println(sourceid);
			retrivesource.approveStatus(sourceid);
			url = "/admin.jsp";		
		}
		if(action.equals("disapprove")){
			
			String sourceid = (String) request.getParameter("sourcename");
			System.out.println(sourceid);
			retrivesource.disapproveStatus(sourceid);
			url = "/admin.jsp";
		}
		
		else if(action.equals("logout")){
			url = "/index.jsp";
            if(user != null){
                session.invalidate();
                request.logout();
                url = "/index.jsp";
            }
		}
		
		System.out.println(url);
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request,response);  
		
		
	}
	
	
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException{
        
        doPost(request,response);
    }

}
