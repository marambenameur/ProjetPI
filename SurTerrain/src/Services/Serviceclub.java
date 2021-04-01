/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.club;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamidou
 */
public class Serviceclub {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(club c) {
        try {

            String requete = "INSERT INTO club (nom_club,nbr_joueurs) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if (c.getNbr_joueurs() <= 11) {
                pst.setString(1, c.getNom_club());
                pst.setInt(2, c.getNbr_joueurs());
                pst.executeUpdate();
                System.out.println("Club ajoutée !");
            } else {
                System.out.println("Club complet");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<club> listerc() throws SQLException {
        ObservableList<club> list = FXCollections.observableArrayList();
        String requete = "SELECT id_club,nom_club,nbr_joueurs FROM club";

        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new club(rs.getInt("id_club"), rs.getString("nom_club"), rs.getInt("nbr_joueurs")));
        }
        System.out.println("lister clubs");

        return list;
    }

    public ObservableList<String> listercj() {
        ObservableList<String> listc = FXCollections.observableArrayList();
        String requete = "SELECT nom_club FROM club";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                listc.add(rs.getString("nom_club"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listc;
    }
    public ObservableList<club> listrecherche(String x) throws SQLException {
        ObservableList<club> listclub = FXCollections.observableArrayList();

        String req = "select * from club where nom_club like '%"+x+"%'";

        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            listclub.add(new club(rs.getInt("id_club"), rs.getString("nom_club"), rs.getInt("nbr_joueurs")));

        }
        return listclub;
    }

    public String getnomclub(int id) {
        String x = null;
        try {

            String Requete = "SELECT nom_club FROM club where id_club='" + id + "'";
            PreparedStatement pst = cnx.prepareStatement(Requete);
            ResultSet rs = pst.executeQuery();
            rs.next();
            x = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println("erreur get nom ");
            System.out.println(ex.getMessage());
        }
        return x;
    }

    public void supprimer(club c) {
        try {
            String requete = "DELETE FROM club WHERE id_club= '" + c.getid_club() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.executeUpdate();
            System.out.println("Club supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(club c) {
        try {
            String requete = "UPDATE club SET nom_club='" + c.getNom_club() + "' ,nbr_joueurs= '" + c.getNbr_joueurs() + "' WHERE id_club= '" + c.getid_club() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
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

}
