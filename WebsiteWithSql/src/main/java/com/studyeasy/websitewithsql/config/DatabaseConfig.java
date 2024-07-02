package com.studyeasy.websitewithsql.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection(){

        String dbURL = "jdbc:mysql://localhost:3306/studyeasy?useSSl=false";
        String dbUsername = "root";
        String dbPassword = "#Keshav$001";
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
