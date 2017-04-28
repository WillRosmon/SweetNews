package com.sn.create.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sn.api.util.SourceRequest;
import com.sn.database.accessors.CategoryAccessor;
import com.sn.database.accessors.SourceAccessor;
import com.sn.database.objects.Category;
import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;
import com.sn.list.beans.CreateSourceListBean;

public class RetrieveSourcesBean {

	public RetrieveSourcesBean(){
		
	}
	
	public void initialiseSources()
	{
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		Source source=new Source();
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		SourceAccessor sourceaccess = new SourceAccessor(connection);
		
		try{
			ja=SourceRequest.getAllSources();
			for(int i=0;i<ja.length();i++)
			{
				jo=ja.getJSONObject(i);
				source.setId(jo.getString("id"));
				source.setName(jo.getString("name"));
				source.setDescription(jo.getString("description"));;
				source.setUrl(jo.getString("url"));
				source.setCountry(jo.getString("country"));
				source.setCategory(jo.getString("category"));
				source.setLanguage(jo.getString("language"));
				source.setApprovalStatus(1);
				sourceaccess.insertSource(source);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
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
