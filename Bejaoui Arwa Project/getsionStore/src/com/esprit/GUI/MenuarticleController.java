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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class MenuarticleController implements Initializable {

    @FXML
    private TextField lib;
    @FXML
    private TableView<Article> tab_art;
    @FXML
    private TableColumn<Article, Integer> col_ref;
    @FXML
    private TableColumn<Article, String> col_lib;
    @FXML
    private TableColumn<Article, String> col_cat;
    @FXML
    private TableColumn<Article, Integer> col_qt;
    @FXML
    private TableColumn<Article, Integer> col_prix;
    @FXML
    private Button consultart;
    @FXML
    private Button ajoutart;
    @FXML
    private Button modifart;
    @FXML
    private Button suppart;
    @FXML
    private Button rechart;
    @FXML
    private Button retourne;
    @FXML
    private Button refresh_article;
    @FXML
    private Button triprix;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
          
            
        col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ServiceArticle sa = new ServiceArticle();
               ObservableList<Article> list = sa.getArticlesList();
       tab_art.setItems(list); 
       
      
    }    

   

  

    @FXML
    private void consulterarticle(javafx.event.ActionEvent mouseEvent) throws IOException {
        
       if (mouseEvent.getSource() == consultart) {
           
                Parent root = FXMLLoader.load(getClass().getResource("ConsulterArticle.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();
                
      
       } 
    }

    @FXML
    private void ajouterarticle(javafx.event.ActionEvent mouseEvent) {
       
        if (mouseEvent.getSource() == ajoutart) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AjouterArticle.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(MenuarticleController.class.getName()).log(Level.SEVERE, null, ex); 
    }}
    }

    @FXML
    private void modifierarticle(javafx.event.ActionEvent mouseEvent) {
         
        if (mouseEvent.getSource() == modifart) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierArticle.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuarticleController.class.getName()).log(Level.SEVERE, null, ex); 
    }}
    }
   
    
   
     
    @FXML
    private void supprimeraticle(javafx.event.ActionEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() == suppart) {
            try {
             Parent root = FXMLLoader.load(getClass().getResource("SupprimerArticle.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();
            } catch (IOException ex) {
            System.err.println("veuillez supprimer une autre fois l'article,un probleme so produit lors de suppression");    }
        }
        
    }

    @FXML
    private void afficherarticle(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == refresh_article) {
             col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ServiceArticle sa = new ServiceArticle();
               ObservableList<Article> list = sa.getArticlesList();
       tab_art.setItems(list);
         }
       
    }

    @FXML
    private void rechercherarticle(javafx.event.ActionEvent mouseEvent) throws IOException {
        
         if (mouseEvent.getSource() == rechart) {
      
         ServiceArticle sa = new ServiceArticle();
//sa.rechercher(lib.getText());
//JOptionPane.showMessageDialog(null,"Article Trouvée");
   col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
               ObservableList<Article> list = sa.rechercher(lib.getText());
       tab_art.setItems(list);
       
   JOptionPane.showMessageDialog(null,"Article Trouvée");
         }
         }
    

    @FXML
    private void retourne(ActionEvent event) {
    }

       @FXML
    void trierarticleprix(javafx.event.ActionEvent mouseEvent) {
    if (mouseEvent.getSource() ==  triprix) {
            
         ServiceArticle sa = new ServiceArticle();
//sa.rechercher(lib.getText());
//JOptionPane.showMessageDialog(null,"Article Trouvée");
   col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
               ObservableList<Article> list = sa.trier_article();
       tab_art.setItems(list);
       

      
  
         }
    }
}
    