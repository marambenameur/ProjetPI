/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author ArwaBj
 */
public class Article {

    private int id_article;
    private String libelle;
    private String categorie;
    private String image_article;
    private int prix;
    private int qt_article;

    public Article(int id_article, String libelle, String categorie, String image_article, int prix,int qt_article) {
        this.id_article = id_article;
        this.libelle = libelle;
        this.categorie = categorie;
        this.image_article = image_article;
        this.prix = prix;
        this.qt_article = qt_article;

    }

    public Article(String libelle, String categorie, String image_article, int prix,int qt_article) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.image_article = image_article;
        this.prix = prix;
        this.qt_article = qt_article;

    }

    public void setImage_article(String image_article) {
        this.image_article = image_article;
    }

    public String getImage_article() {
        return image_article;
    }

    public int getId_article() {
        return id_article;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQt_article() {
        return qt_article;
    }

    public void setQt_article(int qt_article) {
        this.qt_article = qt_article;
    }

    @Override
    public String toString() {
        return "Article{" + "id_article=" + id_article + ", libelle=" + libelle + ", categorie=" + categorie + ", image_article=" + image_article + ", prix=" + prix + ", qt_article=" + qt_article + '}';
    }

    public String getPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
