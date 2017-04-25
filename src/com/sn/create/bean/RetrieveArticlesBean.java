package com.sn.create.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sn.database.accessors.ArticleAccessor;
import com.sn.database.objects.Article;
import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;

public class RetrieveArticlesBean {

	public RetrieveArticlesBean() {
		
	}
	
	
	public List<Article> retrieveArticlesByTopic(String topic) {
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		List<Source> sourceList;
		List<Article> articleList = new ArrayList<>();
		ArticleAccessor articleAccessor = new ArticleAccessor(connection);
		RetrieveSourcesBean sourcesBean = new RetrieveSourcesBean();
		sourceList = sourcesBean.getSourcesByCategory(topic);
		for(Source s : sourceList) {
			articleList.addAll(articleAccessor.getSelectArticlesBySourceStatement(s));
		}
		return articleList;
	}
}
