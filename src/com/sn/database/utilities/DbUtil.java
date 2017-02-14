package com.sn.database.utilities;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtil {

	public static void closeStatement(Statement s) {
        try {
            if( s != null) {
                s.close();
            }
        } catch (SQLException e) {
              System.out.println(e.getMessage()); 
              Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void closeResultSet (ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
