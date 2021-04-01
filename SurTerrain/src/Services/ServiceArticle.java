/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.DataSource;
import models.Article;
import models.Entete_facture;
import Utils.DataSource;
import java.awt.Image;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ArwaBj
 */
public class ServiceArticle  {
    Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(Article t) {
        try {
            String requete = "INSERT INTO article (libelle ,categorie ,image_article, prix,qt_article,ref) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getCategorie());
            pst.setString(3, t.getImage_article());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getQt_article());
            pst.setInt(6, t.getRef());
          System.out.println(t.getRef()+"/"+t.getCategorie()+"/"+t.getImage_article()+"/"+t.getPrix()+"/"+t.getQt_article()+"/");

            pst.executeUpdate();
            System.out.println("Article ajoutée !");

        } catch (SQLException ex) {
            System.err.println("Echec d'ajout");
        }
    }

    public void supprimer(Article t) {
 try {
            String requete = "DELETE FROM article WHERE ref=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getRef());
                      System.out.println(t.getRef()+"/"+t.getCategorie()+"/"+t.getImage_article()+"/"+t.getPrix()+"/"+t.getQt_article()+"/");

            pst.executeUpdate();
            System.out.println("Article supprimée !");

        } catch (SQLException ex) {
            System.err.println("Echec de suppression");
        }
    }
     

    public void modifier(Article t) {
try {
           String requete = "UPDATE ARTICLE SET LIBELLE=?,CATEGORIE=? , IMAGE_ARTICLE=? , PRIX=? ,QT_ARTICLE=? WHERE ref=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getRef());
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getCategorie());
            pst.setString(3, t.getImage_article());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getQt_article());
            pst.executeUpdate();
            System.out.println("Article modifiée !");
           
        } catch (SQLException ex) {
            System.err.println("Echec de modification");
        }
    }

    public List<Article> afficher() {
               List<Article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString("categorie"),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println("Aucun Article existant");
        }

        return list;
    }
       public ObservableList<Article> trier_article(){
 ObservableList<Article> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM article order by prix";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article"),rs.getInt("ref")));
            }
            
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
       }
        public List<Article> trier_articlemr(){
     
           List<Article> list = new ArrayList<>();

        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select * from article");
            while (rs.next()) {
                int id_article = rs.getInt("id_article");
                String libelle =rs.getString("libelle");
                String categorie =rs.getString("categorie");
                String image_article =rs.getString("image_article");
                int prix =rs.getInt("prix");
                int qt_article =rs.getInt("qt_article");
                int ref =rs.getInt("ref");                
               Article t = new Article(id_article, libelle, categorie,image_article , prix, qt_article,ref);
             
                 list.add(t);
                  list.stream().sorted(Comparator.comparing((Article) -> Article.getPrix()));
                 
            }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list; 
        } 

        public ObservableList<Article> rechercher(String libelle ){
            
 ObservableList<Article> list = FXCollections.observableArrayList();           
 try {
               
            String requete = "SELECT * FROM article WHERE libelle = '"+libelle+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article"),rs.getInt("ref")));
            }
             
            System.out.println("Article trouver !"+list.get(0).getLibelle());
              
        } catch (SQLException ex) {
            System.err.println("Article introuvable");
        }
             return list;
    }
        
    
            public List<Article> article_en_rupture(){
             ArrayList<Article> list= new ArrayList<>();
            try {
               
            String requete = "SELECT * FROM article WHERE qt_article < 5 ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article"),rs.getInt("ref")));
            }
             
            System.out.println("Article en rupture de stock !");
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
             return list;
            }

    public ObservableList<Article> getArticlesList(){
         
                   ObservableList<Article> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM article";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString("categorie"),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
           
        }
        for(Article A : list)
            System.out.println(A.getRef()+"/"+A.getLibelle()+"/"+A.getCategorie()+"/"+A.getQt_article()+"/"+A.getPrix()+"/"+A.getImage_article()+"/");


        return list; 
        } 

  
    public ObservableList<Article> rechercher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
       public ObservableList<Article> consulter(Integer ref ){
            
 ObservableList<Article> list = FXCollections.observableArrayList();           
 try {
               
            String requete = "SELECT * FROM article WHERE libelle = '"+ref+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article"),rs.getInt("ref")));
            }
             
            System.out.println("Article trouver !"+list.get(0).getRef());
              
        } catch (SQLException ex) {
            System.err.println("Article introuvable");
        }
             return list;
    }

    public ObservableList<Article> consulter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   



   


   
   

    

        }

 
    
    

