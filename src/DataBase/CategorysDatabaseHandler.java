/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Model.BorrowBookDetails;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class CategorysDatabaseHandler {
     public static ObservableList<String> getCategorys() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<String> tempCategory = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select category from category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempCategory.add(rs.getString("category"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorysDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempCategory;
    }

    public static void AddCategory(String category) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "INSERT INTO category(category) VALUES(?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, category);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategorysDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
