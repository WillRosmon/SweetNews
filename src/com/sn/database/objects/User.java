package com.sn.database.objects;

import java.util.List;

public class User {
	private String email;
	private String firstName;
	private String lastName;
	private List<String> interests;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	
}