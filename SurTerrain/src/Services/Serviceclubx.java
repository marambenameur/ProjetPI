/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Joueurs;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamidou
 */
public class Serviceclubx {

    Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<Joueurs> listjoueurs(int x) throws SQLException {
        ObservableList<Joueurs> listj = FXCollections.observableArrayList();

        String req = "select id,nom,prenom,age,id_club,email from joueurs where id_club = '" + x + "'";

        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            listj.add(new Joueurs(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"), rs.getInt("id_club"),rs.getString("email")));

        }

        return listj;
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

        return (x);
    }
}
