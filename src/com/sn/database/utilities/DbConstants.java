package com.sn.database.utilities;

public class DbConstants {

	public static final String DB_NAME = "sweetnews";
	
	/*Table Names*/
	public static final String SOURCE_TABLE = "source";
	public static final String USER_TABLE= "user";
	public static final String USERLOGIN_TABLE= "userlogin";
	public static final String CATEGORY_TABLE= "category";
	public static final String USER_PREFERENCE_TABLE= "user_preference";
	public static final String ARTICLE_TABLE= "article";
	
	
	/*User Table*/
	public static final String USER_COL_USERID= "UserId";
	public static final String USER_COL_FIRSTNAME= "FirstName";
	public static final String USER_COL_LASTNAME= "LastName";
	public static final String USER_COL_DOB= "DOB";
	public static final String USER_COL_EMAIL= "Email";

	
	/*UserLogin Table*/
	public static final String USERLOGIN_COL_USERNAME= "UserName";
	public static final String USERLOGIN_COL_PASSWORD= "Password";

	
	/*Category Table*/
	public static final String CATEGORY_COL_CATEGORYID= "CategoryId";
	public static final String CATEGORY_COL_CATEGORYDESCRIPTION= "CategoryDescription";
	
	
	/*User-Preference Table*/
	public static final String USER_PREFERENCE_COL_USERID= "UserId";
	public static final String USER_PREFERENCE_COL_CATEGORYID= "CategoryId";
	
	
	/*Source Table*/
	//public static final String SOURCE_COL_USERID= "SourceId";
	public static final String SOURCE_COL_NAME= "Name";
	public static final String SOURCE_COL_DESCRIPTION= "Description";
	public static final String SOURCE_COL_URL= "Url";
	public static final String SOURCE_COL_CATEGORY= "Category";
	public static final String SOURCE_COL_LANGUAGE= "Language";
	public static final String SOURCE_COL_COUNTRY= "Country";
	public static final String SOURCE_COL_URLLOGO= "UrlLogo";
	public static final String SOURCE_COL_APPROVALSTATUS= "ApprovalStatus";
	public static final String SOURCE_COL_SOURCE_ID = "SourceId";

	/*Article Table*/
	public static final String ARTICLE_COL_AUTHOR= "Author";
	public static final String ARTICLE_COL_DESCRIPTION= "Description";
	public static final String ARTICLE_COL_TITLE= "Title";
	public static final String ARTICLE_COL_URL= "Url";
	public static final String ARTICLE_COL_URLTOIMAGE= "UrlToImage";
	public static final String ARTICLE_COL_PUBLISHTIME= "publishTime";
    public static final String ARTICLE_COL_SOURCEID="SourceId";

}
