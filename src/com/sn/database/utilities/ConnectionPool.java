package com.sn.database.utilities;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {
	private static DataSource dataSource;
	private static ConnectionPool pool;
	
	private ConnectionPool () {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/SweetNewsDataSource");
            if(dataSource == null) {
                System.out.println("DATASOURCE IS NULL");
            }
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
	
	public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
	
	public void freeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
