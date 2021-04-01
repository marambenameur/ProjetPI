/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataSource;
import Models.Client;
import Services.ServiceClient;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.management.Notification;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class GererClientController implements Initializable {

    @FXML
    private TextField JrechercheC;
    @FXML
    private FontAwesomeIcon iconrechercheC;
    @FXML
    private Button btajouterC;
    @FXML
    private Button btmodifierC;
    @FXML
    private Button btsupprimerC;
    @FXML
    private TableView<Client> tvclient;
    @FXML
    private TableColumn<?, ?> nomC;
    @FXML
    private TableColumn<?, ?> prenomC;
    @FXML
    private TableColumn<?, ?> addressC;
    @FXML
    private TableColumn<?, ?> numtelC;
    @FXML
    private TableColumn<?, ?> emailC;
    @FXML
    private FontAwesomeIcon downLoadClient;
    @FXML
    private FontAwesomeIcon refclient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         nomC.setCellValueFactory(new PropertyValueFactory<>("nom")); 
     prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
     addressC.setCellValueFactory(new PropertyValueFactory<>("address")); 
     numtelC.setCellValueFactory(new PropertyValueFactory<>("numTelC")); 
     emailC.setCellValueFactory(new PropertyValueFactory<>("email")); 
     ServiceClient cl = new ServiceClient() ;
     ObservableList<Client> list1 = cl.lister();
     tvclient.setItems(list1);
     
     
          FilteredList<Client> filtredata1 = new FilteredList<>(list1,b->true);
           JrechercheC.textProperty().addListener((Observable , oldValue ,newValue)-> {
           filtredata1.setPredicate(client -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (client.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (client.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (client.getAddress().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else if (String.valueOf(client.getNumTelC()).contains(lowercasefilter))
             {
                 return true ;
             
             }else  if (client.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }  
         });
     });
     
    SortedList<Client> sorteddata1 = new SortedList<>(filtredata1); 
    sorteddata1.comparatorProperty().bind(tvclient.comparatorProperty());
    tvclient.setItems(sorteddata1);
    }    

    @FXML
    private void AddClient(ActionEvent event) throws IOException {
         Parent root =FXMLLoader.load(getClass().getResource("/GUI/AddClient.fxml")); 
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
    }

    @FXML
    private void EditClient(ActionEvent event) throws IOException {
          Client selected= tvclient.getSelectionModel().getSelectedItem();
      
        if (selected== null)
        {
            System.out.println("Aucun client séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Client séléctionné");
            alert.showAndWait();
        }else
        {
          String id =String.valueOf(selected.getId());
          String num =String.valueOf(selected.getNumTelC());
         FXMLLoader loader  = new FXMLLoader(getClass().getResource("/GUI/ModifyClient.fxml"));
          Parent root =loader.load();
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
          ModifyClientController controller =loader.getController();
          controller.showInforamtion(selected.getNom(),selected.getPrenom(),selected.getAddress(),selected.getEmail(),id,num);  
         
        }
  
    }

    @FXML
    private void DeleteClient(ActionEvent event) {
         Client selected= tvclient.getSelectionModel().getSelectedItem();
        
        if(selected==null){
        
           System.out.println("Aucun Client séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Client séléctionné");
            alert.showAndWait();
     
        }else {
           ServiceClient cl = new ServiceClient();
            String nom=selected.getNom();
           
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Client");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer le Client " +" "+ selected.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    cl.supprimer(selected);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Client supprimé!");
                    alert1.showAndWait();
                      Notifications n = Notifications.create()
                                                                        .title("SUCCESS")
                                                                        .text("  Client Supprimer")
                                                                        .position(Pos.TOP_CENTER)
                                                                        .hideAfter(javafx.util.Duration.seconds(5));
                                                                         n.darkStyle();
                                                                         n.show();  
    }
        }}

    @FXML
    private void telechargerClient(MouseEvent event) throws SQLException, FileNotFoundException {
                 try {
            String file_name="C:\\PDF\\Client.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from client"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("nomC"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("prenomC"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("addressC"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("numTelC"));
            t.addCell(c4);
             PdfPCell c5 = new PdfPCell(new Phrase("email"));
            t.addCell(c5);
            t.setHeaderRows(1);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                t.addCell(rs.getString(5));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
      //  Notification.showNotif("Pdf Téléchargé","vérifier votre dossier 😃");
    }

    @FXML
    private void UpdateClient(MouseEvent event) {
         nomC.setCellValueFactory(new PropertyValueFactory<>("nom")); 
     prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
     addressC.setCellValueFactory(new PropertyValueFactory<>("address")); 
     numtelC.setCellValueFactory(new PropertyValueFactory<>("numTelC")); 
     emailC.setCellValueFactory(new PropertyValueFactory<>("email")); 
       ServiceClient cl = new ServiceClient() ;
     ObservableList<Client> list1 = cl.lister();
     tvclient.setItems(list1);
      FilteredList<Client> filtredata1 = new FilteredList<>(list1,b->true);
           JrechercheC.textProperty().addListener((Observable , oldValue ,newValue)-> {
           filtredata1.setPredicate(client -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (client.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (client.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (client.getAddress().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else if (String.valueOf(client.getNumTelC()).contains(lowercasefilter))
             {
                 return true ;
             
             }else  if (client.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }  
         });
     });
     
    SortedList<Client> sorteddata1 = new SortedList<>(filtredata1); 
    sorteddata1.comparatorProperty().bind(tvclient.comparatorProperty());
    tvclient.setItems(sorteddata1);
    }
    
}
