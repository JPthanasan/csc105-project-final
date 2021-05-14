package com.example.project_backend.Model;

import com.example.project_backend.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperation {
    Connection con;
    public UserOperation() {

    }

    public User insertNewUser(String username, String password, String email) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO db63130500242.User (username, password, email) VALUES (?, ?, ?);");
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.execute();
            return this.getUser(username);
        } finally {
            con.close();
        }
    }

    public User getUser(String username) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM db63130500242.User WHERE username = ?");
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs);
            }
            return user;
        }finally {
            con.close();
        }
    }

    public User getUser(int user_id) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM db63130500242.User WHERE user_id = ?");
            pstm.setInt(1, user_id);
            ResultSet rs = pstm.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs);
            }
            return user;
        }finally {
            con.close();
        }
    }

    public User signInUser(String username, String password) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs);
            }
            return user;
        } finally {
            con.close();
        }
    }

}
