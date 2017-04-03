package com.sn.database.objects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Source {

	private String id;
	private String name;
	private String description;
	private String url;
	private String category;
	private String language;
	private String country;
	private String urlSmallLogo;
	private String urlMedLogo;
	private String urlLargeLogo;
	private String[] sortByAvailable;
	
	public Source() {
		this.id = null;
		this.name = null;
		this.description = null;
		this.url = null;
		this.category = null;
		this.language = null;
		this.country = null;
		this.urlSmallLogo = null;
		this.urlMedLogo = null;
		this.urlLargeLogo = null;
		sortByAvailable = new String[3];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUrlSmallLogo() {
		return urlSmallLogo;
	}

	public void setUrlSmallLogo(String urlSmallLogo) {
		this.urlSmallLogo = urlSmallLogo;
	}

	public String getUrlMedLogo() {
		return urlMedLogo;
	}

	public void setUrlMedLogo(String urlMedLogo) {
		this.urlMedLogo = urlMedLogo;
	}

	public String getUrlLargeLogo() {
		return urlLargeLogo;
	}

	public void setUrlLargeLogo(String urlLargeLogo) {
		this.urlLargeLogo = urlLargeLogo;
	}

	public String[] getSortByAvailable() {
		return sortByAvailable;
	}

	public void setSortByAvailable(String[] sortByAvailable) {
		this.sortByAvailable = sortByAvailable;
	}
	
	public URL getUrlAsUrl() {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
}
