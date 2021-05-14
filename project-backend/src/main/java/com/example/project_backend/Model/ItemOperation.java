package com.example.project_backend.Model;

import com.example.project_backend.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemOperation {
    Connection con;
    public ItemOperation() {

    }

    public void insertNewItem(String item_name, int item_amount, String category) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Item (item_name, item_amount, category) VALUE (?, ?, ?)");
            pstm.setString(1, item_name);
            pstm.setInt(2, item_amount);
            pstm.setString(3, category);
            pstm.execute();
        } finally {
            con.close();
        }
    }

    public List<Item> viewAllItem() throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT (item_name, item_aomunt, category) FROM Item WHERE user_id = ?");
            ResultSet rs = pstm.executeQuery();
            List<Item>  list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Item(rs));
            }
            return list;
        } finally {
            con.close();
        }
    }

    public void editItem(int item_id, String item_name, int item_amount, String category) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("UPDATE Item SET (item_name = ?, item_amount = ?, category = ?) WHERE item_id = ?");
            pstm.setString(1, item_name);
            pstm.setInt(2, item_amount);
            pstm.setString(3, category);
            pstm.setInt(4, item_id);
            pstm.execute();
        } finally {
            con.close();
        }
    }

    public Item deleteItem(int item_id) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM Item WHERE item_id = ?");
            pstm.setInt(1, item_id);
            pstm.execute();
        } finally {
            con.close();
        }
        return null;
    }

    public Item viewItem(int item_id) throws SQLException {
        try {
            con = DBconnection.getMySQLConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT (item_name, item_aomunt, category) FROM Item WHERE item_id");
            ResultSet rs = pstm.executeQuery();
            rs.next();
            return new Item(rs);
        } finally {
            con.close();
        }
    }

}
