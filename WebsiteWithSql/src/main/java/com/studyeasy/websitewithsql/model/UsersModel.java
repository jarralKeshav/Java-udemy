package com.studyeasy.websitewithsql.model;

import com.studyeasy.websitewithsql.config.DatabaseConfig;
import com.studyeasy.websitewithsql.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersModel {
    public List<User> listusers() {
        List<User> listusers = new ArrayList<>();
        Connection connect = DatabaseConfig.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "select * from users";
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                listusers.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return listusers;
    }


    public void addUser(User newUser){
        PreparedStatement stmt=null;
        try {
            Connection connect = DatabaseConfig.getConnection();
            String username = newUser.getUsername();
            String email = newUser.getEmail();
            String query = "insert into users (username, email) values (?, ?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2,email);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User updateUser){
        Connection connect = null;
        PreparedStatement stmt = null;
        try {
            connect = DatabaseConfig.getConnection();
            int user_id = updateUser.getUser_id();
            String username = updateUser.getUsername();
            String email = updateUser.getEmail();
            String query = "update users set username = ?, email = ? where user_id = ?";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setInt(3,user_id);
            stmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int user_id){
        Connection connect = null;
        PreparedStatement stmt = null;

        try {
            connect = DatabaseConfig.getConnection();
            String query = "delete from users where user_id = ?";
            stmt = connect.prepareStatement(query);
            stmt.setInt(1,user_id);
            stmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
