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
	private PreparedStatement _insertSourceAdder;
	private PreparedStatement _selectourceByID;
	private PreparedStatement _selectSourcesByTopic;
	private PreparedStatement _selectAllSources;
	private PreparedStatement _selectSourcesForApproval;
	
	private PreparedStatement _updateApproval;
	private PreparedStatement _diapproveStatus;
	
	
	public SourceAccessor(Connection connection) {
		this.connection = connection;
	}
	
	public List<Source> selectAllSources() {
		ResultSet rs = null;
		ArrayList<Source> sources = new ArrayList<Source>();
		
		
		try {
			_selectAllSources = getSelectAllSourcesStatement();
			rs = _selectAllSources.executeQuery();
			
			while(rs.next()) {
				sources.add(asSource(rs));
			}
			return sources;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectAllSources, rs);
		}
		return null;
	}
	
	public List<Source> selectSourcesForApproval(String username) {
		ResultSet rs = null;
		ArrayList<Source> sources = new ArrayList<Source>();
		
		
		try {
			_selectSourcesForApproval = getSelectSourcesForApprovalStatement();
			_selectSourcesForApproval.setString(1, username);
			
			rs = _selectSourcesForApproval.executeQuery();
			
			while(rs.next()) {
				sources.add(asSourceAdded(rs));
			}
			return sources;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectSourcesForApproval, rs);
		}
		return null;
	}
	
	
	public int updateApproval(String sourceid) {
		
		ResultSet rs = null;
		
		try {
			_updateApproval = updateApprovalStatement();
			_updateApproval.setString(1, sourceid);
			
			return _updateApproval.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_updateApproval, rs);
		}
		return 0;
	}
	
		public int disapproveStatus(String sourceid) {
		
		ResultSet rs = null;
		
		try {
			_diapproveStatus = disapproveStatusStatement();
			_diapproveStatus.setString(1, sourceid);
			
			return _diapproveStatus.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_diapproveStatus, rs);
		}
			return 0;
		}

	public Source selectSourceById(String id) {
		ResultSet rs = null;
		try {
			Source source = new Source();
			_selectourceByID = getSelectSourceByIDStatement();
			_selectourceByID.setString(1, id);
			rs = _selectourceByID.executeQuery();
			while(rs.next()) {
				source = asSource(rs);
			}
			return source;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectourceByID, rs);
		}
		return null;
	}
	
	public void insertSource(Source source) throws SQLException {
		try {
			_insertSource = getInsertStatement();
			_insertSource.setString(2, source.getName());
			_insertSource.setString(3, source.getDescription());
			_insertSource.setString(4, source.getUrl());
			_insertSource.setString(5, source.getCategory());
			_insertSource.setString(6, source.getLanguage());
			_insertSource.setString(7, source.getCountry());
			_insertSource.setString(8, "image");
			_insertSource.setInt(9, source.getApprovalStatus());
			_insertSource.setString(1, source.getId());
			
			_insertSource.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			_insertSource.close();
		}
	}
	
	public void insertSourceAdder(Source source) throws SQLException {
		try {
			_insertSourceAdder = getInsertAdderStatement();
			_insertSourceAdder.setString(2, source.getUserId());
			_insertSourceAdder.setString(1, source.getId());
			
			_insertSourceAdder.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			_insertSourceAdder.close();
		}
	}
	
	public List<Source> selectSourcesByTopic(String topic) {
		ResultSet rs = null;
		try {
			_selectSourcesByTopic = getSelectSourceByTopicStatement();
			_selectSourcesByTopic.setString(1, topic);
			ArrayList<Source> sourceList = new ArrayList<Source>();
			rs = _selectSourcesByTopic.executeQuery();
			while(rs.next()) {
				sourceList.add(asSource(rs));
			}
			return sourceList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanup(_selectSourcesByTopic, rs);
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
	private Source asSource(ResultSet rs) throws SQLException {
		Source source = new Source();
		source.setCategory(rs.getString(DbConstants.SOURCE_COL_CATEGORY));
		source.setCountry(rs.getString(DbConstants.SOURCE_COL_COUNTRY));
		source.setDescription(rs.getString(DbConstants.SOURCE_COL_DESCRIPTION));
		source.setId(rs.getString(DbConstants.SOURCE_COL_SOURCE_ID));
		source.setLanguage(rs.getString(DbConstants.SOURCE_COL_LANGUAGE));
		source.setName(rs.getString(DbConstants.SOURCE_COL_NAME));
		source.setUrl(rs.getString(DbConstants.SOURCE_COL_URL));
		return source;
	}
	
	private Source asSourceAdded(ResultSet rs) throws SQLException {
		Source source = new Source();
		source.setCategory(rs.getString(DbConstants.SOURCE_COL_CATEGORY));
		source.setId(rs.getString(DbConstants.SOURCE_COL_SOURCE_ID));
		source.setUrl(rs.getString(DbConstants.SOURCE_COL_URL));
		return source;
	}
	
	private PreparedStatement getInsertStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" ( ");
		sb.append(DbConstants.SOURCE_COL_SOURCE_ID);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_NAME);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_DESCRIPTION);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_URL);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_CATEGORY);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_LANGUAGE);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_COUNTRY);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_URLLOGO);
		sb.append(", ");
		sb.append(DbConstants.SOURCE_COL_APPROVALSTATUS);
//		sb.append(DbConstants.SOURCE_COL_USERID);
//		sb.append(", ");
		
		sb.append(" ) ");
		sb.append("VALUES ( ?, ?, ?, ?, ?, ?, ?,?, ? );");
		
		_insertSource = connection.prepareStatement(sb.toString());
		return _insertSource;
	}
	
	private PreparedStatement getInsertAdderStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append("source_added");
		sb.append(" ( ");
		sb.append("SourceId");
		sb.append(", ");
		sb.append("AdminAdded");		
		sb.append(" ) ");
		sb.append("VALUES ( ?, ?);");
		
		_insertSourceAdder = connection.prepareStatement(sb.toString());
		return _insertSourceAdder;
	}
	
	private PreparedStatement getSelectAllSourcesStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" where ");
		sb.append(DbConstants.SOURCE_COL_APPROVALSTATUS+" = 1;");
		_selectAllSources = connection.prepareStatement(sb.toString());
		return _selectAllSources;
	}
	
	private PreparedStatement getSelectSourcesForApprovalStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE+" , source_added");
		sb.append(" where ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_SOURCE_ID+"= source_added.SourceId and ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_APPROVALSTATUS+"= 0 and ");
		sb.append("source_added.AdminAdded != ?");
		_selectSourcesForApproval = connection.prepareStatement(sb.toString());
		return _selectSourcesForApproval;
	}
	
	private PreparedStatement updateApprovalStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" set ");
		sb.append(DbConstants.SOURCE_COL_APPROVALSTATUS+" = 1 ");
		sb.append("where ");
		sb.append(DbConstants.SOURCE_COL_SOURCE_ID+" = ?;");
		_selectSourcesForApproval = connection.prepareStatement(sb.toString());
		return _selectSourcesForApproval;
	}
	
	private PreparedStatement disapproveStatusStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" where ");
		sb.append(DbConstants.SOURCE_COL_SOURCE_ID+" = ?;");
		_selectSourcesForApproval = connection.prepareStatement(sb.toString());
		return _selectSourcesForApproval;
	}
	
	private PreparedStatement getSelectSourceByIDStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" WHERE ");
		sb.append(DbConstants.SOURCE_COL_SOURCE_ID);
		sb.append(" = ?");
		
		_selectourceByID = connection.prepareStatement(sb.toString());
		return _selectourceByID;
	}
	
	private PreparedStatement getSelectSourceByTopicStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" WHERE ");
		sb.append(DbConstants.SOURCE_COL_CATEGORY);
		sb.append(" = ?");
		
		_selectSourcesByTopic = connection.prepareStatement(sb.toString());
		return _selectSourcesByTopic;
	}

}
