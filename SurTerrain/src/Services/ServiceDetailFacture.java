/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Utils.DataSource;
import models.Article;
import models.Detail_facture;
import models.Entete_facture;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author ArwaBj
 */
public class ServiceDetailFacture  {
 Connection cnx = DataSource.getInstance().getCnx();
    private boolean id_article;

    public void ajouter(Detail_facture t) {
        try {
            
           
            String requete = "INSERT INTO detail_facture (libelle,num_piece ,qt,type,ref_article,ref_facture) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
          
            pst.setString(1, t.getLibelle());
            pst.setInt(2, t.getNum_piece());
            pst.setInt(3, t.getQt());
           pst.setString(4, t.getType());
           pst.setInt(5, t.getRef_article());
            pst.setInt(6, t.getRef_facture());
        System.out.println(t.getRef_facture()+"/"+t.getRef_article()+"/"+t.getLibelle()+"/"+t.getNum_piece()+"/"+t.getQt()+"/"+t.getType()+"/");

            pst.executeUpdate();
            System.out.println("Article ajoutée dans la facture !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Detail_facture t) {
        try {
            String requete = "DELETE FROM detail_facture WHERE  ref_article=? AND ref_facture=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getRef_article());
            pst.setInt(2, t.getRef_facture());
                   

            pst.executeUpdate();
            System.out.println("Article supprimée de facture !");

        } catch (SQLException ex) {
            System.err.println("Echec de suppression");
        }
    }
   
    public void modifierQt(int qt,int ref_facture,int ref_article) {
        try {
            String requete = "UPDATE detail_facture SET qt=? WHERE ref_facture=? and ref_article=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            
        
            pst.setInt(1, getQt());
           
            pst.setInt(2, getRef_facture());
             pst.setInt(3, getRef_article());
            pst.executeUpdate();
            System.out.println("Aricle  modifiée dans la facture !");
           
        } catch (SQLException ex) {
            System.err.println("Echec de modification");
        }
 
    }
    public void modifier(Detail_facture t) {
        try {
            String requete = "UPDATE detail_facture SET ref_facture=?,libelle=?,qt=?,type=? WHERE ref_facture=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getRef_article());
            pst.setString(2, t.getLibelle());
        
            pst.setInt(3, t.getQt());
            pst.setString(4, t.getType());
            pst.setInt(5, t.getRef_facture());

            pst.executeUpdate();
            System.out.println("Aricle  modifiée dans la facture !");
           
        } catch (SQLException ex) {
            System.err.println("Echec de modification");
        }
 
    }
    public ObservableList<Detail_facture> getArticleList(int ref_facture){
         
                   ObservableList<Detail_facture> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM detail_facture where ref_facture='"+ref_facture+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Detail_facture(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
           
        }
        for(Detail_facture E : list)
            System.out.println(E.getRef_article()+"/"+E.getLibelle()+"/"+E.getQt()+"/");


        return list; 
        } 
    public List<Detail_facture> afficher() {
List<Detail_facture> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM detail_facture";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Detail_facture(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println("Aucun article existant");
        }

        return list;
    }

    public ObservableList<Detail_facture> getArticleList(Detail_facture detail_facture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getRef_article() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getQt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getLibelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getRef_facture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifierQt(Detail_facture detail_facture) {
    }

    

 



   

  

 

  

}
    

 

   
   
    
    