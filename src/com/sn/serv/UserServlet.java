package com.sn.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		System.out.println(action);
		
		RetrieveSourcesBean retrivesource = new RetrieveSourcesBean();
		
		//retrivesource.initialiseSources();  run this code the first time you are running the server, 
		                                     //check the db for table source, if the sources are added, then comment it later
		
		
		
		RetrieveArticlesBean retrievearticle = new RetrieveArticlesBean();
		
		//retrievearticle.initaliseArticles();   run this code the after the sources are loaded, 
        									     //check the db for table article, if the articles are added, then comment it later
		 
		
		
		
		
		if(action == null){
			action = "join";
		}
		if(action.equals("join")){
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
			
			UserBean userbean = new UserBean();
			
			if(userbean.login(user))
			{
				System.out.println("valid credentials");
				if(userbean.checkUserType(user).equals("admin"))
				{
					System.out.println("admin");
					session.setAttribute("theAdmin", user);
		            url="/admin.jsp";
				}
				else
				{
					System.out.println("user");
					user=userbean.getPreference(user);
					System.out.println(user.getInterests());
					session.setAttribute("theUser", user);
					articleList.addAll(retrievearticle.retrieveArticlesByTopic(user.getInterests()));
					System.out.println(articleList.get(1).getDescription());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
		
        
		
		
		if(action.equals("preference")){
			
		   String[] preference = request.getParameterValues("preference");
	       user=(User) session.getAttribute("theUser");
		   
	       UserBean userbean =new UserBean();
	       userbean.addPreference(user, preference);
	       
	       url="/main.jsp";
			
		}
		
		//TODO: Handle displaying sources/articles based on user preferences
		//ONLY 3 FOR EACH PREFERENCE!
		
		
		
			RetrieveSourcesBean rsb = new RetrieveSourcesBean();
			//TODO: Get user and loop through his preferences.
			/*
			 * for(UserBean user : user){
			 * 		User user = user.getPreference(user);
			 */
//			List<Source> userSources = rsb.getSourcesByCategory("");
//			int beginIndex = 0;
//			if(article.isEmpty()){
//				beginIndex = -1;
//			}else{
//				beginIndex = article.size() - 4;
//			}
//			
//			if(beginIndex >= 0){
//				List<Article> returnArticles = new ArrayList<Article>();
//				for(int i = beginIndex; i < article.size(); i++){
//					returnArticles.add(article.get(i));
//				}
//				session.setAttribute("returnArticles", returnArticles);
//			}else{
//				throw new RuntimeException("No Articles Found!");
//			}
		
		
			
			
		//Handle source addition
		if(action.equals("add")){
			String source = request.getParameter("source");
			String articleUrl = request.getParameter("URL");
			String articleCategory = request.getParameter("Category");
			Article newArticle = new Article();
			newArticle.setUrl(articleUrl);
			newArticle.setSource(source);
			
		}
		
		//Handle admin approval
		if(action.equals("approve")){
			//TODO: Set the submitted source to approved in DB
			//The source that is added does not need to be shown on the site anywhere
		}
		if(action.equals("disapprove")){
			//TODO: Set the submitted source to disapproved in DB
			//The source that is added does not need to be shown on the site anywhere
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
