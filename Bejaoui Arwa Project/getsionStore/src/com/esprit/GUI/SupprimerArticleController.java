/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.models.Article;
import com.esprit.services.ServiceArticle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class SupprimerArticleController implements Initializable {

    @FXML
    private TextField ref;
    @FXML
    private Button validsupp;
    @FXML
    private Button annulsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validersupp(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == validsupp) {
            //try {
       ServiceArticle sa = new ServiceArticle();
       sa.supprimer(new Article(Integer.valueOf(ref.getText())));
       JOptionPane.showMessageDialog(null,"Article Supprimée");
        }
    }

    @FXML
    private void annulersup(javafx.event.ActionEvent mouseEvent) {
          if (mouseEvent.getSource() == annulsupp) {
               ref.setText(null);
        
        }
    }
    
}
