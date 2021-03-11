/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Article;
import com.esprit.models.Entete_facture;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArwaBj
 */
public class ServiceEnteteFacture implements IServiceArticle<Entete_facture>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Entete_facture t) {
         try {
            String requete1 = "INSERT INTO entete_facture (type ,date_exp ,tier,ref_facture) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete1);
            
       
            pst.setString(1, t.getType());
            pst.setDate(2, t.getDate_exp());
            pst.setInt(3, t.getTier());
            pst.setInt(4, t.getRef_facture());
            pst.executeUpdate();
            System.out.println("Facture ajoutée !");

        } catch (SQLException ex) {
            System.err.println("Echec d'ajout ");
        }
    }

    @Override
    public void supprimer(Entete_facture t) {
try {
            String requete = "DELETE FROM entete_facture WHERE ref_facture=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getRef_facture());
            pst.executeUpdate();
            System.out.println("Facture supprimée !");

        } catch (SQLException ex) {
            System.err.println("Facture innexistante");
        }
    }

    @Override
    public void modifier(Entete_facture t) {
        try {
                String requete = "UPDATE entete_facture SET type=?,date_exp=?,tier=? WHERE Ref_facture=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getRef_facture());
            pst.setString(1, t.getType());
            pst.setDate(2, t.getDate_exp());
            pst.setInt(3, t.getTier());
            
            pst.executeUpdate();
            System.out.println("Facture modifiée !");

        } catch (SQLException ex) {
            System.err.println("Facture innexistante");
        }
    }
    

    @Override
    public List<Entete_facture> afficher() {
  List<Entete_facture> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM entete_facture";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Entete_facture(rs.getInt(1), rs.getString(2), rs.getDate(3),rs.getInt(4),rs.getInt(5)));
            }

        } catch (SQLException ex) {
            System.err.println("auccune facture existe ");
        }

        return list;
    }
  
        public List<Entete_facture> trier_entetefacture(){
    List<Entete_facture> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM entete_facture order by Date_exp desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Entete_facture(rs.getInt(1), rs.getString(2), rs.getDate(3),rs.getInt(4),rs.getInt(5)));
            }
            
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
}

    
    public List<Entete_facture> rechercher(Date date_exp){
            ArrayList<Entete_facture> list= new ArrayList<>();
            try {
               
            String requete = "SELECT * FROM entete_facture WHERE date_exp = '"+date_exp+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Entete_facture(rs.getInt(1), rs.getString("type"), rs.getDate("date_exp"),rs.getInt("tier"),rs.getInt("ref_facture")));
            }
             
            System.out.println("Facture trouver !"+list.get(0).getDate_exp());


        } catch (SQLException ex) {
                            System.err.println(ex.getMessage());

        }
             return list;
    }

    
    }
    

