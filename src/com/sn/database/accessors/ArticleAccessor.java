package com.sn.database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sn.database.objects.Article;
import com.sn.database.objects.Category;
import com.sn.database.objects.Source;
import com.sn.database.utilities.ConnectionPool;
import com.sn.database.utilities.DbConstants;
import com.sn.database.utilities.DbUtil;

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
	
	public void insertArticle(Article article,Source source) throws SQLException {
		try {
			_insertArticleStatement = getInsertStatement();
			_insertArticleStatement.setString(1, article.getAuthor());
			_insertArticleStatement.setString(2, article.getDescription());
			_insertArticleStatement.setString(3, article.getPublishTime());
			_insertArticleStatement.setString(4, article.getTitle());
			_insertArticleStatement.setString(5, article.getUrl());
			_insertArticleStatement.setString(6, article.getUrlToImage());
			_insertArticleStatement.setString(7, source.getId());

			
			_insertArticleStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			_insertArticleStatement.close();
		}
	}
	
	private PreparedStatement getInsertStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(DbConstants.ARTICLE_TABLE);
		sb.append(" ( ");
		sb.append(DbConstants.ARTICLE_COL_AUTHOR);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_DESCRIPTION);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_PUBLISHTIME);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_TITLE);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_URL);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_URLTOIMAGE);
		sb.append(", ");
		sb.append(DbConstants.ARTICLE_COL_SOURCEID);
		sb.append(" ) ");
		sb.append("VALUES ( ?, ?, ?, ?, ?, ?, ? ); ");
		
		_insertArticleStatement = connection.prepareStatement(sb.toString());
		return _insertArticleStatement;
	}
	
/**********************/
	
	public List<Article> getSelectArticlesByTopicStatement(Category category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		
		ResultSet rs = null;
		ArrayList<Article> articles = new ArrayList<Article>();
		
		try {
			_selectArticlesByTopicStatement = getArticlesByTopicStatement();
			_selectArticlesByTopicStatement.setString(1, category.getCategory());
			rs = _selectArticlesByTopicStatement.executeQuery();
			
			while(rs.next()) {
				articles.add(asArticle(rs));
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectArticlesByTopicStatement, rs);
			pool.freeConnection(connection);
		}
		
		return null;
	}
	
	private PreparedStatement getArticlesByTopicStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_AUTHOR+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_DESCRIPTION+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_PUBLISHTIME+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_SOURCEID+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_TITLE+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_URL+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_URLTOIMAGE);
		sb.append(" FROM ");
		sb.append(DbConstants.CATEGORY_TABLE);
		sb.append(" , ");
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" , ");
		sb.append(DbConstants.ARTICLE_TABLE);
		sb.append(" where ");
		sb.append(DbConstants.CATEGORY_TABLE+"."+DbConstants.CATEGORY_COL_CATEGORYDESCRIPTION);
		sb.append(" = ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_CATEGORY);
		sb.append(" and ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_SOURCE_ID);
		sb.append(" = ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_SOURCEID);
		sb.append(" and ");
		sb.append(DbConstants.CATEGORY_TABLE+"."+DbConstants.CATEGORY_COL_CATEGORYDESCRIPTION+" = ?");
		
		_selectArticlesByTopicStatement = connection.prepareStatement(sb.toString());
		return _selectArticlesByTopicStatement;
	}

/****************************/
	public List<Article> getSelectArticlesBySourceStatement(Source source) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		
		ResultSet rs = null;
		ArrayList<Article> articles = new ArrayList<Article>();
		
		try {
			_selectArticlesBySourceStatement = getArticlesBySourceStatement();
			_selectArticlesBySourceStatement.setString(1, source.getId());
			rs = _selectArticlesBySourceStatement.executeQuery();
			
			while(rs.next()) {
				articles.add(asArticle(rs));
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(_selectArticlesBySourceStatement, rs);
			pool.freeConnection(connection);
		}
		
		return null;
	}
	
	private PreparedStatement getArticlesBySourceStatement() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_AUTHOR+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_DESCRIPTION+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_PUBLISHTIME+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_SOURCEID+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_TITLE+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_URL+" , ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_URLTOIMAGE);
		sb.append(DbConstants.SOURCE_TABLE);
		sb.append(" , ");
		sb.append(DbConstants.ARTICLE_TABLE);
		sb.append(" where ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_SOURCE_ID);
		sb.append(" = ");
		sb.append(DbConstants.ARTICLE_TABLE+"."+DbConstants.ARTICLE_COL_SOURCEID);
		sb.append(" and ");
		sb.append(DbConstants.SOURCE_TABLE+"."+DbConstants.SOURCE_COL_SOURCE_ID+" = ?");
		
		_selectArticlesBySourceStatement = connection.prepareStatement(sb.toString());
		return _selectArticlesBySourceStatement;
	}
	
	private void cleanup(PreparedStatement ps, ResultSet rs) {
		if(ps != null) {
			DbUtil.closePreparedStatement(ps);
		}
		if(rs != null) {
			DbUtil.closeResultSet(rs);
		}
	}
}
