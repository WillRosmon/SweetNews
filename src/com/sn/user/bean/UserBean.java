package com.sn.user.bean;

import java.sql.Connection;
import java.sql.SQLException;

import com.sn.database.accessors.UserAccessor;
import com.sn.database.objects.User;
import com.sn.database.utilities.ConnectionPool;

public class UserBean {

	private User user;
	
	public UserBean() {
		this.user = null;
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
	
	public boolean login (User user) {
		boolean success = Boolean.TRUE;
		User loginuser=new User();
		loginuser=readUserById(user.getEmail());
		if(loginuser == null)
		   return Boolean.FALSE;
		else
		   return success;
	}
	
	public String checkUserType(User user)
	{
		String type="admin";
		if(readUserById(user.getEmail()).getEmail().contentEquals("admin"))
		    return type;
		else
			return "user";
	}
}
