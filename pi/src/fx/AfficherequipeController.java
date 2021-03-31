/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import entity.Demande;
import entity.Equipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ServiceDemande;
import service.ServiceEquipe;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AfficherequipeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nombre;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        ServiceEquipe se=new ServiceEquipe();
        se.ajouter(new Equipe(nom.getText(),Integer.parseInt(nombre.getText())));
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  equipe Ajouté")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(javafx.util.Duration.seconds(3));
               n.darkStyle();
               n.show();
    }
    
}
