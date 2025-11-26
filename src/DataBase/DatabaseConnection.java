/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    public static Connection connection = null;

    public static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Driver Not Found : " + e.getMessage());
        }
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                loadDriver();
                connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/librarymanagmentssystem", "root","");
                JOptionPane.showMessageDialog(null, "Connection Estaplish");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connection Faild : " + e.getMessage());
            }
        }
        return connection;
    }
//    ----------user table-----------
//    create TABLE Users(
//    id int PRIMARY KEY AUTO_INCREMENT,
//    fullName Varchar(100),
//    userName Varchar(100) UNIQUE,
//    password Varchar(100),
//    email Varchar(100),
//    phone Varchar(100),
//    role Varchar(100),
//    profileImagePath text
//    )

//    --------books Table ----------
//    CREATE TABLE books(
//    id int PRIMARY KEY AUTO_INCREMENT,
//    title varchar(50),
//    auther varchar(50),
//    category varchar(50),
//    isbn varchar(50),
//    publishDate varchar(50),
//     pageCpunt int,
//    copyCount int,
//    publisher varchar(50),
//    language varchar(50),
//    profileImagepath text
//    )
    
//    --------category table------
//    CREATE TABLE category(
//    id int PRIMARY KEY AUTO_INCREMENT,
//    category varchar(50)
//    )
    
//    --------borrow book details table-----
//    create TABLE borrowBookDetails(
//    id int PRIMARY KEY AUTO_INCREMENT,
//    userId int ,
//    userName varchar(100),
//    userImage text,
//    bookId int,
//    bookTitle varchar(50),
//    bookImage text,
//    status varchar(50),
//    FOREIGN KEY (userId) REFERENCES users(id),
//    FOREIGN KEY (bookId) REFERENCES books(id)
//    )
}
