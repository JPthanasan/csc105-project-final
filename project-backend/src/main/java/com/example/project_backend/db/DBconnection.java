package com.example.project_backend.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static final String dbURL = "jdbc:mysql://csproject.sit.kmutt.ac.th:3306/db63130500242";

    public static Connection getMySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, "63130500242", "abcd1234");
            return con;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
