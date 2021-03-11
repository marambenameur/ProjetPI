/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.models.Article;
import com.esprit.services.ServiceArticle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class ModifierArticleController implements Initializable {

    @FXML
    private Button modifart;
    @FXML
    private Button annulart;
    @FXML
    private TextField ref;
    @FXML
    private TextField lib;
    @FXML
    private TextField img;
    @FXML
    private TextField qt;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list=FXCollections.observableArrayList("crampons","veste","accessoir","proteine");
         cat.setItems(list);
    }    

    @FXML
    private void modifierarticle(javafx.event.ActionEvent mouseEvent) {
           if (mouseEvent.getSource() == modifart) {
            //try {
       ServiceArticle sa = new ServiceArticle();
       sa.modifier(new Article( lib.getText(),cat.getSelectionModel().getSelectedItem().toString(),img.getText(),Integer.valueOf(prix.getText()),Integer.valueOf(qt.getText()),Integer.valueOf(ref.getText())));
       JOptionPane.showMessageDialog(null,"Article Modifiée");
       
      
       



//        FXMLLoader loader =new FXMLLoader(getClass().getResource("menuarticle.fxml"));
//          Parent root= loader.load();
//            } catch (IOException ex) {
//                Logger.getLogger(MenuarticleController.class.getName()).log(Level.SEVERE, null, ex); 
//    }
             }
    }

    @FXML
    private void annulermodif(javafx.event.ActionEvent mouseEvent) {
          if (mouseEvent.getSource() == annulart) {
               ref.setText(null);
        lib.setText(null);
        img.setText(null);
                qt.setText(null);
        prix.setText(null);
        }
    }
    
}
