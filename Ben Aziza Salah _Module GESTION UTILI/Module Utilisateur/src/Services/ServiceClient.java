/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataSource.DataSource;
import Entity.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SALAH
 */
public class ServiceClient implements IServices1<Client> {
     Connection cnx = DataSource.getInstance().getCnx();
     private ResultSet rs;
     ObservableList<Client> oblist = FXCollections.observableArrayList();
      

     @Override
    public void ajouter(Client a) {
         try {
            String requete = "INSERT INTO client (nom,prenom,address,numTelC,email,mdp) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getNom());
            pst.setString(2, a.getPrenom());
            pst.setString(3, a.getAddress());
            pst.setInt(4, a.getNumTelC());
            pst.setString(5, a.getEmail());
            pst.setString(6, a.getMdpC());
            
            
            pst.executeUpdate();
            System.out.println("client ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

  
    @Override
   public boolean supprimer(int id) {
        String sql = "delete from Client where idC= ?";

        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            System.out.println("Client deleted");
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            return false;
        }   
        
    }

    
    @Override
   public void modifier(String nom, String prenom, String address,int numTelC, String email, String mdpC,int idC) {
       
         
        String sql ="UPDATE client SET nom=? , prenom=? , address=? ,  numTelC=? , email=? , mdp=? WHERE idC=? ";
          try {
            PreparedStatement pst = cnx.prepareCall(sql);
            pst.setString(1,nom);
            pst.setString(2,prenom);
            pst.setString(3,address);
            pst.setInt(4,numTelC);
            pst.setString(5,email);
            pst.setString(6,mdpC);
            pst.setInt(7, idC);
            
            pst.executeUpdate();
            System.out.println("client modifié");
            
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
           System.err.println(ex.getMessage());
            
        }  
         
        }

    
     @Override
   public List afficher() {
       
            List <Client> list = new ArrayList <>();

        try {
            String requete = "SELECT * FROM Client";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Client( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
            }

       } catch (SQLException ex) {
         System.err.println(ex.getMessage());
        }

        return list;
        
    }   
     @Override
    public Client finfById(int id)
{
     String sql = "Select * from client where idC='" + id + "'";
        Client e = null;
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                e= new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
            }
            return e;

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
            return null;
        }
}
    @Override
            public ObservableList<Client> findByName(String name) {
            
        String sql = "Select* from Client where nom='" + name + "'";
        List<Client> list = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
               oblist.add(new Client( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
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
        String req="select COUNT(idC) from Client";
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
        public List<Client> readAllClientSortedByUsername() {

        List<Client> pr = new ArrayList<>();
        try {
            String req ="select * from client order by nom ";
            Statement stm= cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {
           pr.add(new Client( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pr;
    }
    
    
    
}
