/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDatabaseHandler {

    public static ObservableList<User> getUsersData() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<User> tempUsers = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempUsers.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempUsers;
    }
    
   

    public static void AddUser(User user) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "INSERT INTO users(fullName,userName,password,email,phone,role,profileImagePath) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getProfileImagepath());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void UpdateUser(User user) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "update users set fullName = ?,userName = ?,password =  ?,email  = ?,phone =?,role = ?,profileImagePath = ? where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getProfileImagepath());
            ps.setInt(8, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void DeleteUser(User user) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "delete from users where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static ObservableList<User> getAdmins(){
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<User> tempAdmins = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from users where role = 'Admin'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempAdmins.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempAdmins;
    }
     
     public static ObservableList<User> getLibrerians(){
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<User> tempLib = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from users where role = 'Librerian'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempLib.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempLib;
    }
     
     public static ObservableList<User> getUsers(){
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<User> tempUser = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from users where role = 'User'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempUser.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempUser;
    }

}
