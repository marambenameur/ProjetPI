/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import Entity.Adherant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author SALAH
 */
public class ServiceAdherant implements IService <Adherant> {
    
    ObservableList<Adherant> oblist = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getCnx();
    private ResultSet rs;

    @Override
    public void ajouter(Adherant a) {
         try {
            String requete = "INSERT INTO adherant (nom,prenom,cin,address,nomTerain,email,numTel,mdp) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getNom());
            pst.setString(2, a.getPrenom());
            pst.setInt(3, a.getCin());
            pst.setString(4, a.getAddress());
            pst.setString(5, a.getNomTerrain());
            pst.setString(6, a.getEmail());
            pst.setInt (7, a.getNumTel());
            pst.setString(8, a.getMdpAdh());
            pst.executeUpdate();
            System.out.println("Personne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

  
    @Override
   public boolean supprimer(int id) {
  
        String sql = "delete from adherant where idA= ?";

        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            System.out.println("adherant deleted");
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            return false;
        }   
        
    }

    
    @Override
    public void modifier(String nom,String prenom,int cin,String address,String nomTerain,String email ,int numTel,String mdp, int idA) {
        
        
        String sql = "UPDATE adherant SET nom=? , prenom=? , cin=? , address=? , nomTerain=? , email=? , numTel=? , mdp=? WHERE idA=? ";
          try {
            PreparedStatement pst = cnx.prepareCall(sql);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setInt(3, cin);
            pst.setString(4, address);
            pst.setString(5,nomTerain);
            pst.setString(6, email);
            pst.setInt(7, numTel);
            pst.setString(8, mdp);
            
            pst.setInt(9,idA);
            
            pst.executeUpdate();
            System.out.println("adherant modifié");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            
        }  
       
    }
      /*public void modifier(Adherant t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE terrain SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', cin= '"+t.getCin()+"', address= '"+t.getAddress()+"', nomTerain= '"+t.getNomTerrain()+"', email= '"+t.getEmail()+"', numTel= '"+t.getNumTel()+"', mdp= '"+t.getMdpAdh()+"' WHERE idTerrain='"+t.getIdA()+"'";
        stm.executeUpdate(requete);
        System.out.println("Adherant modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
}
      }*/

    @Override
   public List afficher() {
       
            List <Adherant> list = new ArrayList <>();

        try {
            String requete = "SELECT * FROM adherant";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Adherant( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9)));
            }

       } catch (SQLException ex) {
         System.err.println(ex.getMessage());
        }

        return list;
        
    }   
 
    @Override
   public Adherant finfById(int id)
{
     String sql = "Select * from adherant where idA='" + id + "'";
        Adherant e = null;
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                e= new Adherant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
            }
            return e;

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
            return null;
        }
}
    @Override
            public ObservableList<Adherant> findByName(String name) {
            
        String sql = "Select* from adherant where nom='" + name + "'";
        List<Adherant> list = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                oblist.add(new Adherant (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9)));
            }
            return oblist;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
   
    @Override
   public int nombre()
    {int nb=0;
        try {
        String req="select COUNT(idA) from adherant";
        Statement st=cnx.createStatement();
        rs=st.executeQuery(req);
     while(rs.next())
     {
      nb=rs.getInt(1); 
     } 
    }
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return nb;
}
    @Override
    public List<Adherant> readAllAdherantSortedByNom() {

        List<Adherant> pr = new ArrayList<>();
        try {
            String req ="select * from adherant order by nom ";
            Statement stm= cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {
            pr.add(new Adherant (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pr;
    }

       
    }

