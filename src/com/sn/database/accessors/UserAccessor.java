package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sn.database.objects.Category;
import com.sn.database.objects.User;
import com.sn.database.utilities.ConnectionPool;
import com.sn.database.utilities.DbConstants;
import com.sn.database.utilities.DbUtil;

public class UserAccessor {
	private Connection connection;
	
	private PreparedStatement _insertUserStatement ;
	private PreparedStatement _insertLoginUserStatement ;
	private PreparedStatement _selectUserStatement ;
	private PreparedStatement _selectAllUsersStatement ;
	
	private PreparedStatement _insertUserPreference;
	private PreparedStatement _selectUserPreferences;
	
	public UserAccessor(Connection connection) {
		this.connection = connection;
	}

	
	public void addUser(User user) throws SQLException {
		try {
			_insertUserStatement = getInsertStatement();
			_insertUserStatement.setString(1, user.getFirstName());
			_insertUserStatement.setString(2, user.getLastName());
			_insertUserStatement.setString(3, user.getEmail());
			
			_insertUserStatement.executeUpdate();
			
			_insertLoginUserStatement=getLoginUserInsertStatement();
			_insertLoginUserStatement.setString(1, user.getEmail());
			_insertLoginUserStatement.setString(1, user.getPassword());
			
			_insertLoginUserStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			_insertUserStatement.close();
			_insertLoginUserStatement.close();
		}
	}
	
	private PreparedStatement getInsertStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.USER_TABLE);
		sb.append(" ( ");
		sb.append(DbConstants.USER_COL_FIRSTNAME);
		sb.append(", ");
		sb.append(DbConstants.USER_COL_LASTNAME);
		sb.append(" , ");
		sb.append(DbConstants.USER_COL_EMAIL);
		sb.append(" ) " );
		sb.append(" VALUES ( ?, ? ,?); ");
		
		_insertUserStatement = connection.prepareStatement(sb.toString());
		return _insertUserStatement;
	}
	
	private PreparedStatement getLoginUserInsertStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.USERLOGIN_TABLE);
		sb.append(" ( ");
		sb.append(DbConstants.USERLOGIN_COL_USERNAME);
		sb.append(", ");
		sb.append(DbConstants.USERLOGIN_COL_PASSWORD);
		sb.append(" ) ");
		sb.append("VALUES ( ?, ? ); ");
		
		_insertLoginUserStatement = connection.prepareStatement(sb.toString());
		return _insertLoginUserStatement;
	}
	
	
	public User getUser(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		
		ResultSet rs = null;
		
		try {
			_selectUserStatement = getSelectUserStatement();
			rs = _selectUserStatement.executeQuery();

			return asUser(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectUserStatement, rs);
			pool.freeConnection(connection);
		}
		return null;
	}
		
	public ArrayList<User> getUsers() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			_selectAllUsersStatement = getUsersStatement();
			rs = _selectAllUsersStatement.executeQuery();
			while (rs.next())
			{
				User user = new User();
				user.setFirstName(rs.getString(DbConstants.USER_COL_FIRSTNAME));
				user.setFirstName(rs.getString(DbConstants.USER_COL_LASTNAME));
				user.setFirstName(rs.getString(DbConstants.USER_COL_EMAIL));
				user.setPassword(rs.getString(DbConstants.USERLOGIN_COL_PASSWORD));
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectAllUsersStatement, rs);
			pool.freeConnection(connection);
		}
		return null;
	}
	
	private PreparedStatement getSelectUserStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+DbConstants.USER_TABLE+"."+DbConstants.USER_COL_FIRSTNAME+" , ");
		sb.append(DbConstants.USER_TABLE+"."+DbConstants.USER_COL_LASTNAME+" , ");
		sb.append(DbConstants.USER_TABLE+"."+DbConstants.USER_COL_EMAIL+" , ");
		sb.append(DbConstants.USERLOGIN_TABLE+"."+DbConstants.USERLOGIN_COL_PASSWORD);
		sb.append(" FROM ");
		sb.append(DbConstants.USER_TABLE);
		sb.append(" , "+DbConstants.USERLOGIN_TABLE + " where ");
		sb.append(DbConstants.USER_TABLE+"."+DbConstants.USER_COL_EMAIL+" = ");
		sb.append(DbConstants.USERLOGIN_TABLE+"."+DbConstants.USERLOGIN_COL_USERNAME);
		_selectUserStatement = connection.prepareStatement(sb.toString());
		return _selectUserStatement;
	}
	
	private PreparedStatement getUsersStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+DbConstants.USER_TABLE+"."+DbConstants.USER_COL_FIRSTNAME+" , ");
		sb.append(DbConstants.USER_TABLE+"."+DbConstants.USER_COL_LASTNAME+" , ");
		sb.append(DbConstants.USER_TABLE+"."+DbConstants.USER_COL_EMAIL+" , ");
		sb.append(DbConstants.USERLOGIN_TABLE+"."+DbConstants.USERLOGIN_COL_PASSWORD);
		sb.append(" FROM ");
		sb.append(DbConstants.USER_TABLE);
		sb.append(" , "+DbConstants.USERLOGIN_TABLE);
		_selectAllUsersStatement = connection.prepareStatement(sb.toString());
		return _selectAllUsersStatement;
	}
	
	private PreparedStatement getInsertUserPreferenceStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.USER_PREFERENCE_TABLE);
		sb.append(" ( ");
		sb.append(DbConstants.USER_PREFERENCE_COL_USERID);
		sb.append(", ");
		sb.append(DbConstants.USER_PREFERENCE_COL_CATEGORYID);
		sb.append(" VALUES ( ?, ? )");
		
		_insertUserPreference = connection.prepareStatement(sb.toString());
		return _insertUserPreference;
	}
	
	private PreparedStatement getSelectUserPreferencesStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.USER_PREFERENCE_TABLE);
		sb.append(" WHERE ");
		sb.append(DbConstants.USER_PREFERENCE_COL_USERID);
		sb.append(" = ? ");
		_selectUserPreferences = connection.prepareStatement(sb.toString());
		return _selectUserPreferences;
	}
	
	private User asUser(ResultSet rs) throws SQLException{
		User user = new User();
		user.setFirstName(rs.getString(DbConstants.USER_COL_FIRSTNAME));
		user.setFirstName(rs.getString(DbConstants.USER_COL_LASTNAME));
		user.setFirstName(rs.getString(DbConstants.USER_COL_EMAIL));
		user.setPassword(rs.getString(DbConstants.USERLOGIN_COL_PASSWORD));
		addUserPreferences(user);
		return user;
	}
	
	private void addUserPreferences(User user) throws SQLException {
		CategoryAccessor categoryAccessor = new CategoryAccessor(connection);
		List<Category> categories = categoryAccessor.selectUserCategory(user);
		for(Category c : categories) {
			user.addInterest(c.getCategory());
		}
	}
	
	private void cleanup(PreparedStatement ps, ResultSet rs) {
		if(ps != null) {
			DbUtil.closePreparedStatement(ps);
		}
		if(rs != null) {
			DbUtil.closeResultSet(rs);
		}
	}
}
