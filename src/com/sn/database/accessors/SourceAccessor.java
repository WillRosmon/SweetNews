package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;
import com.sn.database.utilities.DbConstants;
import com.sn.database.utilities.DbUtil;

public class SourceAccessor {
	private Connection connection = null;
	
	private PreparedStatement _insertSource;
	private PreparedStatement _selectourceByID;
	private PreparedStatement _selectSourcesByTopic;
	private PreparedStatement _selectAllSources;
	private PreparedStatement _deleteSource;
	
	public SourceAccessor(Connection connection) {
		this.connection = connection;
	}
	
	public List<Source> selectAllSources() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		ArrayList<Source> sources = new ArrayList<Source>();
		sb.append("SELECT * FROM ")
		.append(DbConstants.SOURCE_TABLE);
		
		try {
			ps = connection.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				sources.add(asSource(rs));
			}
			return sources;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(ps, rs);
			pool.freeConnection(connection);
		}
		return null;
	}
	
	
	private void cleanup(PreparedStatement ps, ResultSet rs) {
		if(ps != null) {
			DbUtil.closePreparedStatement(ps);
		}
		if(rs != null) {
			DbUtil.closeResultSet(rs);
		}
	}
	private Source asSource(ResultSet rs) {
		Source source = new Source();
		
		return source;
	}
	
	private PreparedStatement getInsertStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.SOURCE_TABLE);
		
		_insertSource = connection.prepareStatement(sb.toString());
		return _insertSource;
	}
	
	private PreparedStatement getSelectAllSourcesStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		_selectAllSources = connection.prepareStatement(sb.toString());
		return _selectAllSources;
	}
	
	private PreparedStatement getSelectSourceByIDStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" WHERE ");
		
		_selectAllSources = connection.prepareStatement(sb.toString());
		return _selectAllSources;
	}
	
	private PreparedStatement getSelectSourceByTopicStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" WHERE ");
		
		_selectSourcesByTopic = connection.prepareStatement(sb.toString());
		return _selectSourcesByTopic;
	}
}
