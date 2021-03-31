/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import entity.Demande;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ServiceDemande;
import java.util.Date;
import java.util.Properties;



/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AjouterController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextField terrain;
    @FXML
    private TextField equipe;
    @FXML
    private Button ajouter;
    private String dateN="yyy-MM-dd";
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws Exception {
        ServiceDemande sd=new ServiceDemande();
        sd.ajouter(new Demande(datN(date.getValue()),terrain.getText(),equipe.getText(),email.getText()));
         

 
            
            
            
            
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  demande Ajouté")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(3));
               n.darkStyle();
               n.show();
        
            sd.sendMail(email.getText());
        
        
              
              
              
      
    }    
        
        
    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateN);
    public String datN(LocalDate date ) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }



   
 
    
}
