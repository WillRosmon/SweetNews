package com.sn.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sn.database.objects.*;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String url = "/index.jsp";
		String action = request.getParameter("action");
		//Proper way to instantiate?
		User user = new User();
		
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
			//TODO: If it does not, display this message.
			msg = "Sorry but this user does not exist. <br/>" + 
                    "Please try another email address.";
			//TODO: If it does exist, set the role of the user and use the session attributes below
			//session.setAttribute("theUser",user);
			//session.setAttribute("theAdmin",admin);
			
			
			
			request.setAttribute("msg", msg);
		}
		
		//Handle Sign Up Form...
		if(action.equals("create")){
			String msg="";
			//TODO: Get parameters from text field and set them to the user object
			//Do the same check for email address as above and manually set the role to admin or user
			msg = "Sorry, the email you entered already exists. <br/>" +
                    "Please enter another email address.";
			
		}
		
		if(action.equals("preference")){
			//TODO: Get selected preferences from the radio buttons and store them to the user object
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
