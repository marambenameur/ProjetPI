/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Models.Categorie;
import Models.Event;
import Models.Promotion;
import Utils.DataSource;
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
public class ServicePromotion  implements IServices<Promotion>{
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement stm;
    private PreparedStatement ps;
    private ResultSet rs;
int id_promo  ;
    int pourcentage ;
    Date date_debut ;
    Date date_fin ;


    public void Ajouter(Promotion t) 
    {
        try {
            String req="INSERT INTO promotion (pourcentage,date_debut,date_fin) VALUES (?,?,?)";
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,t.getPourcentage());
            pst.setDate(2,t.getDate_debut());
            pst.setDate(3,t.getDate_fin());
           
            
            pst.executeUpdate();
                    System.out.println("cbn");

        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
System.out.println("mosh c bn ");
        }
    }
    public ObservableList<Promotion> lister(){
           ObservableList<Promotion> list1 =FXCollections.observableArrayList();
           String requete="SELECT id_promo,pourcentage,date_debut,date_fin) FROM promotion";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                     list1.add
        (new Promotion(rs.getInt(1),rs.getInt(2),rs.getDate(3),
                rs.getDate(4)));
                 
                     }
                System.out.println("lister promotion");
                }  
            catch (SQLException ex) {
            
            } 

        
     return list1;
    }
 
    public void Modifier(Promotion t) 
    {
       
          try {
              
              String sql = "UPDATE promotion SET pourcentage=?,date_debut=?,date_fin=? WHERE id_promo=?";
              PreparedStatement statement = cnx.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
              try {
                  statement.setInt(1, t.getPourcentage());
                  statement.setDate(2,(Date)t.getDate_debut());
                  statement.setDate(3, (Date)t.getDate_fin());
                                   statement.setInt(4, Integer.valueOf(t.getId_promo()));

                  System.out.println(statement);
                  statement.executeUpdate();
                  System.out.println("update done");
              } catch (SQLException ex) {
                  Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
              }
              
          } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     *
     * @param e
     * @throws SQLException
     */
    
    public void Supprimer(Promotion e) throws SQLException 
    { 
    
 try { 
            String delete = "DELETE FROM promotion WHERE id_promo = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        int id = e.getId_promo();
        
        st2.setInt(1,id);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
 

    public List<Promotion> Afficher() {
        List<Promotion> l = new ArrayList<>();
         try {
            String req="SELECT * FROM promotion";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {

                Promotion e = new Promotion();
                                e.setId_promo(rs.getInt("id_promo"));

                e.setPourcentage(rs.getInt("pourcentage"));
                e.setDate_debut(rs.getDate("date_debut"));
                 e.setDate_fin(rs.getDate("date_fin"));
                System.out.println(e);

                l.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    } 
    public List<Promotion> readAl() throws SQLException  {
        List<Promotion> evs =new ArrayList<>();
       
        Statement stm = cnx.createStatement();
             
          try {  ResultSet rest= 
                    stm.executeQuery("select * from promotion   ");
            while(rest.next()){
                Promotion ev = new Promotion();
                ev.setId_promo(rest.getInt("id promo"));
                    ev.setDate_debut(rest.getDate("Date debut"));
             ev.setDate_fin(rest.getDate("Date fin"));
                           
               evs.add(ev);
                System.out.println(evs);
                 }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
         return evs;
    }

    @Override
    public List<Promotion> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

  
