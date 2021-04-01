/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Facture;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;

/**
 *
 * @author OussKh
 */
public class ServiceFacture implements IServices <Facture> {

   
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Facture f) {
        try {
            String requete = "INSERT INTO facture (idFacture,idRes,sommeFacture,datePaiement) VALUES('" + f.getIdFacture()+ "','" + f.getIdRes()+ "','" + f.getSommeFacture()+"','" +f.getDatePayementFacture()+ "')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("facture affectée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } 

    @Override
    public void supprimer(Facture f) {
        try {
            String requete = "DELETE FROM facture WHERE idFacture="+ f.getIdFacture();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Facture supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


@Override
    public void modifier(Facture f)  {
        try{
        Statement stm = cnx.createStatement();
        
        String query = "UPDATE facture SET idRes= '"+f.getIdRes()+"', sommeFacture= '"+f.getSommeFacture()+"',datePaiement= '"+f.getSommeFacture()+"' WHERE idFacture='"+f.getIdFacture()+"'";
        stm.executeUpdate(query);
        System.out.println("Facture modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
}
      }

    
    @Override
    public List<Facture> afficher() {
        List<Facture> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Facture(rs.getInt(3),rs.getFloat(4),rs.getDate(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
//  @Override
    public List<Facture> rechercher(String charac) {
        String requete = "select * from facture where  idRes LIKE '%"+charac+"%' or sommeFacture LIKE '%"+charac+"%' or datePaiement LIKE '%"+charac+"%'";
        List<Facture> listFacture = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Facture result = new Facture();
                result.setIdFacture(rst.getInt("idFacture"));
                result.setIdRes(rst.getInt("idRes"));
                result.setSommeFacture(rst.getFloat("sommeFacture"));
                result.setDatePayementFacture(rst.getDate("datePaiement"));
                listFacture.add(result);
            }
        } catch (SQLException ex) {
            System.out.println("aucune Facture disponible!");
        }
        return listFacture;
    }
    
     public List<Facture> trierTerrainParSommeF() {

        List<Facture> listFactureTri = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select * from facture");
            while (rs.next()) {
                int idF = rs.getInt("idFacture");
                int idR =rs.getInt("idR");
                float sommeF=rs.getFloat("sommeFacture");
                Date dateP = rs.getDate("datePaiement");
                
               Facture t = new Facture(idR, sommeF, dateP);
                 listFactureTri.add(t);
                  listFactureTri.stream()
                              .sorted(Comparator.comparing((Facture) -> {
                              return Facture.getDatePayementFacture();
                        }));
            }
        } catch (SQLException ex) {
        }
        return  listFactureTri;
    }

    
}
