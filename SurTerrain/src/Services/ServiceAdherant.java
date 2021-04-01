/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import Models.Adherant;
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
public class ServiceAdherant implements IServices <Adherant> {
    
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
            pst.setString(8, a.getMdp());
            pst.executeUpdate();
            System.out.println("Adherant ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
 public void supprimer(Adherant t) {
         try {
            String requete = "DELETE FROM adherant WHERE idA=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("adherant supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
      public void modifier(Adherant t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE adherant SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', cin= '"+t.getCin()+"', address= '"+t.getAddress()+"', nomTerain= '"+t.getNomTerrain()+"', email= '"+t.getEmail()+"', numTel= '"+t.getNumTel()+"', mdp= '"+t.getMdp()+"' WHERE idA='"+t.getId()+"'";
        stm.executeUpdate(requete);
        System.out.println("Adherant modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
}
      }
            public void modifier2(Adherant t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE adherant SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', address= '"+t.getAddress()+"', nomTerain= '"+t.getNomTerrain()+"', email= '"+t.getEmail()+"', numTel= '"+t.getNumTel()+"' WHERE idA='"+t.getId()+"'";
        stm.executeUpdate(requete);
        System.out.println("Adherant modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
}
      }

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
   

      public ObservableList<Adherant> lister(){
           ObservableList<Adherant> list =FXCollections.observableArrayList();
           String requete="SELECT * FROM adherant";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                      list.add(new Adherant( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9)));
                     }
           
                }  
            catch (SQLException ex) {
            
            }  
     return list;
    }

            public void UpdatePass(String pass ,String email) {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE adherant SET mdp= '"+pass+"' where email= '"+email+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
   
 
  
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
//         public void UpdatePass(String pass,String email) {
//        try{
//        Statement stm = cnx.createStatement();
//        String requete = "UPDATE adherant SET mdp= '"+pass+"' where email= '"+email+"'";
//        stm.executeUpdate(requete);
//        System.out.println("Mot de passe  modifiée !");
//    } catch (SQLException ex) {
//           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
//       
//   }
//   }
              public boolean findByPass(String Pass) {
            
        String sql = "Select mdp from adherant where mdp='" + Pass + "'";
       
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
         public boolean findByEmail(String email) {
            
        String sql = "Select email from adherant where email='" + email + "'";
       
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
                public boolean findByEmailPassword(String email , String Pass) {
            
        String sql ="Select email,mdp from adherant where email= '"+email+"' and mdp = '"+Pass+"'";
       
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
   
//    @Override
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

