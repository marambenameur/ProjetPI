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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class AddEventController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker dpDate_event;
    @FXML
    private Label Date_event;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfLieu_event;
    @FXML
    private Label Price;
    @FXML
    private Label Nom;
    @FXML
    private TextField tfNom;
    @FXML
    private Label Description;
    @FXML
    private Label Lieu_event;
    @FXML
    private Button btSaveE;
    @FXML
    private ComboBox<?> cbCategories;
    @FXML
    private Label Categories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ArrayList<Categorie> lesCategories= new ArrayList<>();
        ServiceCategorie cs = new ServiceCategorie();
     try {
         lesCategories=cs.getAllCategorie();
     } catch (SQLException ex) {
         //Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
     }
        ObservableList obs = FXCollections.observableArrayList(lesCategories);
        cbCategories.setItems(obs);
        
       
    }    
         
    @FXML
    private void Save(ActionEvent event) throws SQLException, IOException {
        if ( dpDate_event.getEditor().getText().length() == 0 || tfPrice.getText().length() == 0 || tfNom.getText().length() == 0 || tfLieu_event.getText().length() == 0 ||  taDescription.getText().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();
        }
        else if (Float.parseFloat(tfPrice.getText())<0){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur positif !!");
            alert3.showAndWait();  
            }
        
         /* else if (cbCategories.getValue()==null){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez choisir une categories");
            alert3.showAndWait();  
            }*/
       else if (dpDate_event.getValue()==null){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez choisir une date");
            alert3.showAndWait();  
            }
       else if (dpDate_event.getValue().isBefore(java.time.LocalDate.now())){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez choisir une autre date");
            alert3.showAndWait();  
            }
          else if (tfNom.getText().equals("")){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("le champ est vide");
            alert3.showAndWait();  
            }
          else if (tfLieu_event.getText().equals("")){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("le champ est vide");
            alert3.showAndWait();  
            }
          else if (taDescription.getText().equals("")){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("le champ est vide");
            alert3.showAndWait();  
            }
        else{
            ServiceEvent se = new ServiceEvent();
            double tfprix = Double.parseDouble(tfPrice.getText());
            se.Ajouter(new Event(tfNom.getText(), Date.valueOf(dpDate_event.getValue()), taDescription.getText(), tfLieu_event.getText(),tfprix));
            //Event ev= new Event(1,  cbCategories.getValue().getId(), tfNom.getText(), Date.valueOf(dpDate_event.getValue()), taDescription.getText(), tfLieu_event.getText(),Double.valueOf(tfPrice.getText()),Integer.parseInt(tfNbr_participant.getText()) );
            System.out.println("ok");
            
//          try {
//                      ServiceEvent   es= new ServiceEvent();
//                      es.Ajouter(ev);
//                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
//                      alert.setTitle("Ajout terminé");
//                      alert.setHeaderText(null);
//                      alert.setContentText("L'evenement : " +ev.getNom()+"  est ajoutée avec succès ");
//                      alert.showAndWait();
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }

        System.out.println("ajout terminé");   
         AnchorPane pane   = javafx.fxml.FXMLLoader.load(getClass().getResource("Event.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();     
        }
    }
    
//     @FXML
//    private void choiceFileAction(ActionEvent event) throws IOException {
//
//        Stage primary = new Stage();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Selectionner une image");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image File\n" +
//"        String path = \"C:\\\\wamp64\\\\www\";s", "*.png", "*.jpg", "*.gif"));
//        File file = fileChooser.showOpenDialog(primary);
//        btPhoto.setText(file.getName());
//        if (file != null) {
//            try {
//           
//                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    }
    }
   
   
