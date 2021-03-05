/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Joueurs;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */
public class ServiceJoueurs implements IService<Joueurs> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Joueurs t) {
        try {
            String requete = "INSERT INTO joueurs (nom,prenom,age,club_name) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setInt(3, t.getAge());
            pst.setString(4,t.getclubname());
            pst.executeUpdate();
            System.out.println("Joueur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Joueurs t) {
        try {
            String requete = "DELETE FROM joueurs WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Joueur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(String nom, String prenom, int age, String club_name,int id) {
        try {
            String requete = "UPDATE joueurs SET nom=?,prenom=?,age=?,club_name=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, id);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setInt(3, age);
            pst.setString(4, club_name);
            pst.executeUpdate();
            System.out.println("Joueur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Joueurs> afficher() {
        List<Joueurs> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM joueurs";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Joueurs(rs.getInt(1), rs.getString(2), rs.getString("prenom"), rs.getInt("age"), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void modifier(Joueurs t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
