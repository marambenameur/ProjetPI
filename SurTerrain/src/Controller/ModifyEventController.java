/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Categorie;
import Models.Event;
import Services.ServiceCategorie;
import Services.ServiceEvent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class ModifyEventController implements Initializable {

 ObservableList<String> options=FXCollections.observableArrayList();
    @FXML
     Button btnModifiyE;
    Button btnPhoto;
    @FXML
     DatePicker dpnDate_event;
    @FXML
     TextField tfnPrice;
    @FXML
     TextField tfnNom;
    @FXML
     TextField tfnLieu_event;
    TextField tfnNbr_participant;
    @FXML
     TextArea tanDescription;
    Label Nbr_participant;
    @FXML
     Label Categories;
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
    
    Event e;

    Event ev = new Event();
    @FXML
    private ComboBox<String> cbCategories;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                   ArrayList<Categorie> lesCategories= new ArrayList<>();
        ServiceCategorie cs1 = new ServiceCategorie();
                try {
                    lesCategories= cs1.getAllCategorie();
                        ArrayList<String> list=new ArrayList<>();
      for(Categorie i : lesCategories)
                {
                 list.add(i.getType());
                }
      
      
ObservableList<String>data=FXCollections.observableArrayList(list);
     cbCategories.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ModifyEventController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
  
  


        //cbnCategories.setItems(options);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(e);
          
     
                dpnDate_event.getEditor().setText(String.valueOf(e.getDate_event()));
                tfnPrice.setText(String.valueOf(e.getPrix()));
                
                System.out.println(e.getDate_event());
                tfnNom.setText(e.getNom());
                tfnLieu_event.setText(e.getLieu_event());
                tanDescription.setText(e.getDescription());
                
                ServiceCategorie cs = new ServiceCategorie();
                /*Categorie  combo =cat.getValue();
                es.update(t,combo);
                cbnCategories.setValue(cs.getCategorieById(e.getCategorie_id()));
                {
                    Logger.getLogger(ModifyEventController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        });    
    }
    
    @FXML
    private void Modifiy(ActionEvent event) throws SQLException, IOException {
        
     
            
String datePu=dpnDate_event.getValue().toString();
ev.setDate_event(Date.valueOf(datePu));
   ServiceCategorie cs1 = new ServiceCategorie();
 ev.setPrix(Double.valueOf(tfnPrice.getText()));
            ev.setNom(tfnNom.getText());
            ev.setLieu_event(tfnLieu_event.getText());
            ev.setDescription(tanDescription.getText());
            ev.setCategories_id(cs1.getCategorieByType(this.cbCategories.getSelectionModel().getSelectedItem()));
            
            
  System.out.println("123"+ev);
            
                      

            //a mmodifier !!!!
            //ev.setCategories_id();
            try {

               ServiceEvent es = new ServiceEvent();
                System.out.println("azaz.............");
                es.update(ev,cs1.getCategorieByType(this.cbCategories.getSelectionModel().getSelectedItem()));

                System.out.println(ev.toString());
               //es.update(t,combo);
              /* AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/Event.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();*/ 
              try {
            Parent parent = FXMLLoader.load(getClass().getResource("../GUI/Event.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();    
Stage stage2 = (Stage) btnModifiyE.getScene().getWindow();
        stage2.close();        
        } catch (IOException ex) {
            System.out.println(ex);
        }


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
   
    
    
    
    void setData(int id, String Nom, Date Date_event, String Description, String Lieu_event, Double Prix) {
       ev.setId(id);
       tfnNom.setText(Nom);
       dpnDate_event.getEditor().setText(String.valueOf(Date_event));
       tanDescription.setText(Description);
       tfnLieu_event.setText(Lieu_event);
       tfnPrice.setText(String.valueOf(Prix));
    }
}