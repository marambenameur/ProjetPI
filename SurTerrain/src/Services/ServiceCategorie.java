/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Categorie;
import Models.Event;
import Utils.DataSource;
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
 * @author maram
 */
public class ServiceCategorie  {
 Connection connexion = DataSource.getInstance().getCnx();
    private Statement stm;
    //private PreparedStatement pst;
    private ResultSet rs;
         private PreparedStatement pst;




    public void addCategorie(String type) {
        System.out.println(type);
          String req="INSERT INTO categorie (type) VALUES (?)" ;
        
       
     try{
         PreparedStatement ps1 = connexion.prepareStatement(req);
     
          
            ps1.setString(1,type);
         
            
          
            ps1.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null , ex);
        } 
             }
     public List<Categorie> readAll()  {
        List<Categorie> evs =new ArrayList<>();
        try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from categorie v  ");
            while(rest.next()){
                Categorie ev = new Categorie();
                ev.setId(rest.getInt("id_categorie"));

                              ev.setType(rest.getString("type"));
                                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
         return evs;
    }
    
    
    
    
    
    
    

    public void delete(Categorie c) {
               try { 
            String delete = "DELETE FROM categorie WHERE id_categorie = ? ";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        int id_categorie = c.getId();
        
        st2.setInt(1,id_categorie);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void UpdateCategorie(Categorie c) throws SQLException {

String sql = "UPDATE categorie SET type=? WHERE id_categorie=?";

PreparedStatement statement = connexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{
           
       

            statement.setString(1 ,c.getType(
            ));
       
 statement.setInt(2,c.getId());
        
          
   statement.executeUpdate();
    System.out.println("Updated");
    
    }
catch (Exception ex) {
                Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
   
       
         

    
    
}}
    public List<Categorie> readNom()
    {
        List<Categorie> evs =new ArrayList<>();
        try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select type from categorie  ");
            while(rest.next()){
                Categorie ev = new Categorie();
         

                              ev.setType(rest.getString("type"));
             
                                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
 return evs;
}
    public ArrayList<Categorie> getAllCategorie() throws SQLException {
       ArrayList<Categorie> categories = new ArrayList<>();
        
        String req = "select * from categorie";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Categorie c = new Categorie(result.getInt("id_categorie"),result.getString("type"));
            categories.add(c);
        }
        
        return categories;
    }
    
    public Categorie getCategorieById(int id) throws SQLException {
       Categorie categorie = new Categorie();
        
        String req = "select * from categorie where id_categorie="+id+";";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            categorie = new Categorie(result.getInt("id_categorie"), result.getString("type"));
            
        }
        
        return categorie;
    }
        public Categorie getCategorieByType(String type) throws SQLException {
       Categorie categorie = new Categorie();
        
        String req = "select * from categorie where type='"+type+"' ";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            categorie = new Categorie(result.getInt("id_categorie"), result.getString("type"));
            
        }
        
        return categorie;
    }
    
    
    
}


    

