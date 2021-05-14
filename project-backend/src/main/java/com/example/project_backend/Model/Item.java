package com.example.project_backend.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
    private int id;
    private String item_name;
    private int item_amount;
    private String category;
    private int user_id;

    public Item(int id, String item_name, int item_amount, String category, int user_id) {
        this.id = id;
        this.item_name = item_name;
        this.item_amount = item_amount;
        this.category = category;
        this.user_id = user_id;
    }

    public Item(ResultSet rs) throws SQLException {
        this.id = rs.getInt("item_id");
        this.item_name = rs.getString("item_name");
        this.item_amount = rs.getInt("item_amount");
        this.category = rs.getString("category");
        this.user_id = rs.getInt("user_id");
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return item_amount;
    }

    public void setQuantity(int item_amount) {
        this.item_amount = item_amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
