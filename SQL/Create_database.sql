DROP DATABASE SWEETNEWS;

CREATE DATABASE SWEETNEWS;

SET SCHEMA SWEETNEWS;

CREATE TABLE SOURCES (
	ID VARCHAR(30) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(100),
	URL VARCHAR(100) NOT NULL,
	CATEGORY VARCHAR(30) NOT NULL,
	LANGUAGE VARCHAR(3) NOT NULL WITH DEFAULT 'ENG',
	COUNTRY VARCHAR(3) NOT NULL WITH DEFAULT 'USA',
	URL_LOGO VARCHAR(100) NOT NULL,
	
	CONSTRAINT Source_PK PRIMARY KEY(ID)
);

CREATE TABLE ARTICLES ()
	TITLE VARCHAR(30) NOT NULL,
	AUTHOR VARCHAR(40) NOT NULL,
	DESCRIPTION VARCHAR(300) NOT NULL,
	URL VARCHAR(100) NOT NULL,
	IMAGE_URL VARCHAR(100),
	PUBLISH_TIME VARCHAR(100),
	SOURCE VARCHAR(30) NOT NULL,
	
	CONSTRAINT ARTICLE_PK PRIMARY KEY(URL),
	CONSTRAINT ARTICLE_SOURCE_FK FOREIGN KEY(SOURCE) REFERENCES SOURCES.ID
