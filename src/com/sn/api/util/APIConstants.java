package com.sn.api.util;

public class APIConstants {

	//Regenerate once we have a more secure way of storing
	public static final String API_KEY = "96d5fc449b8b4c2c93c60fa457dba6e4";
	
	public static final String BASE_URL = "https://newsapi.org/";
	public static final String VERSION = "v1";
	public static final String ARTICLES = "articles";
	public static final String SOURCES = "sources";
	public static final String API_KEY_TAG = "apiKey";
	
	
	//Article Parameters
	public static final String SORT_BY = "sortBy";
	public static final String TOP = "top";
	public static final String LATEST = "latest";
	public static final String POPULAR = "popular";
	
	
	//Response constants
	public static final String STATUS = "status";
	public static final String SOURCE = "source";
	public static final String AUTHOR = "author";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String URL = "url";
	public static final String IMAGE_URL = "urlToImage";
	public static final String PUBLISHED_AT = "publishedAt";
	
	//Sources request constants
	public static final String CATEGORY = "category";
	public static final String LANGUAGE = "language";
	public static final String COUNTRY = "country";
	//I think just the one will work for this project - we can expand later
	public static final String COUNTRY_US = "us";
	
	//Categories
	public static final String CATEGORY_BUSINESS = "business";
	public static final String CATEGORY_ENTERTAINMENT = "entertainment";
	public static final String CATEGORY_GAMING = "gaming";
	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_MUSIC = "music";
	public static final String CATEGORY_SCIENCE_NATURE = "science-and-nature";
	public static final String CATEGORY_SPORTS = "sport";
	public static final String CATEGORY_TECHNOLOGY = "technology";
	
	//Languages
	//I think all we need to support is english for right now
	public static final String LANG_ENGLISH = "en";
	
	//Source Response COnstants
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String LOGO_URLS = "urlsToLogos";
	public static final String SMALL = "small";
	public static final String MEDIUM = "medium";
	public static final String LARGE = "large";
	public static final String SORT_AVAILABLE = "sortBysAvailable";
}
