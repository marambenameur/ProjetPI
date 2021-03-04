/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;
import datasource.DataSource;
import entity.Demande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
        
/**
 *
 * @author ABS1
 */
public class ServiceDemande implements IService<Demande>  {
    
    Connection cnx = DataSource.getInstance().getCnx();

  
    public void ajouter(Demande d) {
        try {
            String requete = "INSERT INTO demande (date,idterrain,idequipe) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, d.getDate());
            pst.setInt(2, d.getIdterrain());
            pst.setInt(3, d.getIdequipe());
            pst.executeUpdate();
            System.out.println("demande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        
  
         public void supprimer(Demande d) {
        try {
            String requete = "DELETE FROM demande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.executeUpdate();
            System.out.println("Personne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        
          public List<Demande> afficher() {
        List<Demande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        @Override
    public void modifier(Demande d)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE demande SET date= '"+d.getDate()+"', idterrain= '"+d.getIdterrain()+"', idequipe= '"+d.getIdequipe()+"' WHERE id='"+d.getId()+"'";
                stm.executeUpdate(requete);
        System.out.println("demande modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
}
      }
       
    public List<Demande> readAllEmployeessSortedByDate() {

        List<Demande> d = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select id,idterrain,idequipe from demande order by date ");
            while (rs.next()) {
                int id = rs.getInt("id");
                int idterrain = rs.getInt("idterrain");
                 int idequipe = rs.getInt("idequipe");
                
                
                
                Demande d1 = new Demande(id,idterrain,idequipe);
                d.add(d1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return d ;
    }
    
    public Demande getId(int id) throws SQLException {
        Demande d = null;
        String req = "SELECT * FROM demande WHERE id= '" + id + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            d= new Demande(rs.getString("date"), rs.getInt("idterrain"),rs.getInt("idequipe"));
        }
        return d;  
    }
}
    
    

