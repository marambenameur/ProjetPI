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
public class AjouterArticleController implements Initializable {

    @FXML
    private Button valid_ajout;
    @FXML
    private Button annulerajout;
    @FXML
    private TextField ref;
    @FXML
    private TextField lib;
    @FXML
    private TextField img;
    @FXML
    private TextField qt;
    @FXML
    private TextField px;
    @FXML
    private ComboBox combcat;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ObservableList<String> list=FXCollections.observableArrayList("crampons","veste","accessoir","proteine");
         combcat.setItems(list);
    }   

    @FXML
    private void Ajouter_Article(javafx.event.ActionEvent mouseEvent) throws IOException {
             if (mouseEvent.getSource() == valid_ajout) {
            //try {
       ServiceArticle sa = new ServiceArticle();
       sa.ajouter(new Article( lib.getText(),combcat.getSelectionModel().getSelectedItem().toString(),img.getText(),Integer.valueOf(px.getText()),Integer.valueOf(qt.getText()),Integer.valueOf(ref.getText())));
       JOptionPane.showMessageDialog(null,"Article Ajoutée");
       
      
       



//        FXMLLoader loader =new FXMLLoader(getClass().getResource("menuarticle.fxml"));
//          Parent root= loader.load();
//            } catch (IOException ex) {
//                Logger.getLogger(MenuarticleController.class.getName()).log(Level.SEVERE, null, ex); 
//    }
             }
    }

    @FXML
    private void annulerajout(javafx.event.ActionEvent mouseEvent) {
          if (mouseEvent.getSource() == annulerajout) {
        ref.setText(null);
        lib.setText(null);
        img.setText(null);
                qt.setText(null);
        px.setText(null);



          }
    }
    
}
