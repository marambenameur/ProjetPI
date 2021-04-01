/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Models.Promotion;
import Services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author infoMix
 */
public class AddPromotionController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker date_debut;
    @FXML
    private Label date_fi;
    @FXML
   
    private Button btSaveE;
    @FXML
    private TextField pourcentage;
    @FXML
    private DatePicker date_fin;
    @FXML
    private Label Date_event;
    @FXML
    private Label Lieu_event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
       
    }    
         
    @FXML
    private void Save(ActionEvent event) throws SQLException, IOException {
        if ( date_debut.getEditor().getText().length() == 0 || date_fin.getEditor().getText().length() == 0 || pourcentage.getText().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();
        }
   
       else if (date_debut.getValue()==null){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez choisir une date");
            alert3.showAndWait();  
            }
         else if (date_fin.getValue()==null){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez choisir une date");
            alert3.showAndWait();  
            }
    if (Integer.parseInt(pourcentage.getText())<10 || (Integer.parseInt(pourcentage.getText())> 80))
         {
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur entre 10 et 80  !!");
            alert3.showAndWait();  
            
           }
        else{
            ServicePromotion se = new ServicePromotion();
            se.Ajouter(new Promotion(Integer.parseInt(pourcentage.getText()), Date.valueOf(date_debut.getValue()),Date.valueOf(date_fin.getValue())));
            //Event ev= new Event(1,  cbCategories.getValue().getId(), tfNom.getText(), Date.valueOf(dpDate_event.getValue()), taDescription.getText(), tfLieu_event.getText(),Double.valueOf(tfPrice.getText()),Integer.parseInt(tfNbr_participant.getText()) );
            System.out.println("ok");
            
//          try {
//                      ServiceEvent   es= new ServiceEvent();
//                      es.Ajouter(ev);
//                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
//                      alert.setTitle("Ajout terminé");
//                      alert.setHeaderText(null);
//                      alert.setContentText("promotion : " +ev.getNom()+"  est ajoutée avec succès ");
//                      alert.showAndWait();
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }

        System.out.println("ajout terminé");   
         AnchorPane pane   = javafx.fxml.FXMLLoader.load(getClass().getResource("/GUI/Promotion.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();     
        }
    }
}


