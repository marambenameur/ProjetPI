/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ServiceAdmin;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class ResetPassController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField ancien;
    @FXML
    private TextField nouveau;
    @FXML
    private TextField confirmer;
    ServiceAdmin admin =new ServiceAdmin();
    @FXML
    private Label email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        if (admin.findByPass(ancien.getText())==  true)
        {
              if (nouveau.getText().equals(confirmer.getText()))
                 {
            admin.UpdatePass(nouveau.getText(),email.getText());
            Alert alertA = new Alert(Alert.AlertType.CONFIRMATION);
            alertA.setTitle("Succes");
            alertA.setHeaderText(null);
            alertA.setContentText("Mot de passe Modifié avec Succés");
            alertA.showAndWait();
            Optional<ButtonType> action = alertA.showAndWait();
                                                   if (action.get() == ButtonType.OK) {
                                                                  Parent roott =FXMLLoader.load(getClass().getResource("/GUI/ResetPass.fxml")); 
                                                                   Scene scene= new Scene(roott);
                                                                   Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                                                                   window.close();}
                     
                 }else
              {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Verifier que les deux mot passe sont egaux");
            alert.showAndWait();
              }
            
        }else
        {
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Erreur");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Verfier Votre Ancien Mot de Passe");
                    alert1.showAndWait();
        }
    }
    
          public void myFunction(String text)
    {
        email.setText(text);
         
        
    }
    
}
