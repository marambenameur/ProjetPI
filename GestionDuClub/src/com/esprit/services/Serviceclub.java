/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.club;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hamidou
 */
public class Serviceclub {
    
    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(club c) {
        try {
            String requete = "INSERT INTO club (id_club,nom_club,nbr_joueurs) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getid_club());
            pst.setString(2, c.getnom_club());
            pst.setInt(3, c.getnbr_joueurs());
            pst.executeUpdate();
            System.out.println("Club ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(club c) {
        try {
            String requete = "DELETE FROM club WHERE id_club=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getid_club());
            pst.executeUpdate();
            System.out.println("Club supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(int id_club, String nom_club, int nbr_joueurs) {
        try {
            String requete = "UPDATE club SET nom_club=?,nbr_joueurs=? WHERE id_club=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_club);
            pst.setString(2, nom_club);
            pst.setInt(3, nbr_joueurs);
            
            pst.executeUpdate();
            System.out.println("Club modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<club> afficher() {
        List<club> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM club";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new club(rs.getInt(1), rs.getString(2), rs.getInt("nbr_joueurs")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void modifier(club c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
