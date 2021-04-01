/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddCategoriesController implements Initializable {

 @FXML
  private TextField TextType;
 
    ServiceCategorie cs=new ServiceCategorie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
  
    @FXML
   private void saveCategoriesAction(ActionEvent event) throws SQLException, IOException {

         
           if (TextType.getText().isEmpty())
           {
            System.out.println(TextType);
               
                
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           
       
   
        else {
              
          String 	type=TextType.getText();
           
               
             
               
               cs.addCategorie(type);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategories.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
               
           }         
 
} 
