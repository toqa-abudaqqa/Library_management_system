/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
    private int id;
    private StringProperty fullname = new SimpleStringProperty();
    private String userName;
    private String password;
    private String email; 
    private String phone;
    private String role;
    private StringProperty profileImagepath = new SimpleStringProperty();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFullname() {
        return fullname.get();
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }
    
     public StringProperty FullNameProperty() {
        return this.fullname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfileImagepath() {
        return profileImagepath.get();
    }

    public void setProfileImagepath(String profileImagepath) {
        this.profileImagepath.set(profileImagepath);
    }

    public StringProperty ProfileImagePathProperty() {
        return this.profileImagepath;
    }

    public User(String fullname, String userName, String password, String email, String phone, String role, String profileImagepath) {
        this.fullname.set(fullname);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
         this.profileImagepath.set(profileImagepath);
    }

    public User(int id,String fullname, String userName, String password, String email, String phone, String role, String profileImagepath) {
        this.id = id;
         this.fullname.set(fullname);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
         this.profileImagepath.set(profileImagepath);
    }

   
}
