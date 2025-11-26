/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Model.Book;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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
public class BookDatabaseHandler {

    public static ObservableList<Book> getBooksData() {
        Connection conn = DatabaseConnection.getInstance();
        ObservableList<Book> tempBooks = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) conn.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempBooks.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("auther"), rs.getString("category"), rs.getString("isbn"), rs.getString("publishDate"), rs.getInt("pageCount"), rs.getInt("copyCount"), rs.getString("publisher"), rs.getString("language"), rs.getString("profileImagePath")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempBooks;
    }

    public static void AddBook(Book book) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "INSERT INTO books(title,auther,category,isbn,publishDate,pageCount,copyCount,publisher,language,profileImagePath) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuther());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublishDate());
            ps.setInt(6, book.getPagecount());
            ps.setInt(7, book.getCopycount());
            ps.setString(8, book.getPublisher());
            ps.setString(9, book.getLanguage());
            ps.setString(10, book.getProfileImagepath());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void UpdateBook(Book book) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "update books set title = ?,auther =  ?,category  = ?,isbn =?,publishDate = ?,pageCount = ?,copyCount = ?,publisher = ?,language = ?,profileImagePath = ? where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuther());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublishDate());
            ps.setInt(6, book.getPagecount());
            ps.setInt(7, book.getCopycount());
            ps.setString(8, book.getPublisher());
            ps.setString(9, book.getLanguage());
            ps.setString(10, book.getProfileImagepath());
            ps.setInt(11, book.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void DeleteBook(Book book) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "delete from books where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setInt(1, book.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateCopyCount(Book book) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "update books set copyCount = ? where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            int copuCount = book.getCopycount()+1;
             ps.setInt(1,copuCount);
             ps.setInt(2, book.getId());
              ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void MinCopyCount(Book book) {
        Connection conn = DatabaseConnection.getInstance();
        try {
            String query = "update books set copyCount = ? where id = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            int copuCount = book.getCopycount()-1;
             ps.setInt(1,copuCount);
             ps.setInt(2, book.getId());
              ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
