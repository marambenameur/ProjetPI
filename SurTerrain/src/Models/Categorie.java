/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author maram
 */
public class Categorie {
    
    int id ;
    String type ;

    public Categorie() {
    }

    public Categorie(int id) {
        this.id = id;
    }

    public Categorie(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Categorie(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
    
    
}
