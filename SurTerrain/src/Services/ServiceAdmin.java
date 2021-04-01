/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Admin;

import Utils.DataSource;
import Models.Adherant;
import static java.lang.Integer.parseInt;
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
import javax.swing.JOptionPane;


/**
 *
 * @author SALAH
 */
public class ServiceAdmin implements IServices<Admin> {
     private PreparedStatement pst;
    private ResultSet ls;
    private ResultSet rs;
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void ajouter(Admin a) {
         try {
            String requete = "INSERT INTO admin (nom,prenom,username,email,mdp) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getNom());
            pst.setString(2, a.getPrenom());
            pst.setString(3, a.getUsername());
            pst.setString(4, a.getEmail());
            pst.setString(5, a.getMdp());
            
            
            
            pst.executeUpdate();
            System.out.println("admin ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

  
    @Override
 public void supprimer(Admin t) {
        try {
            String requete = "DELETE FROM admin WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("admin supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
      public void modifier(Admin t)  {
        String requete = "UPDATE admin SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', username= '"+t.getUsername()+"', email= '"+t.getEmail()+"', mdp= '"+t.getMdp()+"' WHERE id='"+t.getId()+"'";
        try{
         PreparedStatement pst = cnx.prepareStatement(requete);
         pst.executeUpdate(requete);
        System.out.println("Admin modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
}
      }
            public void modifier2(Admin t)  {
        String requete = "UPDATE admin SET nom= '"+t.getNom()+"', prenom= '"+t.getPrenom()+"', username= '"+t.getUsername()+"', email= '"+t.getEmail()+"' WHERE id='"+t.getId()+"'";
        try{
         PreparedStatement pst = cnx.prepareStatement(requete);
         pst.executeUpdate(requete);
        System.out.println("Admin modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
    
     @Override
   public List afficher() {
       
            List <Admin> list = new ArrayList <>();

        try {
            String requete = "SELECT * FROM Admin";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Admin( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }

       } catch (SQLException ex) {
         System.err.println(ex.getMessage());
        }

        return list;
        
    }   
      public ObservableList<Admin> lister(){
           ObservableList<Admin> list =FXCollections.observableArrayList();
           String requete="SELECT * FROM admin";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                     list.add(new Admin( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                 
                     }
           
                }  
            catch (SQLException ex) {
            
            }  
     return list;
    }

     public List<Admin> rechercher(String charac) {
        String requete = "select * from admin where  nom LIKE '%"+charac+"%' or prenom LIKE '%"+charac+"%' or username LIKE '%"+charac+"%' or email LIKE '%"+charac+"%'";
        List<Admin> listAdmin = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Admin t = new Admin();
                t.setNom(rst.getString("nom"));
                t.setPrenom(rst.getString("prenom"));
                t.setUsername(rst.getString("username"));
                t.setEmail(rst.getString("email"));
                listAdmin.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("aucun admin disponible!");
        }
        return listAdmin;
    }
         public boolean findByEmail(String email) {
            
        String sql = "Select email from admin where email='" + email + "'";
       
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
            
        String sql = "Select mdp from admin where mdp='" + Pass + "'";
       
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
         
        public boolean findByUsername(String user) {
            
        String sql = "Select username from admin where username='" + user+ "'";
       
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
            
        String sql ="Select * from admin where email= '"+email+"' and mdp = '"+Pass+"'";
       
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
        String requete = "UPDATE admin SET mdp= '"+pass+"' where email= '"+email+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
     public void UpdatePass(String pass) {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE  admin SET mdp= '"+pass+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceAdherant.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
   public int nombre()
    {int nb=0;
        try {
        String req="select COUNT(id) from admin";
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
  
      public List<Admin> readAllAdminSortedByUsername() {

        List<Admin> pr = new ArrayList<>();
        try {
            String req ="select * from admin order by username ";
            Statement stm= cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {
            pr.add(new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pr;
    }
   
    
    
    
}
