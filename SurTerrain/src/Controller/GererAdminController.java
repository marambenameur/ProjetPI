/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import  Utils.DataSource;
import Models.Admin;
import Services.ServiceAdmin;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class GererAdminController implements Initializable {

    @FXML
    private TextField Jrecherche;
    @FXML
    private FontAwesomeIcon iconrecherche;
    @FXML
    private Button btajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btsupprimer;
    @FXML
    private TableView<Admin> tvadmin;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private FontAwesomeIcon downLoad;
    @FXML
    private FontAwesomeIcon refadmin;
    Connection cnx = DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            nom.setCellValueFactory(new PropertyValueFactory<>("nom")); 
     prenom.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
     username.setCellValueFactory(new PropertyValueFactory<>("username"));
     email.setCellValueFactory(new PropertyValueFactory<>("email"));
      ServiceAdmin ad = new ServiceAdmin() ;
     ObservableList<Admin> list = ad.lister();
     tvadmin.setItems(list);

     FilteredList<Admin> filtredata = new FilteredList<>(list,b ->true);
     Jrecherche.textProperty().addListener((Observable , oldValue ,newValue)-> {
         filtredata.setPredicate(admin -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (admin.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (admin.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (admin.getUsername().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (admin.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }
             
         });
     });
     
    SortedList<Admin> sorteddata = new SortedList<>(filtredata); 
    sorteddata.comparatorProperty().bind(tvadmin.comparatorProperty());
    tvadmin.setItems(sorteddata);
    }    

    @FXML
    private void AddAdmin(ActionEvent event) throws IOException {
          Parent root =FXMLLoader.load(getClass().getResource("/GUI/AddAdmin.fxml")); 
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
    }

    @FXML
    private void EditAdmin(ActionEvent event) throws IOException {
            Admin selected= tvadmin.getSelectionModel().getSelectedItem();
        if (selected== null)
        {
            System.out.println("Aucun Admin séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Admin séléctionné");
            alert.showAndWait();
        }else
        {
          String id =String.valueOf(selected.getId());
         FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Gui/ModifyAdmin.fxml"));
          Parent root =loader.load();
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
          ModifyAdminController controller =loader.getController();
          controller.showInforamtion(selected.getNom(),selected.getPrenom(),selected.getUsername(),selected.getEmail(),id);  
            
        }
    }

    @FXML
    private void DeleteAdmin(ActionEvent event) {
                 Admin selected= tvadmin.getSelectionModel().getSelectedItem();
        
        if(selected==null){
        
           System.out.println("Aucun Admin séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Admin séléctionné");
            alert.showAndWait();
     
        }else {
           ServiceAdmin st = new ServiceAdmin();
            String nom=selected.getNom();
            
           
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Admin");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer l'Admin " +" "+ selected.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    st.supprimer(selected);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("admin supprimé!");
                    alert1.showAndWait();
                  //  UpdateAdmin();
    }}
    }

    @FXML
    private void telecharger(MouseEvent event) throws FileNotFoundException, SQLException {
        
        try {
            String file_name="C:\\PDF\\produit.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from admin"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("nom"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("prenom"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("username"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("email"));
            t.addCell(c4);
            t.setHeaderRows(1);

            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier 😃");
    }

    @FXML
    private void UpdateAdmin(MouseEvent event) {
             nom.setCellValueFactory(new PropertyValueFactory<>("nom")); 
     prenom.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
     username.setCellValueFactory(new PropertyValueFactory<>("username"));
     email.setCellValueFactory(new PropertyValueFactory<>("email"));
      ServiceAdmin ad = new ServiceAdmin() ;
     ObservableList<Admin> list = ad.lister();
     tvadmin.setItems(list);
    FilteredList<Admin> filtredata = new FilteredList<>(list,b ->true);
     Jrecherche.textProperty().addListener((Observable , oldValue ,newValue)-> {
         filtredata.setPredicate(admin -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (admin.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (admin.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (admin.getUsername().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (admin.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }
             
         });
     });
     
    SortedList<Admin> sorteddata = new SortedList<>(filtredata); 
    sorteddata.comparatorProperty().bind(tvadmin.comparatorProperty());
    tvadmin.setItems(sorteddata);
    }
    
}
