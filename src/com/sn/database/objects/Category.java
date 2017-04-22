package com.sn.database.objects;

public class Category {

	private int categoryId;
	private String category;
	
	public Category() {
		this.category = null;
		this.categoryId = 0;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
