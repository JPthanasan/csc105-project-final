package com.example.project_backend.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String email;

    public User(int id, String username, String password, String email) {
        this.user_id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(ResultSet rs) throws SQLException {
        this.user_id = rs.getInt("user_id");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.email = rs.getString("email");
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
