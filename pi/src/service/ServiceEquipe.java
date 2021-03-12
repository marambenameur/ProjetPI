/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;


import entity.Equipe;

import datasource.DataSource;
import entity.Demande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABS1
 */
public class ServiceEquipe implements IService<Equipe>{
    
    
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
     public void ajouter(Equipe e) {
        try {
            String requete = "INSERT INTO equipe (nom,nombre) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
          
            pst.setString(1,e.getNom());
            pst.setInt(2,e.getNombre());
            pst.executeUpdate();
            System.out.println("equipe ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @Override
     public void supprimer(Equipe e) {
        try {
            String requete = "DELETE FROM equipe WHERE idequipe=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,e.getIdequipe());
            pst.executeUpdate();
            System.out.println("equipe supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
     @Override
       public List<Equipe> afficher() {
        List<Equipe> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM equipe";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Equipe(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
       @Override
        public void modifier(Equipe e)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE equipe SET nom= '"+e.getNom()+"', nombre= '"+e.getNombre()+"' WHERE idequipe='"+e.getIdequipe()+"'";
                stm.executeUpdate(requete);
        System.out.println("equipe modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceEquipe.class.getName()).log(Level.SEVERE, null, ex);
}
      }
    public List<Equipe> readAllEmployeessSortedByDate() {

        List<Equipe> e = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select idequipe,nombre,nom from equipe order by nombre ");
            while (rs.next()) {
                int idequipe = rs.getInt("idequipe");
                String nom = rs.getString("nom");
                 int nombre = rs.getInt("nombre");
                
                
                
                Equipe e1 = new Equipe(idequipe,nom,nombre);
                e.add(e1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e ;
    }
    public Equipe getId(int idequipe) throws SQLException {
        Equipe e = null;
        String req = "SELECT * FROM equipe WHERE nombre= '" + idequipe + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            e= new Equipe(rs.getInt("idequipe"), rs.getString("nom"),rs.getInt("nombre"));
        }
        return e;}
    
    
}
