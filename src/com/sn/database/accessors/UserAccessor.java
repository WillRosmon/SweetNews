package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sn.database.objects.User;
import com.sn.database.utilities.DbUtil;

public class UserAccessor {
	private Connection connection;
	
	private PreparedStatement _insertUserStatement = null;
	private PreparedStatement _selectUserStatement = null;
	
	public UserAccessor(Connection connection) {
		this.connection = connection;
	}
	
	public void addUser(User user) {
		try {
			getInsertUserStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DbUtil.closePreparedStatement(_insertUserStatement);
	}
	
	public User getUser(String id) {
		ResultSet rs = null;
		try {
			getSelectUserStatement();
			
			rs = _selectUserStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		DbUtil.closePreparedStatement(_selectUserStatement);
		DbUtil.closeResultSet(rs);
		return asUser(rs);
	}
	
	private PreparedStatement getInsertUserStatement() throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		
		_insertUserStatement = connection.prepareStatement(sb.toString());
		return _insertUserStatement;
	}
	
	private PreparedStatement getSelectUserStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		
		_selectUserStatement = connection.prepareStatement(sb.toString());
		return _selectUserStatement;
	}

	private User asUser(ResultSet resultSet) {
		User user = new User();
		
		return user;
	}
}
