package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBUSER="root";
//    private static final String URL="jdbc:mysql://ozdhxpymvykw.mysql.sae.sina.com.cn:10319/crawler";
//    private static final String DBPASS="123456";
    private static final String URL="jdbc:mysql://localhost:3306/db_genealogy";
    private static final String DBPASS="123456";
    private static Connection conn;
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DBDRIVER);
        conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
        return conn;
    }
    public void close()throws SQLException {
        if(conn!=null){
            conn.close();
        }
    }
}
