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
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		UserAccessor userAccessor = new UserAccessor(connection);
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
			//TODO: Check if email exists
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			user = userAccessor.getUser(email);
			UserBean userbean = new UserBean();
			
			if (user == null) {
			//TODO: If it does not, display this message.
			msg = "Sorry but this user does not exist. <br/>" + 
                    "Please try another email address.";
			session.setAttribute("msg", msg);
			url = "/index.jsp";
			}
			else if (!password.equals(user.getPassword())){
				msg = "Invalid Credentials";
				session.setAttribute("msg", msg);
				url = "/index.jsp";
			}
			//TODO: If it does exist, set the role of the user and use the session attributes below
			//session.setAttribute("theUser",user);
			//session.setAttribute("theAdmin",admin);
			else if (!userbean.checkUserType(user).equals("admin")){
                
                //set as session attribute
                session.setAttribute("theUser", user);
                //forward to main.jsp
                url="/main.jsp";
            }
            
            //Code to check if User Type is Admin
            else if (userbean.checkUserType(user).equals("admin")){
                
                //set as session attribute
                session.setAttribute("theAdmin", user);
                //forward to admin.jsp
                url="/admin.jsp";
            }
					
			//request.setAttribute("msg", msg);
		}
		
		//Handle Sign Up Form...
		if(action.equals("create")){
			String msg="";
			//TODO: Get parameters from text field and set them to the user object
			//Do the same check for email address as above and manually set the role to admin or user
			msg = "Sorry, the email you entered already exists. <br/>" +
                    "Please enter another email address.";
			String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirmpassword");
            System.out.println("Retrieved Params");
            //ArrayList<User> users = userAccessor.getUsers();
            
            user.setFirstName(name); 
            user.setEmail(email);
            user.setPassword(password);
            UserBean userbean = new UserBean();
            userbean.addUser(user);
           /* 
            for (User singleUser : users) {
            if(user == null){
            	
            }*/
                 /* if(email.equals(singleUser.getEmail())){
                	System.out.println("E-mail in DB");
                	session.setAttribute("msg", msg);
                	url = "/signup.jsp";
                }
            }
            if (!password.equals(confirm_password)){
            	System.out.println(password);
            	System.out.println(confirm_password);
            	msg = "Password Mismatch occured.";
				session.setAttribute("msg", msg);
				System.out.println("Password Mismatch");
				url = "/signup.jsp";
            }
            else{*/
           
            
                
                
                //set as session attribute
                session.setAttribute("theUser", user);
                //forward url
                url="/userpreference.jsp";
            }
		
        
		
		
		if(action.equals("preference")){
			//TODO: Get selected preferences from the radio buttons and store them to the user object
		CategoryAccessor categoryAccess=new CategoryAccessor(connection);
		String[] preference = request.getParameterValues("preference");
		
/*		List<String> categoryList=new ArrayList<String>();
		categoryList.add("business");
		categoryList.add("sports");
		categoryList.add("technology");
		categoryList.add("travel");
		categoryList.add("politics");
		try{
		for(int i=0;i<categoryList.size();i++)
		      categoryAccess.insertCategory(i+1,categoryList.get(i));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		Category ca=new Category();
		user=(User) session.getAttribute("theUser");
		System.out.println(user.getEmail());;
		try{
		for(int i=0; i<preference.length;i++)	
		    categoryAccess.insertUserCategory(preference[i],user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		url="/main.jsp";
			
		}
		
		
		//Handle source addition
		if(action.equals("add")){
			//TODO: Get the source information added by the user and store it in the DB
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
