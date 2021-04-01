/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Models.Categorie;
import Models.Event;
import Utils.DataSource;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
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
 * @author maram
 */

public class ServiceEvent implements IServices<Event> { 
 Connection cnx = DataSource.getInstance().getCnx();
    private Statement stm;
    //private PreparedStatement pst;
    private ResultSet rs;
         private PreparedStatement pst;

    public void Ajouter(String tfNom, Date dpDate_event, String taDescription, String tfLieu_event,Double tfprix,Categorie combo) throws SQLException, AWTException, MalformedURLException {

        String c=combo.getType();
        System.out.println(c); 
  
        PreparedStatement pt2 = cnx.prepareStatement("select id_categorie from categorie where type='"+combo+"'");
            ResultSet rs2 = pt2.executeQuery();
            while(rs2.next()){
               Integer  res =rs2.getInt(1);
                        System.out.println(res);

        String req="INSERT INTO event (categories_id,Nom,Date_event,Description,Lieu_event,Prix,etat) VALUES (?,?,?,?,?,?,?)";
           
             

         
        
     try{
         PreparedStatement ps1 = cnx.prepareStatement(req);
     
            ps1.setInt(1, res);
            ps1.setString(2, tfNom);
            ps1.setDate(3,dpDate_event);
     

            ps1.setString(4, taDescription);
            ps1.setString(5, tfLieu_event);
                        ps1.setDouble(6,tfprix);

                        ps1.setInt(7 ,0);

            
          
            ps1.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null , ex);
        } 
     Notification.sendNotification("Success", "evenement ajoutee avec success",TrayIcon.MessageType.INFO);
        }}
 
 
    
    
  
 



    
     
      
    public ObservableList<Event> lister(){
           ObservableList<Event> list1 =FXCollections.observableArrayList();
           String requete="SELECT id,categories_id,nom,description,lieu_event,date_event,prix FROM event";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                 
                     }
                System.out.println("lister event");
                }  
            catch (SQLException ex) {
            
            } 
//        for( Joueurs j : list1)
//        System.out.println(j.getId()+"/"+j.getNom()+"/"+j.getPrenom()+"/"+ j.getAge()+"/"+ j.getclub_name());
        
     return list1;
    } 
    
 
    /*public void Modifier(Event t) 
    {
       
          try {
              
              String sql = "UPDATE event SET categories_id=?,Nom=?,Date_event=?,Description=?,Lieu_event=?,Prix=? WHERE id=?";
              PreparedStatement statement = cnx.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
              try {
                  statement.setString(2, t.getNom());
                  statement.setDate(3, t.getDate_event());
                  statement.setString(4, t.getDescription());
                  statement.setString(5, t.getLieu_event());
                  statement.setDouble(6, t.getPrix());
                  statement.setInt(7, t.getId());
                  System.out.println(statement);
                  statement.executeUpdate();
                  System.out.println("update done");
              } catch (SQLException ex) {
                  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
              }
              
          } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
 public void update(Event t,Categorie combo) throws SQLException, MalformedURLException, AWTException {
            
           
        PreparedStatement pt2 = cnx.prepareStatement("select id_Categorie from Categorie where type='"+combo.getType()+"'");
            ResultSet rs2 = pt2.executeQuery();
            while(rs2.next()){
               Integer  res =rs2.getInt(1);
                        System.out.println(res);
           String sql ="UPDATE event SET categories_id=?,Nom=?,Date_event=?,Description=?,Lieu_event=?,Prix=? WHERE id=?"; 

PreparedStatement statement = cnx.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{      statement.setInt(1, t.getCategories_id().getId());
       statement.setString(2, t.getNom());
                  statement.setDate(3, t.getDate_event());
                  statement.setString(4, t.getDescription());
                  statement.setString(5, t.getLieu_event());
                  statement.setDouble(6, t.getPrix());
                  statement.setInt(7, t.getId());
                  System.out.println(statement);
                  statement.executeUpdate();
                  System.out.println("update done");

  
    
    
    }
catch (Exception ex) {
                Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
}
            }
            Notification.sendNotification("Success", "evenement modifiee avec success",TrayIcon.MessageType.INFO);
 }
   
    
    /**
     *
     * @param e
     * @throws SQLException
     */
    public void Supprimer(Event e) throws SQLException 
    { 
    

 try { 
            String delete = "DELETE FROM event WHERE id = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        int id = e.getId();
        
        st2.setInt(1,id);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
readAl();


    }
     public List<Event> readAl() throws SQLException  {
        List<Event> evs =new ArrayList<>();
       
        Statement stm = cnx.createStatement();
              ResultSet rest1= 
                    stm.executeQuery("select type from categorie ");
              while(rest1.next()){
                  String rs=rest1.getString("type");
              }
          try {  ResultSet rest= 
                    stm.executeQuery("select * from event   ");
            while(rest.next()){
                Event ev = new Event();
                ev.setId(rest.getInt("id"));
                    ev.setDate_event(rest.getDate("Date_event"));

                              ev.setDescription(rest.getString("Description"));
                ev.setLieu_event(rest.getString("Lieu_event"));
                                ev.setPrix(rest.getDouble("Prix"));

             Categorie c= new Categorie(rest.getInt("categories_id"));
           ev.setCategories_id(c);
                
       ev.setNom(rest.getString("Nom"));
               evs.add(ev);
                System.out.println(evs);
                 }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
              
         return evs;
    }
 
    
    public Event getEvent(String Nom) throws SQLException{
        Event e=new Event();
        try {
            String req="SELECT * FROM event where Nom='"+Nom+"'";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {
            e.setNom(rs.getString("Nom"));
            e.setDate_event(rs.getDate("Date_event"));
            e.setDescription(rs.getString("description"));
            e.setLieu_event(rs.getString("Lieu_event"));
                System.out.println(rs.getDouble("Prix"));
            e.setPrix(rs.getDouble("Prix"));
           
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    
 
    public List<Event> Afficher() throws SQLException {
        List<Event> l = new ArrayList<>();
         
         
      
        try {
            String req="SELECT * FROM event";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {

                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("Nom"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setDescription(rs.getString("description"));
                e.setLieu_event(rs.getString("Lieu_event"));
                                e.setPrix(rs.getDouble("Prix"));

                l.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    public void ValiderEvent(int id,int etat ) throws SQLException
    {
        String sql = "UPDATE event SET `etat`='"+etat+ "' WHERE id='"+id+"' ";
          PreparedStatement pst= cnx.prepareStatement(sql);
             try {
              
              pst.executeUpdate();
              System.out.println("update done");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
   
     public List<Event> trieEvent(String s) 
    {
        
        String requete = "SELECT * FROM event order by "+s;
        PreparedStatement pst;
            List<Event> list = new ArrayList<>();
            

        try {
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            Event e = new Event();
            e.setId(rs.getInt("id"));
            e.setNom(rs.getString("Nom"));
            e.setDate_event(rs.getDate("Date_event"));
            e.setDescription(rs.getString("description"));
            e.setLieu_event(rs.getString("Lieu_event"));
            e.setPrix(rs.getDouble("Prix"));
            list.add(e);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
        
    }

   
    public void modifier(Event t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Event> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Event t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Event t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

   
}

   
 
  

    


