/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.esprit.models.Categorie;
import com.esprit.models.Event;
import com.esprit.services.ServiceCategorie;
import com.esprit.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class ModifyEventController implements Initializable {

 ObservableList<String> options=FXCollections.observableArrayList();
    @FXML
     Button btnModifiyE;
    @FXML
     Button btnPhoto;
    @FXML
     DatePicker dpnDate_event;
    @FXML
     TextField tfnPrice;
    @FXML
     TextField tfnNom;
    @FXML
     TextField tfnLieu_event;
    @FXML
     TextField tfnNbr_participant;
    @FXML
     TextArea tanDescription;
    @FXML
     Label Nbr_participant;
    @FXML
     Label Categories;
    @FXML
     Label Photo;
    @FXML
     Label Lieu_event;
    @FXML
     Label Description;
    @FXML
     Label Nom;
    @FXML
     Label Price;
    @FXML
     Label Date_event;
 
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
     private ComboBox<Categorie> cbnCategories;
    Event e;
    Event ev = new Event();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //cbnCategories.setItems(options);
        Platform.runLater(()->{
            System.out.println(e);

             dpnDate_event.getEditor().setText(String.valueOf(e.getDate_event()));
                                        tfnPrice.setText(String.valueOf(e.getPrix()));

             System.out.println(e.getDate_event());
               tfnNom.setText(e.getNom());
               tfnLieu_event.setText(e.getLieu_event());
               tanDescription.setText(e.getDescription());
               ServiceCategorie cs = new ServiceCategorie();
            try {
                cbnCategories.setValue(cs.getCategorieById(e.getCategories_id()));
            } catch (SQLException ex) {
                Logger.getLogger(ModifyEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
    });    
    }
    
    @FXML
    private void Modifiy(ActionEvent event) throws SQLException, IOException {
        
        if (ev == null) {

            System.out.println("choisir un evenement");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify event");
            alert.setHeaderText(null);
            alert.setContentText("the event is not modified !");

            alert.showAndWait();
        }else {
                                String datePu=dpnDate_event.getValue().toString();
ev.setDate_event(java.sql.Date.valueOf(datePu));

            ev.setPrix(Double.valueOf(tfnPrice.getText()));
            ev.setNom(tfnNom.getText());
            ev.setLieu_event(tfnLieu_event.getText());
            ev.setDescription(tanDescription.getText());

            
                      

            //a mmodifier !!!!
            ev.setCategories_id(1);
            try {

               ServiceEvent es = new ServiceEvent();
                System.out.println("azaz.............");

                System.out.println(ev.toString());
               es.Modifier(ev);
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("Event.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();     

        } catch (Exception ex) {
                System.out.println("ex.............");
                ex.printStackTrace();
        }
            System.out.println("Modification terminé");
           
          
         //  Image image = new Image(input);
        //ImageView view = new ImageView();
        //tf_image_view.setImage(image);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement enregistré avec succès.");
        alert.setHeaderText(null);
//        alert.setContentText("L'evenement " + e.getNom() + " has been modified.");
        alert.showAndWait();
        }
    }
   
    
    
    
    void setData(int id, String Nom, Date Date_event, String Description, String Lieu_event, Double Prix) {
       ev.setId(id);
       tfnNom.setText(Nom);
       dpnDate_event.getEditor().setText(String.valueOf(Date_event));
       tanDescription.setText(Description);
       tfnLieu_event.setText(Lieu_event);
       tfnPrice.setText(String.valueOf(Prix));
    }
}