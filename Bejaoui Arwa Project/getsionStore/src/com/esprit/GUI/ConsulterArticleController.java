/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.services.ServiceArticle;
import com.esprit.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class ConsulterArticleController implements Initializable {

    @FXML
    private Button fermer_article;
    @FXML
    private TextField ref_consult;
    @FXML
    private TextField lib_consult;
    @FXML
    private TextField qt_consult;
    @FXML
    private TextField prix_consult;
    @FXML
    private TextField cat_consult;
    @FXML
    private ImageView img_article;
    @FXML
    private Button validconsuly;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void fermerarticle(ActionEvent event) {
    }

    @FXML
    private void validerconsult(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == validconsuly) {


      String requete = "SELECT * FROM article WHERE ref = '"+ref_consult.getText()+"'  ";
      try{
                      Connection cnx = DataSource.getInstance().getCnx();

          PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                ref_consult.setText(rs.getString("ref"));
                lib_consult.setText(rs.getString("libelle"));
                cat_consult.setText(rs.getString("categorie"));
                qt_consult.setText(rs.getString("qt_article"));
                prix_consult.setText(rs.getString("prix"));
                Image image =new Image("image_article");
                img_article.setImage(image);
                ref_consult.setText(rs.getString("ref"));
            }
      }catch (SQLException e){
          e.printStackTrace();
      }
              
         
    }
    
    }
}
