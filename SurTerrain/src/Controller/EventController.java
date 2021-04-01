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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.teamdev.jxmaps.i;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author maram
 */
public class EventController implements Initializable {
 @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
     private TableColumn<Event, String> tcNom;
    @FXML
     private TableColumn<Event, Categorie> tcCategories;
    @FXML
     private TableColumn<Event, String> tcDate_event;
    @FXML
     private TableColumn<Event, String> tcDescription;
    @FXML
     private TableColumn<Event, String> tcLieu_event;
    @FXML
     private TableColumn<Event, String> tcPrice;
    @FXML
    private TableView<Event> tvEvent;
    private int as ;
    @FXML
    private Button id_mail;
   
    @FXML
    private TextField filterFiled;
    @FXML
    private Button Modify;
    @FXML
    private Button print;
    @FXML
    private Button QR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ServiceEvent sousCatService= new ServiceEvent();
        ArrayList<Event> lesSousCategories = new ArrayList<>();
        try {
              
            lesSousCategories = (ArrayList<Event>) sousCatService.readAl();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                ServiceCategorie catService = new ServiceCategorie();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
        tvEvent.setItems(obs);         
            
            
        System.out.println(obs);
        List<Event> le = new ArrayList<>();

        tcNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        tcCategories.setCellValueFactory(new PropertyValueFactory<>("Categories_id"));
        tcDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tcLieu_event.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        int nbe=tvEvent.getItems().size();
   FilteredList<Event> filteredData = new FilteredList<>(obs, b -> true);
      {
			filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getCategories_id().getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				
                                } else if (employee.getLieu_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                  else if (String.valueOf(employee.getDescription()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                      else if (String.valueOf(employee.getPrix()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                            else if (String.valueOf(employee.getDate_event()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
        
        	});
        SortedList<Event> sortedData = new SortedList<>(filteredData);
      
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvEvent.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvEvent.setItems(sortedData);

                            
    } 
                                }
    
    
    @FXML
    private void ajouterEventAction(ActionEvent event)  {
            
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/GUI/AddEvent.fxml"));
        try {
            Parent root = loader.load();
            add.getScene().setRoot(root);
                            
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        private void TableEvent(ActionEvent event) {
        
        
        tvEvent.getSelectionModel().getSelectedItem();   
     }
        
  @FXML
    private void modifierEventAction(ActionEvent event) throws IOException  {
             Event e=tvEvent.getSelectionModel().getSelectedItem();
      System.out.println(e);
if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
     
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/GUI/ModifyEvent.fxml"));
        Scene scene=new Scene(loader.load());
        

        ModifyEventController mc= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        as=tvEvent.getSelectionModel().getSelectedItem().getId();
        String Nom = tvEvent.getSelectionModel().getSelectedItem().getNom();
        Date Date_event = tvEvent.getSelectionModel().getSelectedItem().getDate_event();
        String Description = tvEvent.getSelectionModel().getSelectedItem().getDescription();
        String Lieu_event = tvEvent.getSelectionModel().getSelectedItem().getLieu_event();
        Double Prix = tvEvent.getSelectionModel().getSelectedItem().getPrix();
        
        
        mc.setData(
                 tvEvent.getSelectionModel().getSelectedItem().getId(),
                 tvEvent.getSelectionModel().getSelectedItem().getNom(),
                 tvEvent.getSelectionModel().getSelectedItem().getDate_event(),
                 tvEvent.getSelectionModel().getSelectedItem().getDescription(),
                 tvEvent.getSelectionModel().getSelectedItem().getLieu_event(),
                 tvEvent.getSelectionModel().getSelectedItem().getPrix()
        );
        } 
    
}
        
   
    
    
    public void loadData() throws SQLException{
    ObservableList<Event> dataa = null;

    dataa = FXCollections.observableArrayList(new ServiceEvent().lister());
    }
    
   
    
        private void SupprimerEventAction(ActionEvent event) throws SQLException, MalformedURLException  {
            Event e=tvEvent.getSelectionModel().getSelectedItem();
        
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
            ServiceEvent es=new ServiceEvent();
            String nom_P=e.getNom();
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer l'évenement " +" "+ e.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    es.Supprimer(e);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Evenement supprimé!");

                    alert1.showAndWait();
                    
                      setCellTableNormale();
       es.readAl() ;
       
                try {
                    Notification.sendNotification("Success", "evenement a ete suprimee avec success",TrayIcon.MessageType.INFO);
                } catch (AWTException ex) {
                    Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
                }
                
         
        }
       
        }
        
               
    

/*
    @FXML
    private void mail(ActionEvent event) throws IOException {
      //mail m=new mail();
     // m.message("Maram.benameur@esprit.tn","nouveau evenement");
      
    } */

   @FXML
private void mail(ActionEvent event)  {
    mail m =new mail();
           m.message(("maram.benameur@esprit.tn"),"Un nouveau evenemnt, consulter notre application pour voire notre nouveau evenement  ");
                Notifications n = Notifications.create()
                                    .title("SUCCESS")
                                    .text(" Mail Envoyer ")
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                            n.darkStyle();
                            n.show();
            
   }
    @FXML
    private void generatepdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException
    {  Pdf pd=new Pdf();
       System.out.println(" hello");
        try{
        pd.GeneratePdf("list of event");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void setCellTableNormale() {
        
       ServiceEvent sousCatService= new ServiceEvent();
        ArrayList<Event> lesSousCategories = new ArrayList<>();
        try {
              
            lesSousCategories = (ArrayList<Event>) sousCatService.readAl();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                ServiceCategorie catService = new ServiceCategorie();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
        tvEvent.setItems(obs);         
            
            
        System.out.println(obs);
        List<Event> le = new ArrayList<>();
/* ServiceEvent sousCatService= new ServiceEvent();
     ArrayList<Event> lesSousCategories = new ArrayList<>();
     lesSousCategories = (ArrayList<Event>) sousCatService.readll();
     System.out.println(lesSousCategories);
     for(int i=0;i<lesSousCategories.size();i++){
     ServiceCategorie catService = new ServiceCategorie();
     lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
     lesSousCategories.get(i).getCategories_id().getId()));
     System.out.println(lesSousCategories);
     }
     ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
     tab.setItems(obs);
     System.out.println(obs);
     Categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));*/
        tcNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        tcCategories.setCellValueFactory(new PropertyValueFactory<>("Categories_id"));
        tcDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tcLieu_event.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        int nbe=tvEvent.getItems().size();
   FilteredList<Event> filteredData = new FilteredList<>(obs, b -> true);
      {
			filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getCategories_id().getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				
                                } else if (employee.getLieu_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                  else if (String.valueOf(employee.getDescription()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                      else if (String.valueOf(employee.getPrix()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                            else if (String.valueOf(employee.getDate_event()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
        
        	});
        SortedList<Event> sortedData = new SortedList<>(filteredData);
      
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvEvent.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvEvent.setItems(sortedData);

                            
    } 
                                }

    @FXML
    private void SupprimerEventAction(MouseEvent event) {
    }

    @FXML
    private void Qr(ActionEvent event) {
        try {
//            String qrCodeData = tcNom.getText();
            String qrCodeData ="coupe de monde";
            String filePath = "C:\\chillyfacts.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            Notifications n = Notifications.create()
                    .title("SUCCESS")
                    .text(" QR Code image created successfully! ")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(javafx.util.Duration.seconds(5));
            n.darkStyle();
            n.show();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    
    }


                
            
    
    
    
    



   
