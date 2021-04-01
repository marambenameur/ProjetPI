/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AddCompteController.isEmailAdress;
import Services.ServiceAdherant;
import Services.ServiceAdmin;
import Services.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    public TextField emailtextfield;
    @FXML
    private  PasswordField mdptextfield;
    @FXML
    private Label mdpoublié;
    @FXML
    private Button btlogin;
    @FXML
    private Label pasdecompte;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Label Connexion;
    @FXML
    private Label Add_Compte;
    @FXML
    private Label acceuil;

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
      //public String email = emailtextfield.getText();
    @FXML
    public void loginButtonOnAction (ActionEvent event) throws IOException
    {
        
        if (emailtextfield.getText().isEmpty() == false && mdptextfield.getText().isEmpty()== false)
        {
            if (isEmailAdress(emailtextfield.getText()))
            {
                if (admin.findByEmailPassword(emailtextfield.getText(),mdptextfield.getText()) == true || client.findByEmailPassword(emailtextfield.getText(),mdptextfield.getText()) == true || adherant.findByEmailPassword(emailtextfield.getText(),mdptextfield.getText()) == true)
                         {
                
                             if (admin.findByEmailPassword(emailtextfield.getText(),mdptextfield.getText()) == true )
                             {
                                   FXMLLoader loader =new FXMLLoader(getClass().getResource("/GUI/MenuAdmin.fxml")); 
                                   Parent root = (Parent) loader.load();
                                   MenuAdminController second =loader.getController();
                                   second.myFunction(emailtextfield.getText());
                                  
                                   Scene scene= new Scene(root);
                                   Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                                   window.setScene(scene);
                                   window.show(); 
                                  
                             }else if (client.findByEmailPassword(emailtextfield.getText(),mdptextfield.getText()) == true )
                             {
                                    FXMLLoader loader =new FXMLLoader(getClass().getResource("/GUI/SideMenu.fxml")); 
                                   Parent root = (Parent) loader.load();
                                   SideMenuController second3 =loader.getController();
                                   second3.myFunction(emailtextfield.getText());
                                  
                                   Scene scene= new Scene(root);
                                   Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                                   window.setScene(scene);
                                   window.show(); 
                             }else
                             {
                                   FXMLLoader loader =new FXMLLoader(getClass().getResource("/GUI/MenuAdherant.fxml")); 
                                   Parent root = (Parent) loader.load();
                                   MenuAdherantController second2 =loader.getController();
                                   second2.myFunction(emailtextfield.getText());
                                  
                                   Scene scene= new Scene(root);
                                   Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                                   window.setScene(scene);
                                   window.show(); 
                                     }
            
                         }else
                {
                     loginMessageLabel.setText("accun Compte avec Ces Cordonées  "); 
                }
                
    
                
            }else
            {
               loginMessageLabel.setText("Verfier Votre @ email"); 
            }
                    
  
        }else
        {
         loginMessageLabel.setText("Champs Vide"); 
        }

    }
    public StringProperty returnMail()
    {
       emailtextfield.getText();
        return emailtextfield.textProperty();
    }
    
 

    @FXML
    public void acceuil (MouseEvent event) throws Exception 
    {
      Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")); 
          Scene scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.show();
    }
    @FXML
   public void CreationPage (MouseEvent event) throws Exception 
    {
          Parent root =FXMLLoader.load(getClass().getResource("/GUI/AddCompte.fxml")); 
          Scene scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.show();
    }
     public void connexion (MouseEvent event) throws Exception 
    {
    Parent root =FXMLLoader.load(getClass().getResource("/GUI/login.fxml")); 
          Scene scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.show();
    }

    @FXML
    public void ToResetPassword (MouseEvent event) throws IOException
    {
        Parent root =FXMLLoader.load(getClass().getResource("/GUI/SendCode.fxml")); 
          Scene scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.show();
    }
     
    
}
