package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAccessor {
	private Connection connection;
	
	private PreparedStatement _insertUserStatement = null;
	private PreparedStatement _selectUserStatement = null;
	
	public UserAccessor(Connection connection) {
		this.connection = connection;
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

}
