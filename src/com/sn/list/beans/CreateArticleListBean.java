package com.sn.list.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sn.api.util.APIConstants;
import com.sn.api.util.ArticleRequest;
import com.sn.api.util.SourceRequest;
import com.sn.database.objects.Article;

public class CreateArticleListBean {

	private List<Article> articlesList;
	
	public CreateArticleListBean() {
		articlesList = null;
	}
	
	public List<Article> getsArticlesListBySource(String sourceId) {
		
		JSONArray jsonArray = ArticleRequest.getArticlesBySource(sourceId);
		articlesList = new ArrayList<Article>();
		for(int i = 0; i < jsonArray.length(); i++) {
			
			try {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Article article = new Article();
				String author = jsonObject.getString(APIConstants.AUTHOR);
				article.setAuthor(author == null ? "unknown" : author);
				String description = jsonObject.getString(APIConstants.DESCRIPTION);
				article.setDescription(description == null ? "no description available" : description);
				String title = jsonObject.getString(APIConstants.TITLE);
				if(title == null) {
					continue;
				}
				article.setTitle(title);
				String publishTime = jsonObject.getString(APIConstants.PUBLISHED_AT);
				article.setPublishTime(publishTime == null ? "unknown" : publishTime);
				String imageUrl = jsonObject.getString(APIConstants.IMAGE_URL);
				article.setUrlToImage(imageUrl == null ? "null" : imageUrl);
				article.setSource(sourceId);
				articlesList.add(article);
			} catch (JSONException e) {
				Logger.getLogger(SourceRequest.class.getName()).log(Level.SEVERE, null, e);
				return null;
			}
		}
		
		return articlesList;
	}
	
	public List<Article> getArticlesByTopic(String topic) { 
		CreateSourceListBean sourceListBean = new CreateSourceListBean();
		
		return null;
	}
}
