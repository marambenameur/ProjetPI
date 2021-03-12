/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import entity.Demande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField date;
    @FXML
    private TextField terrain;
    @FXML
    private TextField equipe;
    @FXML
    private Button ajouter;
    @FXML
    private Button reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceDemande sd=new ServiceDemande();
        sd.ajouter(new Demande(date.getText(),Integer.parseInt(terrain.getText()),Integer.parseInt(equipe.getText())));
        JOptionPane.showMessageDialog(null, "demande ajouter");
        
        
        
        
    }

    @FXML
    private void reset(ActionEvent event) {
    }
    
}
