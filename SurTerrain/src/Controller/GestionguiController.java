/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class GestionguiController implements Initializable {

    @FXML
    private Button btnjrs;
    @FXML
    private Button btnclub;
    @FXML
    private Button btncomp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //TODO
      
       
    }    

    @FXML
    private void ListeJoueurs(ActionEvent event) throws IOException {
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/Joueursgui.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();            
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void ListeClubs(ActionEvent event) throws IOException {
       try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/Clubgui.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void Competitions(ActionEvent event) throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/competitiongui.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
   
    
}
