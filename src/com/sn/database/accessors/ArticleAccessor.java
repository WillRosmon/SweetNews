package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sn.database.objects.Article;

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
