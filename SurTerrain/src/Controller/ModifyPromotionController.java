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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author infoMix
 */
public class ModifyPromotionController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker date_debut;
    @FXML
    private Label Date_event;
    @FXML
    private Label date_fi;
    @FXML
    private Label Lieu_event;
    @FXML
    private Button btSaveE;
    @FXML
    private TextField pourcentage;
    @FXML
    private DatePicker date_fin;
   Promotion e;
    Promotion ev = new Promotion();
    /**
     * Initializes the controller class.
     */
    @Override
       public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //cbnCategories.setItems(options);
   
    }
    
    @FXML
    private void Modifiy(ActionEvent event) throws SQLException, IOException {
   

 
           
        
        
           if (date_debut.getValue()==null)
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("Alerte Date");
               alert.setContentText("Veuillez verifier la  date de circulation");
               alert.showAndWait();
               
           }
             
              
           if (date_fin.getValue()==null)
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("Alerte Date");
               alert.setContentText("Veuillez verifier la  date de circulation");
               alert.showAndWait();
               
           }
             
               
       
           
      if (Integer.parseInt(pourcentage.getText())<10 || (Integer.parseInt(pourcentage.getText())> 80))
         {
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur entre 10 et 80  !!");
            alert3.showAndWait();  
            
           }else {
                 String datedebut=date_debut.getValue().toString();
        String datef=date_fin.getValue().toString();
            String datePu=date_debut.getValue().toString();
                                
ev.setDate_debut(java.sql.Date.valueOf(datePu));

ev.setDate_fin(java.sql.Date.valueOf(datef));
            ev.setPourcentage(Integer.parseInt(pourcentage.getText()));
          

            
                      

            //a mmodifier !!!!
            try {

               ServicePromotion es = new ServicePromotion();
                System.out.println("azaz.............");

                System.out.println(ev.toString());
               es.Modifier(ev);
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/Promotion.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();     

        } catch (Exception ex) {
                System.out.println("ex.............");
                ex.printStackTrace();
        }
            System.out.println("Modification terminÃ©");
           
          
         //  Image image = new Image(input);
        //ImageView view = new ImageView();
        //tf_image_view.setImage(image);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Promotion enregistrÃ© avec succÃ¨s.");
        alert.setHeaderText(null);
//        alert.setContentText("L'evenement " + e.getNom() + " has been modified.");
        alert.showAndWait();
        }
    }
   
    
    
    
    void setData(int id_promo, int pourcentagee, Date datedebut, Date datefin) {
       ev.setId_promo(id_promo);
       pourcentage.setText(String.valueOf(pourcentagee));

       date_debut.getEditor().setText(String.valueOf(datedebut));
              date_fin.getEditor().setText(String.valueOf(datefin));

     
    }
}
