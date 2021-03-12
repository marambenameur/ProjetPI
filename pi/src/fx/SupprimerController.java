/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import entity.Demande;
import static java.lang.Integer.parseInt;
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
public class SupprimerController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) {
                ServiceDemande sd = new ServiceDemande();
        sd.supprimer(new Demande( parseInt(id.getText())));
    JOptionPane.showMessageDialog(null,"demande supprimé");
    }
    
}
