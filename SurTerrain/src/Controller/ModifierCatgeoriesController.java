/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Categorie;
import Services.ServiceCategorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maram
 */


import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierCatgeoriesController implements Initializable {

 @FXML
  private TextField TextType;

  Categorie c=new Categorie();

    ServiceCategorie cs=new ServiceCategorie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
Platform.runLater(()->{
       

            TextType.setText(c.getType());

        
    });    
    }
       
    @FXML
   private void saveCategoriesUpdatedAction(ActionEvent event) throws SQLException, IOException {

         
 
           if (TextType.getText().isEmpty()
                
               
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           
  
        
        
        else {
              
                   
        c.setType(TextType.getText());

       

    c.setId(c.getId());
               cs.UpdateCategorie(c);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategories.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
           }  


    void setData(String type, int id) {
    
     c.setId(id);
        System.out.println("hi");
       c.setType(type);
}
}