/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Admin;
import Models.Client;
import static Controller.ModifyAdminController.isEmailAdress;
import Services.ServiceAdmin;
import Services.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class ModifyClientController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adr;
    @FXML
    private TextField email;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private TextField id;
    @FXML
    private TextField numT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void Cancel(ActionEvent event) {
           nom.setText("");
        prenom.setText("");
        adr.setText("");
        numT.setText("");
        email.setText("");
       
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
        String name = nom.getText();
        String pr = prenom.getText();
        String Address = adr.getText();
        String emaill = email.getText();
        String i = id.getText();
        String C =numT.getText();
         if (name.isEmpty() == false && pr.isEmpty() == false && Address.isEmpty() == false &&  emaill.isEmpty() == false &&  C.isEmpty() == false  ){
             
                        if (isEmailAdress(emaill))
                                {
                                     if (validatePhoneNumber(C))
            {
                                      ServiceClient service = new ServiceClient();
                                      Client A = new Client(Integer.parseInt(i),name,pr,Address,Integer.parseInt(C),emaill);
                                      service.modifier2(A);
                                      Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
                                                   alertAD.setTitle("Succes");
                                                   alertAD.setHeaderText(null);
                                                   alertAD.setContentText("Client Modifié avec succees ");
                                                   Optional<ButtonType> action = alertAD.showAndWait();
                                                   if (action.get() == ButtonType.OK) {
                                                                 Parent roott =FXMLLoader.load(getClass().getResource("/GUI/ModifyClient.fxml")); 
                                                                   Scene scene= new Scene(roott);
                                                                   Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                                                                   window.close();
                                                                     Notifications n = Notifications.create()
                                    .title("SUCCESS")
                                    .text("  Client Modifié")
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                            n.darkStyle();
                            n.show();
                                                   }
                                                                        
                                                   
                 }else 
            {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Le téléphone doit contenir 8 numéros");
            a.setHeaderText("Valeur invalide");
            a.show();
            }                                                      
             }else
             {
                   Alert a = new Alert(Alert.AlertType.WARNING);
                         a.setContentText("Verifier Votre Email");
                         a.setHeaderText("Valeur invalide");
                         a.show();
             }

            
        } else
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Champs Vide ");
             alert.showAndWait();
         }
        
    }
    
         public void showInforamtion (String n , String pr ,String user , String e , String t ,String numt)
      {
          nom.setText(n);
          prenom.setText(pr);
          adr.setText(user);
          numT.setText(numt);
          email.setText(e);  
          id.setText(t);
      }
        public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
        } 
             private static boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{8}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//return false if nothing matches the input
		else return false;
		
	}
      
}
