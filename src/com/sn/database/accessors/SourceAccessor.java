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
	Connection connection = null;
	
	public static List<Source> selectAllSources() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(ps, rs);
			pool.freeConnection(connection);
		}
	}
	
	
	private static void cleanup(PreparedStatement ps, ResultSet rs) {
		if(ps != null) {
			DbUtil.closePreparedStatement(ps);
		}
		if(rs != null) {
			DbUtil.closeResultSet(rs);
		}
	}
	private static Source asSource(ResultSet rs) {
		Source source = new Source();
		
		return source;
	}
}
