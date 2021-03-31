/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;
import datasource.DataSource;
import entity.Demande;
import fx.AjouterController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
        
/**
 *
 * @author ABS1
 */
public class ServiceDemande implements IService<Demande>  {
    
    
    Connection cnx = DataSource.getInstance().getCnx();

  @Override
    public void ajouter(Demande d) {
        try {
            String requete = "INSERT INTO demande (date,nomterrain,nomequipe,email,etat) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            String etat="libre";
            pst.setString(1, d.getDate());
            pst.setString(2, d.getNomterrain());
            pst.setString(3, d.getNomequipe());
            pst.setString(4, d.getEmail());
            pst.setString(5,etat);
            pst.executeUpdate();
            System.out.println("demande ajoutée !");
            
            
         
    
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    

        

        
  @Override
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
        @Override
          public List<Demande> afficher() {
        List<Demande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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
        String requete = "UPDATE demande SET date= '"+d.getDate()+"', nomterrain= '"+d.getNomterrain()+"', nomequipe= '"+d.getNomequipe()+"',email='"+d.getEmail()+"',etat='"+d.getEtat()+"' WHERE id='"+d.getId()+"'";
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
            ResultSet rs = stm.executeQuery("select id,nomterrain,nomequipe from demande order by date ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nomterrain = rs.getString("nomequipe");
                 String nomequipe = rs.getString("nomequipe");
                
                
                
                Demande d1 = new Demande(id,nomterrain,nomequipe);
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
            d= new Demande(rs.getString("date"), rs.getString("nomterrain"),rs.getString("nomequipe"));
        }
        return d;  
    }
    public List<Demande> Search(String charac) {
           String requete="select * from demande where id LIKE '%"+charac+"%'or date LIKE '%"+charac+"%' or nomequipe LIKE '%"+charac+"%' or nomterrain LIKE '%"+charac+"'" ;
           
           List<Demande> demande = new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(requete);           
     while(rst.next()) 
    {       
        //System.out.println("Event : "+rst.getString("description")+"\tMedia :"+rst.getString("source")+"\tNombre de J'aime :"+rst.getInt("nbrlikes") );
 
            Demande result = new Demande();
            result.setId(rst.getInt("id"));
            result.setDate(rst.getString("date"));
            result.setNomequipe(rst.getString("nomequipe"));
            result.setNomterrain(rst.getString("nomterrain"));
           
            
            
     
          
    }
        } catch (SQLException ex) {
            System.out.println("No materiel Available !");
        } 
        return demande;   
    }
    
    public static void sendMail(String recepient ) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        

        //Your gmail address
        String myAccountEmail = "mohamedalaa.dhaouadi@esprit.tn";
        //Your gmail password
        String password = "181JMT0832";

        //Create a session with account credentials
       
        Session session = Session.getInstance(properties, new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient );

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        Message message=null;
        try {
             message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Demande de Match");
            String htmlCode = "<h1> Mercie Monsieur Votre demande a ete cree sur le date </h1> <br/> <h2><b> et dans le terrain </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } 
           catch (Exception ex) {
            Logger.getLogger(AjouterController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }

 

}
    
    

