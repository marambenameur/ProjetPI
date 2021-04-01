/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Clubcomp;
import Models.Joueurs;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamidou
 */
public class ServiceClubComp {

    Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<Clubcomp> listnomclub(int x) throws SQLException {
        ObservableList<Clubcomp> listclub = FXCollections.observableArrayList();

        String req = "select nom_club from clubcomp where id_competition = '" + x + "'";

        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            listclub.add(new Clubcomp(rs.getString("nom_club")));
        }
       
        return listclub;
    }

    public String getnomcompetition(int id) {
        String x = null;
        try {

            String Requete = "SELECT nom_competition FROM competition where id_competition='" + id + "'";
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
    public void ajout(Clubcomp clcom) {
        try {
            String req = "insert into clubcomp (id_competition,id_club,nom_club) values(?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, clcom.getId_competition());
            pst.setInt(2, clcom.getId_club());
            pst.setString(3, clcom.getNom_club());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("err ajout");
            System.out.println(ex.getMessage());
        }
        
            }
}
