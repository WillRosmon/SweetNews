package com.sn.serv;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sn.create.bean.RetrieveArticlesBean;
import com.sn.database.accessors.CategoryAccessor;
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
		
		
		//Populate index.jsp with sample articles
		RetrieveArticlesBean rab = new RetrieveArticlesBean();
		List<Article> article = rab.retrieveArticlesByTopic("ap");
		int beginIndex = 0;
		if(article.isEmpty()){
			beginIndex = -1;
		}else{
			beginIndex = article.size() - 4;
		}
		
		if(beginIndex >= 0){
			List<Article> returnArticles = new ArrayList<Article>();
			for(int i = beginIndex; i < article.size(); i++){
				returnArticles.add(article.get(i));
			}
			session.setAttribute("returnArticles", returnArticles);
		}else{
			throw new RuntimeException("No Articles Found!");
		}

		
		
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
		            url="/main.jsp";
				}
				
			}
			else
			{
				System.out.println("Invalid Credentials");
				msg = "Invalid Credentials";
				session.setAttribute("msg", msg);
				url = "/index.jsp";
			}
			
		}
		
		
		
		//Handle Sign Up Form...
		if(action.equals("create")){
			
			String msg= "Sorry, the email you entered already exists. <br/>" + "Please enter another email address.";
			
			String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            user.setFirstName(name); 
            user.setEmail(email);
            user.setPassword(password);
            
            UserBean userbean = new UserBean();
            userbean.addUser(user);
            
            session.setAttribute("theUser", user);
            
            url="/userpreference.jsp";
          }
		
        
		
		
		if(action.equals("preference")){
			
		   String[] preference = request.getParameterValues("preference");
	       user=(User) session.getAttribute("theUser");
		   
	       UserBean userbean =new UserBean();
	       userbean.addPreference(user, preference);
	       
	       url="/main.jsp";
			
		}
		
		
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
			String userLogged = (String) session.getAttribute("theUser");
            if(userLogged != null){
                session.invalidate();
                request.logout();
                url = "/home.jsp";
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
