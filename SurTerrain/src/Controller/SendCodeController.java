/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ServiceAdherant;
import Services.ServiceAdmin;
import Services.ServiceClient;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class SendCodeController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button envoyer;
    @FXML
    private TextField code;
    @FXML
    private Button valider;
    
    int randomcode ;
    private Component root;
    @FXML
    private TextField mdp1;
    @FXML
    private TextField mdp2;
    @FXML
    private Button btreset;
        ServiceAdmin admin =new ServiceAdmin();
        ServiceAdherant adherant = new ServiceAdherant();
        ServiceClient client = new ServiceClient();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void envoyer (ActionEvent event)
    {
        if (client.findByEmail(email.getText())== false && admin.findByEmail(email.getText())==false && adherant.findByEmail(email.getText())==false)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Email enregistrer de ce genre !! Veuiller crée un compte ");
            alert.showAndWait();
        }else
        {
        try {
        Random rand = new Random();
        randomcode = rand.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "Benazizasalah55@gmail.com";
        String pass = "salahest123456..";
        String to =email.getText();
        String Subject ="Reseting Code ";
        String message ="Your Reseting Code : "+randomcode;
        boolean sessionDebug =false ;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","host");
        pros.put("mail.smtp.port","587");
        pros.put("mail.smtp.starttls.auth","true");
        pros.put("mail.smtp.starttls.required","true");
        
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailsession =Session.getDefaultInstance(pros,null);
        mailsession.setDebug(sessionDebug);
        Message msg = new MimeMessage(mailsession);
        msg.setFrom(new InternetAddress(user));
        InternetAddress [] address ={new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(Subject);
        msg.setText(message);
        Transport transport =mailsession.getTransport("smtp");
        transport.connect(host,user,pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("code has been send to the mail");
                    alert1.showAndWait();
       // JOptionPane.showMessageDialog(null,"code has been send to the mail");
        }catch (Exception ex)
                {
           JOptionPane.showMessageDialog(root,ex);         
                } 
        }
    }
    @FXML
    public void valider (ActionEvent event) throws IOException
    {
        if (Integer.valueOf(code.getText())==randomcode)
        {
             mdp1.setVisible(true);
             mdp2.setVisible(true);
             btreset.setVisible(true);
          
        }
    }

    @FXML
    private void Reset(ActionEvent event) throws IOException {
       if (mdp1.getText().equals(mdp2.getText()))
       {
           if (admin.findByEmail(email.getText())==true)
           {
           admin.UpdatePass(mdp1.getText());
            Alert alertA = new Alert(Alert.AlertType.CONFIRMATION);
            alertA.setTitle("Succes");
            alertA.setHeaderText(null);
            alertA.setContentText("Mot de passe Modifié avec Succés");
            alertA.showAndWait();
            Optional<ButtonType> actionA = alertA.showAndWait();
             if (actionA.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                   window.setScene(scene);
                   window.show();}
        
           }else if (client.findByEmail(email.getText())==true)
           {
           client.UpdatePass(mdp1.getText(),email.getText());
            Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
            alertC.setTitle("Succes");
            alertC.setHeaderText(null);
            alertC.setContentText("Mot de passe Modifié avec Succés");
            Optional<ButtonType> actionC = alertC.showAndWait();
            if (actionC.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                  window.setScene(scene);
                  window.show();}
           }else if  (adherant.findByEmail(email.getText())==true)
           {
           adherant.UpdatePass(mdp1.getText(),email.getText());
            Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
            alertAD.setTitle("Succes");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Mot de passe Modifié avec Succés");
            Optional<ButtonType> actionAD = alertAD.showAndWait();
            if (actionAD.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                   window.setScene(scene);
                   window.show();}
           }
       }else 
       {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Verifier que les deux mot passe sont egaux");
            alert.showAndWait();
       }
    }
    
}
