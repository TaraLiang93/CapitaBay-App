package DataBase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason
 */

import java.sql.*;


public class CapitaBay {
    
    private static final String SQL_URL = "jdbc:mysql://localhost:3306/CAPITABAY?zeroDateTimeBehavior=convertToNull";
    
    private static final String USER = "root";
    
    private static final String PASSWD = "Password1*";

    
    private static Connection conn = null;
    
    public static Connection getConnection() throws SQLException{
        if(conn == null)
            conn = DriverManager.getConnection(SQL_URL, USER, PASSWD);
        
        return conn;
    }
    
    public static ResultSet ExecuteQuery(String query) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = getConnection();
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery(query);
        
        return res;
    }

    public static String getPASSWD() {
        return PASSWD;
    }
    
    
    
}
