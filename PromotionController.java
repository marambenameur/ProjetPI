/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.esprit.utils.DataSource;
import com.esprit.models.Promotion;
import com.esprit.services.ServiceCategorie;
import com.esprit.services.ServiceEvent;
import com.esprit.services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialArray;

/**
 * FXML Controller class
 *
 * @author infoMix
 */
public class PromotionController implements Initializable {
    
     int index = -1;
    @FXML private TableView<Promotion> tvPromo;
    @FXML private TableColumn<Promotion, Integer> pourcentage;
    @FXML private TableColumn<Promotion, Date> date_debut;
    @FXML private TableColumn<Promotion,Date > date_fin;
    @FXML private Button add;
    @FXML private Button Modifiy;
    @FXML private Button remove;
    @FXML private TextField recherche;
     private ObservableList<Promotion> promoList;
    ServicePromotion es = new ServicePromotion();
       private int as ;
    @FXML
    private Button add1;
    @FXML
    private Label fx_recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
     
        
  ServicePromotion es = new ServicePromotion();
        
        List<Promotion> le = new ArrayList<>();
        le = (ArrayList<Promotion>) es.Afficher();
        
        ObservableList<Promotion> data = FXCollections.observableArrayList(le);
         
           
            System.out.println(data);
        pourcentage.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("pourcentage"));
        date_debut.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("date_fin"));
      
        int nbe=tvPromo.getItems().size();
          tvPromo.setItems(data);
        
    } 
            @FXML

    private void ajouterEventAction(ActionEvent event)  {
            
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddPromotion.fxml"));
        try {
            Parent root = loader.load();
            add.getScene().setRoot(root);
                            
        } catch (IOException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        private void TableEvent(ActionEvent event) {
        
        
        tvPromo.getSelectionModel().getSelectedItem();   
     }
     
        @FXML
    private void modifierEventAction(ActionEvent event) throws IOException  {
             Promotion e=tvPromo.getSelectionModel().getSelectedItem();
      System.out.println(e);
if(e==null){
        
           System.out.println("Aucun promotion séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun promotion séléctionné");

            alert.showAndWait();
     
        }else {
 
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ModifyPromotion.fxml"));
        Scene scene=new Scene(loader.load());
        

      
    ModifyPromotionController mc= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        as=tvPromo.getSelectionModel().getSelectedItem().getId_promo();
        int pourcentage = tvPromo.getSelectionModel().getSelectedItem().getPourcentage();
        Date date_debut = tvPromo.getSelectionModel().getSelectedItem().getDate_debut();
        Date date_fin = tvPromo.getSelectionModel().getSelectedItem().getDate_fin();
      
        mc.setData(
                 tvPromo.getSelectionModel().getSelectedItem().getId_promo(),
                 tvPromo.getSelectionModel().getSelectedItem().getPourcentage(),
                 tvPromo.getSelectionModel().getSelectedItem().getDate_debut(),
                 tvPromo.getSelectionModel().getSelectedItem().getDate_fin()
                
        );
      
}
        }
  
    private void setCellTableNormale() throws SQLException { 
     ObservableList<Promotion>data=FXCollections.observableArrayList();

   data.addAll(es.Afficher());
   System.out.println(data.size());
        System.out.println(pourcentage);
        loadData();

            pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentqge"));
                       
         date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));

            date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
        tvPromo.setItems(data); 
        ObservableList<Promotion>usersList = FXCollections.observableArrayList();
       
          FilteredList<Promotion> filteredData = new FilteredList<>(promoList, b -> true);
          int nbe=tvPromo.getItems().size();
 
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Promotion -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Promotion.getDate_debut()).indexOf(lowerCaseFilter) != -1 ){
                                //String.valueOf(employee.getDate_event()).indexOf(lowerCaseFilter) != -1{
					return true; // Filter matches first name.
				} else if (String.valueOf(Promotion.getDate_fin()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Promotion.getPourcentage()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Promotion> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvPromo.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvPromo.setItems(sortedData);
               
        
        
    }   
 
    
    public void loadData() throws SQLException{
    ObservableList<Promotion> dataa = null;

    dataa = FXCollections.observableArrayList(new ServicePromotion().lister());
    
    }
    
    void refresh() throws SQLException {
     
           ServicePromotion es = new ServicePromotion();
        

           ArrayList<Promotion> le;
       
            le = (ArrayList<Promotion>) es.Afficher();
            ObservableList<Promotion> data = FXCollections.observableArrayList(le);
            pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
       
            date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));

            date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
         
            tvPromo.setItems(data);
       
           
    }
            @FXML

        private void SupprimerEventAction(ActionEvent event) throws SQLException {
            Promotion e=tvPromo.getSelectionModel().getSelectedItem();
        
        if(e==null){
        
           System.out.println("Aucun Promotion séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Promotion séléctionné");

            alert.showAndWait();
     
        }else {
            ServicePromotion es=new ServicePromotion();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Promotion");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer promotion ");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    es.Supprimer(e);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Promotion supprimé!");

                    alert1.showAndWait();
                             loadData();
         refresh();
                }
            } catch (Exception ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
            }
        

        }
        
        }
         private void EnvoyerEventAction(ActionEvent event)  {
            
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                                .getResource("Mail.fxml"));
        try {
            Parent root = loader.load();
            add.getScene().setRoot(root);
                            
        } catch (IOException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
         @FXML
           private void EnvoyerMailAction(ActionEvent event)  {
            
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                                .getResource("Main.fxml"));
        try {
            Parent root = loader.load();
            add.getScene().setRoot(root);
                            
        } catch (IOException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       /* public void searching() {
        // TODO
       
        pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        ObservableList<Promotion>usersList = FXCollections.observableArrayList();
       
          FilteredList<Promotion> filteredData = new FilteredList<>(promoList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Promotion -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Promotion.getDate_debut().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Promotion.getDate_fin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Promotion.getPourcentage()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Promotion> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvPromo.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvPromo.setItems(sortedData);
               
        
    }   */
}
   
