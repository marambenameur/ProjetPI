/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Admin;

import DataSource.DataSource;
import Entity.Adherant;
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
public class ServiceAdmin implements IServices2<Admin> {
    ObservableList<Admin> oblist = FXCollections.observableArrayList();
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
   public boolean supprimer(int id) {
        String sql = "delete from admin where id= ?";

        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            System.out.println("admin deleted");
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            return false;
        }   
        
    }

    
    @Override
   public void modifier(String nom, String prenom, String username, String email ,String mdp,int id) {
       
         
        String sql ="UPDATE admin SET nom=? , prenom=? , username=? ,email=? , mdp=? WHERE id=? ";
          try {
            PreparedStatement pst = cnx.prepareCall(sql);
            pst.setString(1,nom);
            pst.setString(2,prenom);
            pst.setString(3,username);
            pst.setString(4,email);
            pst.setString(5,mdp);
            pst.setInt(6, id);
            
            pst.executeUpdate();
            System.out.println("admin modifié");
            
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
           System.err.println(ex.getMessage());
            
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
   
    @Override
    public Admin finfById(int id)
{
     String sql = "Select * from admin where id='" + id + "'";
        Admin e = null;
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                e= new Admin( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }
            return e;

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
            return null;
        }
}
    @Override
            public ObservableList<Admin> findByName(String name) {
            
        String sql = "Select* from admin where nom='" + name + "'";
        List<Admin> list = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                oblist.add(new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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
    @Override
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
