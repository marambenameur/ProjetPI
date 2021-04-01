/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class GestionClubsController implements Initializable {

    @FXML
    private Button btncomp;
    @FXML
    private Button btnjrs;
    @FXML
    private Button btnclub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Competitions(ActionEvent event) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AjouterCompetition.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.DECORATED);
//            stage.show();
//            
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
        switchPane("/GUI/AjouterCompetition.fxml");
    }

    @FXML
    private void ListeJoueurs(ActionEvent event) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AjouterJoueur.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.DECORATED);
//            stage.show();            
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
        switchPane("/GUI/AjouterJoueur.fxml");
    }

    @FXML
    private void ListeClubs(ActionEvent event) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AjouterClub.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.DECORATED);
//            stage.show();
//            
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
        switchPane("/GUI/AjouterClub.fxml");
    }
    
      private void switchPane(String pane){
        try {
            MenuAdherantController.temporaryPane.getChildren().clear();
            StackPane pane2=FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements =pane2.getChildren();
            MenuAdherantController.temporaryPane.getChildren().setAll(elements);
        } catch (IOException ex) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
