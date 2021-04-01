/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class RechercherTerrainController implements Initializable {

    @FXML
    private ImageView btnLogo;
    @FXML
    private ListView<?> LVterrain;
    @FXML
    private Button btnRecherche;
    @FXML
    private TextField tfRecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rechercherTerrain(ActionEvent event) {
    }
    
}
