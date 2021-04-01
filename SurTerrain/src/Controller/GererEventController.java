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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class GererEventController implements Initializable {

    
    @FXML
    private Button pro;
    @FXML
    private Button cat;
    @FXML
    private Button eventt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
       public void actionButton (MouseEvent event)
    {
         if (event.getSource() == eventt) {
        switchPane("/GUI/Event.fxml");
        } else if (event.getSource() ==pro) {
         switchPane("/GUI/Promotion.fxml");
        } else if (event.getSource() == cat) {
        switchPane("/GUI/AfficherCategories.fxml");
        }
    }
        private void switchPane(String pane){
        try {
            MenuAdminController.temporaryPane.getChildren().clear();
            StackPane pane2=FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements =pane2.getChildren();
            MenuAdminController.temporaryPane.getChildren().setAll(elements);
        } catch (IOException ex) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
