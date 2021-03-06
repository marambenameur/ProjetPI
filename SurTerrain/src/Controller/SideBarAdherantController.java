/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class SideBarAdherantController implements Initializable {

    @FXML
    private JFXButton btnReserver;
    @FXML
    private JFXButton btnMatch;
    @FXML
    private JFXButton btnClub;
    @FXML
    private JFXButton btnStore;
    private JFXButton btnEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionbouton(MouseEvent event) {
        
         if (event.getSource() ==btnReserver) {
            switchPane("/GUI/GererReservation.fxml");
        } else if (event.getSource() == btnClub) {
            switchPane("/GUI/GestionClubs.fxml");
        }else if (event.getSource() ==btnStore) {
            switchPane("/GUI/accuiel.fxml");
        } else if (event.getSource() == btnEvent) {
            switchPane("/GUI/Event.fxml");
        }else if (event.getSource() == btnMatch) {
            switchPane("/GUI/ajouterequipe.fxml");
        }
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
