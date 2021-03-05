/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.club;
import com.esprit.models.competition;
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
public class Servicecompetition {
    
     Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(competition com) {
        try {
            String requete = "INSERT INTO competition (nom_competition, id_club) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, com.getNom_competition());
            pst.setInt(2, com.getId_club());
            pst.executeUpdate();
            System.out.println("Club ajoutée au compétition!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public List<competition> afficher() {
        List<competition> list = new ArrayList<>();

        try {
            String requete = "SELECT distinct id_competition, club.id_club, nom_competition, nom_club FROM competition,club where nom_competition like 'competition1' and id_competition=1";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new competition(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
