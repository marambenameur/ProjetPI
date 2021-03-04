/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Article;
import com.esprit.utils.DataSource;
import java.awt.Image;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArwaBj
 */
public class ServiceArticle implements IServiceArticle<Article> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Article t) {
        try {
            String requete = "INSERT INTO article (libelle ,categorie ,image_article, prix,qt_article) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getCategorie());
            pst.setString(3, t.getImage_article());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getQt_article());
            pst.executeUpdate();
            System.out.println("Article ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Article t) {
 try {
            String requete = "DELETE FROM article WHERE id_article=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_article());
            pst.executeUpdate();
            System.out.println("Article supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Article t) {
try {
           String requete = "UPDATE ARTICLE SET LIBELLE=?,CATEGORIE=? , IMAGE_ARTICLE=? , PRIX=? ,QT_ARTICLE=? WHERE ID_ARTICLE=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getId_article());
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getCategorie());
            pst.setString(3, t.getImage_article());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getQt_article());
            pst.executeUpdate();
            System.out.println("Article modifiée !");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Article> afficher() {
               List<Article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString("categorie"),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    @Override
        public List<Article> trier_article(){
                     List<Article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article order by prix ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString("categorie"),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list; 
        } 
    @Override
        public List<Article> rechercher(String libelle ){
            
         ArrayList<Article> list= new ArrayList<>();
            try {
               
            String requete = "SELECT * FROM article WHERE libelle = '"+libelle+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article")));
            }
             
            System.out.println("Article trouver !"+list.get(0).getLibelle());
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
             return list;
    }
        
    @Override
            public List<Article> article_en_rupture(){
             ArrayList<Article> list= new ArrayList<>();
            try {
               
            String requete = "SELECT * FROM article WHERE qt_article < 5 ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Article(rs.getInt(1), rs.getString("libelle"), rs.getString("categorie"),rs.getString("image_article"),rs.getInt("prix"),rs.getInt("qt_article")));
            }
             
            System.out.println("Article en rupture de stock !");
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
             return list;
            }
              //             String requete = "SELECT * FROM article,detail_facture  "             
//                    + "where article.id_article = detail_facture.id_article AND detail_facture.type= 'vente'

 

 

    @Override
    public void file(File file, Article article) {
try{
           String requete;
     requete = "INSERT INTO article (libelle,categorie,image_article,prix,qt_article) VALUES ('" + article.getLibelle()+ "','" + article.getCategorie()+ "','" + file.getPath()+ "','" + article.getPrix()+"','"+ article.getQt_article()+ "')";
           Statement st = cnx.createStatement();
            st.executeUpdate(requete);
           System.out.println("article ajoutée !");

      } catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }     }

    

   
   

    

        }

 
    
    

