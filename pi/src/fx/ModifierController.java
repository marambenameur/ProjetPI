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
public class ModifierController implements Initializable {

    @FXML
    private TextField date;
    @FXML
    private TextField terrain;
    @FXML
    private TextField equipe;
    @FXML
    private TextField id;
    @FXML
    private Button modifier;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        ServiceDemande sd = new ServiceDemande();
        sd.modifier(new Demande(parseInt(id.getText()),date.getText(), terrain.getText(),equipe.getText(),email.getText()));
    JOptionPane.showMessageDialog(null,"demande modifié");
        
    }

    @FXML
    private void initialize(ActionEvent event) {
        
        
    }
    public  void showInformation(String i , String d, String t,String e,String em ) {
        
       id.setText(i);
       date.setText(d);
       terrain.setText(t);
       equipe.setText(e);
       email.setText(em);
        
        
        
    }
}
