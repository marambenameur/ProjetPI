/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Terrain;
import Services.ServiceTerrain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class AjouterTerrainController implements Initializable {

    @FXML
    private TextField tfNomTerrain;
    @FXML
    private TextField tfAdresseTerrain;
    @FXML
    private Button btnajouterTerrain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterTerrain(ActionEvent event) {
        ServiceTerrain st = new Services.ServiceTerrain();
        st.ajouter(new Terrain(tfNomTerrain.getText(), tfAdresseTerrain.getText()));
        
    }
  

    @FXML
    private void AjouterTerrain(MouseEvent event) {
       
        } 
    
    
}
