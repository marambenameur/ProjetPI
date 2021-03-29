/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.esprit.models.Categorie;
import com.esprit.services.ServiceCategorie;
import javafx.scene.Scene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherCategoriesController implements Initializable {
 @FXML
    private TableView<Categorie> tabc;
               @FXML 
   private TableColumn<Categorie , String>TextType;
   
                    ServiceCategorie cs=new ServiceCategorie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Categorie>data=FXCollections.observableArrayList();

   data.addAll(cs.readAll());
   System.out.println(data.size());

            TextType.setCellValueFactory(new PropertyValueFactory<
                    
                    >("type"));
                      
        
      
        tabc.setItems(data); 
    }   
    
 @FXML
    
        private void AddCategoriesAction(ActionEvent event) {
        try {   
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/Admin/AddCategories.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
        
        
         @FXML
    
        private void UpdateCategoriesAction(ActionEvent event) {
   try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("modifierCatgeories.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
     
 ModifierCatgeoriesController test = pane.getController();
         int as=tabc.getSelectionModel().getSelectedItem().getId();

test.setData(
                                         
                 

              tabc.getSelectionModel().getSelectedItem().getType(),
               
                  tabc.getSelectionModel().getSelectedItem().getId()
             
             );  
    } catch(Exception e)
    {
     System.out.println("eer");
}
            
          
    }

  
        
    private void setCellTableNormale() {
            
  ObservableList<Categorie>data=FXCollections.observableArrayList();

   data.addAll(cs.readAll());
   System.out.println(data.size());

            TextType.setCellValueFactory(new PropertyValueFactory<>("type"));
                       
        
      
        tabc.setItems(data); 
    }
    
    




    @FXML
    private void deleteCategoriesAction(ActionEvent event) 
    {
    ServiceCategorie cs = new ServiceCategorie();
        Categorie c = (Categorie) tabc.getSelectionModel().getSelectedItem();
        cs.delete(c);
   
                    tabc.getItems().clear();
cs.readAll();                    
setCellTableNormale();
        cs.readAll();
    
    }        
        
        
        
}