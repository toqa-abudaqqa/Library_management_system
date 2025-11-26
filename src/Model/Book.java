/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Book {

    private int id;
    private String title;
    private String auther;
    private String category;
    private String isbn;
    private String PublishDate;
    private int Pagecount;
    private int copycount;
    private String publisher;
    private String language;
    private String profileImagepath;

    public Book() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String PublishDate) {
        this.PublishDate = PublishDate;
    }

    public int getPagecount() {
        return Pagecount;
    }

    public void setPagecount(int Pagecount) {
        this.Pagecount = Pagecount;
    }

    public int getCopycount() {
        return copycount;
    }

    public void setCopycount(int copycount) {
        this.copycount = copycount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProfileImagepath() {
        return profileImagepath;
    }

    public void setProfileImagepath(String profileImagepath) {
        this.profileImagepath = profileImagepath;
    }

    public Book(String language, String publisher, int Pagecount, String PublishDate, int copycount, String isbn, String category, String auther, String title, String profileImagepath) {

        this.auther = auther;
        this.category = category;
        this.isbn = isbn;
        this.language = language;
        this.PublishDate = PublishDate;
        this.Pagecount = Pagecount;
        this.copycount = copycount;
        this.publisher = publisher;
        this.title = title;
        this.profileImagepath = profileImagepath;
    }

    public Book(int id, String title, String auther, String category, String isbn, String PublishDate, int Pagecount, int copycount, String publisher, String language, String profileImagepath) {
        this.id = id;
        this.title = title;
        this.auther = auther;
        this.category = category;
        this.isbn = isbn;
        this.PublishDate = PublishDate;
        this.Pagecount = Pagecount;
        this.copycount = copycount;
        this.publisher = publisher;
        this.language = language;
        this.profileImagepath = profileImagepath;

    }

}
