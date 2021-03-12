/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import entity.Demande;
import entity.Equipe;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.ServiceEquipe;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class SupprimerequipeController implements Initializable {

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
    private void supprimer(ActionEvent event) {
         ServiceEquipe se = new ServiceEquipe();
        se.supprimer(new Equipe( parseInt(id.getText())));
    JOptionPane.showMessageDialog(null,"Equipe supprimé");
    }
    
}
