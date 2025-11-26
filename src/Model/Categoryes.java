/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
public class Categoryes {
    int id ;
    String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Categoryes(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public Categoryes(String category) {
        this.category = category;
    }
    
}
