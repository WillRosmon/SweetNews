package com.sn.database.accessors;

import java.sql.Connection;

import com.sn.database.utilities.DbConstants;

public class CategoryAccessor {

	Connection connection;
	
	public CategoryAccessor(Connection connection) {
		this.connection = connection;
	}
	
	
}
