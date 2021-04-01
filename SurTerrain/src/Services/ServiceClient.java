/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import Models.Admin;
import Models.Client;
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
public class ServiceClient implements IServices<Client> {
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
            pst.setString(6, a.getMdp());
            
            
            pst.executeUpdate();
            System.out.println("client ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

     @Override
 public void supprimer(Client t) {
     try {
            String requete = "DELETE FROM client WHERE idC=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("client supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    @Override
      public void modifier(Client t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE client SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', address= '"+t.getAddress()+"', numTelC= '"+t.getNumTelC()+"', email= '"+t.getEmail()+"', mdp= '"+t.getMdp()+"' WHERE idC='"+t.getId()+"'";
        stm.executeUpdate(requete);
        System.out.println("Client modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
}
      }

            public void modifier2(Client t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE client SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', address= '"+t.getAddress()+"', numTelC= '"+t.getNumTelC()+"', email= '"+t.getEmail()+"' WHERE idC='"+t.getId()+"'";
        stm.executeUpdate(requete);
        System.out.println("Client modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
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
   public ObservableList<Client> lister(){
           ObservableList<Client> list1 =FXCollections.observableArrayList();
           String requete="SELECT * FROM client";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                    list1.add(new Client( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
                 
                     }
           
                }  
            catch (SQLException ex) {
            
            } 
        
     return list1;
    }

       public void UpdatePass(String pass) {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE client SET mdp= '"+pass+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
        public boolean findByEmail(String email) {
            
        String sql = "Select email from client where email='" + email + "'";
       
         try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
             if (rs.next()) {
                 return true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;

    }
                   public boolean findByPass(String Pass) {
            
        String sql = "Select mdp from client where mdp='" + Pass + "'";
       
         try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
             if (rs.next()) {
                 return true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;

         }

               public List<Client> rechercher(String charac) {
        String requete = "select * from client where  nom LIKE '%"+charac+"%' or prenom LIKE '%"+charac+"%' or address LIKE '%"+charac+"%' or email LIKE '%"+charac+"%'";
        List<Client> listClient = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Client result = new Client();
                result.setNom(rst.getString("nom"));
                result.setPrenom(rst.getString("adresse"));
                result.setAddress(rst.getString("address"));
                result.setEmail(rst.getString("email"));
                listClient.add(result);
            }
        } catch (SQLException ex) {
            System.out.println("aucun Client disponible!");
        }
        return listClient;
    }
        public boolean findByEmailPassword(String email , String Pass) {
            
        String sql ="Select email,mdp from client where email= '"+email+"' and mdp = '"+Pass+"'";
       
         try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
             if (rs.next()) {
                 return true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;

    }
      public void UpdatePass(String pass,String email) {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE client SET mdp= '"+pass+"' where email= '"+email+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
   
//    @Override
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
