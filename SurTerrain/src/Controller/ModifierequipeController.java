/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Equipe;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Services.ServiceEquipe;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class ModifierequipeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nombre;
    @FXML
    private TextField id;
    @FXML
    private Button appliquer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void appliquer(ActionEvent event) {
        ServiceEquipe se = new ServiceEquipe();
        se.modifier(new Equipe( parseInt(id.getText()),nom.getText(),parseInt( nombre.getText())));
    JOptionPane.showMessageDialog(null,"Equipe modifié");
    }

    @FXML
    private void retourHome(MouseEvent event) {
    }
    
}
