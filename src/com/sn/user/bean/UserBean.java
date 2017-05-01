package com.sn.user.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sn.database.accessors.CategoryAccessor;
import com.sn.database.accessors.UserAccessor;
import com.sn.database.objects.Category;
import com.sn.database.objects.User;
import com.sn.database.utilities.ConnectionPool;

public class UserBean {

	private User user;
	private Category category;
	
	public UserBean() {
		this.user = null;
		this.category= null;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User readUserById(String id) {
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		UserAccessor userAccessor = new UserAccessor(connection);
		
		User user = userAccessor.getUser(id);
		
		pool.freeConnection(connection);
		
		return user;
	}
	
	public void addUser(User user) {
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		UserAccessor userAccessor = new UserAccessor(connection);
		
		try{			
		userAccessor.addUser(user);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		pool.freeConnection(connection);
	}
	
public User getUser(String email) throws SQLException {
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		UserAccessor userAccessor = new UserAccessor(connection);
		
		User user = userAccessor.getUser(email);
		
		pool.freeConnection(connection);
		return user;
}

	public boolean login (User user) {
		
		User loginuser=new User();
		
		loginuser=readUserById(user.getEmail());
		
		System.out.println(loginuser.getEmail());
		
		if(loginuser == null || !loginuser.getPassword().equals(user.getPassword()))
		   return false;
		else
		   return true;
	}
	
	public String checkUserType(User user)
	{
		String type="admin";
		
		User loginuser=new User();
		loginuser=readUserById(user.getEmail());
		
		if(user.getEmail().equals("admin123@asd") || user.getEmail().equals("admin016@asd"))
		    return type;
		else
			return "user";
	}
	
	public void addPreference(User user,String[] interest)
	{
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		CategoryAccessor categoryaccessor= new CategoryAccessor(connection);
		
		try{
		for(int i=0;i<interest.length;i++){
			categoryaccessor.insertUserCategory(interest[i], user);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		pool.freeConnection(connection);
				
	}
	
	public User getPreference(User user)
	{
		List<Category> categorylist = new ArrayList<Category>();
	    List<String> usercategory = new ArrayList<String>();
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		CategoryAccessor categoryaccessor= new CategoryAccessor(connection);
		categorylist = categoryaccessor.selectUserCategory(user);
		
		for(int i=0;i<categorylist.size();i++)
		{
			usercategory.add(categorylist.get(i).getCategory());
			
		}
		user.setInterests(usercategory);
		return user;
	}
	
	
}
