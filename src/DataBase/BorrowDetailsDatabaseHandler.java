/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Model.Book;
import Model.BorrowBookDetails;
import Model.User;
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
public class BorrowDetailsDatabaseHandler {

    public static ObservableList<BorrowBookDetails> getBorrowDetails() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<BorrowBookDetails> tempBorrow = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from borrowbookdetails");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempBorrow.add(new BorrowBookDetails(rs.getInt("id"), rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempBorrow;
    }

    public static void AddBorrow(BorrowBookDetails borrowDetails) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "INSERT INTO BorrowBookDetails(userId,userName,userImage,bookId,bookTitle,bookImage,status) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, borrowDetails.getUserId());
            ps.setString(2, borrowDetails.getUserName());
            ps.setString(3, borrowDetails.getUserImage());
            ps.setInt(4, borrowDetails.getBookId());
            ps.setString(5, borrowDetails.getBookTitle());
            ps.setString(6, borrowDetails.getBookImage());
            ps.setString(7, borrowDetails.getStatus());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void DeleteBorrow(BorrowBookDetails borrowDetails) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "delete from BorrowBookDetails where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, borrowDetails.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void UpdateBorrowStatus(BorrowBookDetails borrowDetails) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "update BorrowBookDetails set status = ? where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, borrowDetails.getStatus());
            ps.setInt(2, borrowDetails.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<BorrowBookDetails> getApprovedBooka() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<BorrowBookDetails> tempApprovedBooks = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from borrowbookdetails where status = 'Approved'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempApprovedBooks.add(new BorrowBookDetails(rs.getInt("id"), rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempApprovedBooks;
    }

    public static ObservableList<BorrowBookDetails> getPendingBooks() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<BorrowBookDetails> tempPendingBooks = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from borrowbookdetails where status = 'Pending'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempPendingBooks.add(new BorrowBookDetails(rs.getInt("id"), rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempPendingBooks;
    }

    public static BorrowBookDetails getBookStatus(int userId, int bookId) {
        Connection conn = DatabaseConnection.getInstance();
        BorrowBookDetails tempBooks = new BorrowBookDetails();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from borrowbookdetails where userId = ? and bookId = ?");
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempBooks.setId(rs.getInt("id"));
                tempBooks.setUserId(rs.getInt("userId"));
                tempBooks.setUserName(rs.getString("userName"));
                tempBooks.setUserImage(rs.getString("userImage"));
                tempBooks.setBookId(rs.getInt("bookId"));
                tempBooks.setBookTitle(rs.getString("bookTitle"));
                tempBooks.setBookImage(rs.getString("bookImage"));
                tempBooks.setStatus(rs.getString("status"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempBooks;
    }

    public static ObservableList<BorrowBookDetails> getUserBooks(User user) {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<BorrowBookDetails> tempBorrow = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from borrowbookdetails where userId = ?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempBorrow.add(new BorrowBookDetails(rs.getInt("id"), rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailsDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempBorrow;
    }

}
