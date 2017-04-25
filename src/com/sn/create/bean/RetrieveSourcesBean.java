package com.sn.create.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sn.database.accessors.CategoryAccessor;
import com.sn.database.accessors.SourceAccessor;
import com.sn.database.objects.Category;
import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;
import com.sn.list.beans.CreateSourceListBean;

public class RetrieveSourcesBean {

	public void loadSources() {
		CreateSourceListBean sourceListBean = new CreateSourceListBean();
		List<Source> sourceList = sourceListBean.getSources();
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		SourceAccessor sourceAccessor = new SourceAccessor(connection);
		CategoryAccessor categoryAccessor = new CategoryAccessor(connection);
		try {
			for (Source source : sourceList) {
				Category category = new Category();
				category.setCategory(source.getCategory());
				category.setCategoryId(0);
				categoryAccessor.insertCategory(category.getCategory());
				sourceAccessor.insertSource(source);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public List<Source> getSourcesByCategory(String category) {
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		List<Source> sourceList = null;
		try {
		SourceAccessor sourceAccessor = new SourceAccessor(connection);
		sourceList = sourceAccessor.selectSourcesByTopic(category);
		return sourceList;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	
}
