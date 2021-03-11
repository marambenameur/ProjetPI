/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class AccuielController implements Initializable {

    @FXML
    private Button facture;
    @FXML
    private Button article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gofacture( javafx.event.ActionEvent mouseEvent) {
       
        
        }

    @FXML
    private void goarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == article) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("menuarticle.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex); 
    }}
    }
    
}
