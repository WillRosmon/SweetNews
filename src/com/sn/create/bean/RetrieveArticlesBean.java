package com.sn.create.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sn.api.util.ArticleRequest;
import com.sn.api.util.SourceRequest;
import com.sn.database.accessors.ArticleAccessor;
import com.sn.database.accessors.SourceAccessor;
import com.sn.database.objects.Article;
import com.sn.database.objects.Category;
import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;

public class RetrieveArticlesBean {

	public RetrieveArticlesBean() {
		
	}
	
	public void initaliseArticles()
	{
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		List<Source> sourceList=new ArrayList<Source>();
		Article article = new Article();
		
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		SourceAccessor sourceaccess = new SourceAccessor(connection);
		ArticleAccessor articleaccess = new ArticleAccessor(connection);
		
		sourceList=sourceaccess.selectAllSources();
		Iterator<Source> itSource = sourceList.iterator();
		
		try{
			
			while(itSource.hasNext())
			{
			 Source source=itSource.next();	
			 
			 ja=ArticleRequest.getArticlesBySource(source.getId());
			
			 for(int i=0;i<ja.length();i++)
			 {
				jo=ja.getJSONObject(i);
				if(jo.get("author").toString() == null)
					article.setAuthor("anonymous");
				else
				    article.setAuthor(jo.get("author").toString());
				article.setDescription(jo.get("description").toString());
				article.setPublishTime(jo.get("publishedAt").toString());
				article.setSource(source.getId());
				article.setTitle(jo.get("title").toString());
				article.setUrl(jo.get("url").toString());
				article.setUrlToImage(jo.get("urlToImage").toString());
				System.out.println("Inserting Article" + article.getTitle());
				articleaccess.insertArticle(article);
			 }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	
	public List<Article> retrieveArticlesByTopic(List<String> categoryList) {
		Connection connection = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.getConnection();
		
		List<Article> articleList = new ArrayList<>();
		ArticleAccessor articleAccessor = new ArticleAccessor(connection);
		
		for(int i=0;i<categoryList.size();i++) {
			articleList.addAll(articleAccessor.getSelectArticlesByTopicStatement(categoryList.get(i)));
		}
		return articleList;
	}
	
	public List<Article> getMainPageArticles() {
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("general");
		List<Article> articlesList = retrieveArticlesByTopic(categoryList);
		System.out.println("DB Returned " + articlesList.size());
		List<Article> returnList = new ArrayList<Article>();
		if(articlesList != null && !articlesList.isEmpty()) {
			if(articlesList.size() <= 3) {
				return articlesList;
			} else {
				for(int i = articlesList.size() - 3; i < articlesList.size(); i++) {
					returnList.add(articlesList.get(i));
					return returnList;
				}
			}
		}
		return null;
	}
}
