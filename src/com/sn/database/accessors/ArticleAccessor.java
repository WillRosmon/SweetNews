package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sn.database.objects.Article;
import com.sn.database.utilities.DbConstants;

public class ArticleAccessor {

	private Connection connection = null;
	
	private PreparedStatement _insertArticleStatement = null;
	private PreparedStatement _selectArticlesByTopicStatement = null;
	private PreparedStatement _selectArticlesBySourceStatement = null;
	
	public ArticleAccessor(Connection connection) {
		this.connection = connection;
	}
	
	public List<Article> selectAllArticles() {
		
		return null;
	}
	
	private Article asArticle(ResultSet rs) throws SQLException {
		Article article = new Article();
		article.setAuthor(rs.getString(DbConstants.ARTICLE_COL_AUTHOR));
		article.setDescription(rs.getString(DbConstants.ARTICLE_COL_DESCRIPTION));
		article.setPublishTime(rs.getString(DbConstants.ARTICLE_COL_PUBLISHTIME));
		article.setTitle(rs.getString(DbConstants.ARTICLE_COL_TITLE));
		article.setUrl(rs.getString(DbConstants.ARTICLE_COL_URL));
		article.setUrlToImage(rs.getString(DbConstants.ARTICLE_COL_URLTOIMAGE));
		return article;
	}
	
	private PreparedStatement getInsertArticleStatement() throws SQLException{
		StringBuilder sb = new StringBuilder();
		
		_insertArticleStatement = connection.prepareStatement(sb.toString());
		return _insertArticleStatement;
	}
	
	private PreparedStatement getSelectArticlesByTopicStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		
		_selectArticlesByTopicStatement = connection.prepareStatement(sb.toString());
		return _selectArticlesBySourceStatement;
	}
	
	private PreparedStatement getSelectArticlesBySourceStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		
		_selectArticlesBySourceStatement = connection.prepareStatement(sb.toString());
		return _selectArticlesBySourceStatement;
	}
}
