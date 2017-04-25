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
	private String userId;
	private String approvalStatus;
	
	public Source() {
		this.id = null;
		this.name = null;
		this.description = null;
		this.url = null;
		this.category = null;
		this.language = null;
		this.country = null;
		this.approvalStatus = null;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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
