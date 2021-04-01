/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataSource;
import Models.Adherant;
import Services.ServiceAdherant;
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
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class GererAdherantController implements Initializable {

    @FXML
    private TextField JrechercheA;
    @FXML
    private FontAwesomeIcon iconrechercheA;
    @FXML
    private Button btajouterA;
    @FXML
    private Button btmodifierA;
    @FXML
    private Button btsupprimerA;
    @FXML
    private TableColumn<?, ?> addressA;
    @FXML
    private TableColumn<?, ?> NumtelA;
    @FXML
    private TableColumn<?, ?> nomTerain;
    @FXML
    private TableColumn<?, ?> emailA;
    @FXML
    private FontAwesomeIcon downLoadAdherant;
    @FXML
    private FontAwesomeIcon refadherant;
    @FXML
    private TableView<Adherant> tvadherant;
    @FXML
    private TableColumn<?, ?> nomA;
    @FXML
    private TableColumn<?, ?> prenomA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                 nomA.setCellValueFactory(new PropertyValueFactory<>("nom")); 
         prenomA.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
         addressA.setCellValueFactory(new PropertyValueFactory<>("email") );
         emailA.setCellValueFactory(new PropertyValueFactory<>("Address"));
         nomTerain.setCellValueFactory(new PropertyValueFactory<>("nomTerain"));
         NumtelA.setCellValueFactory(new PropertyValueFactory<>("numTel"));
    ServiceAdherant Adh = new ServiceAdherant() ;
    ObservableList<Adherant> list2 =  Adh.lister();
     tvadherant.setItems(list2);
     
     
           FilteredList<Adherant> filtredata2 = new FilteredList<>(list2,b->true);
           JrechercheA.textProperty().addListener((Observable , oldValue ,newValue)-> {
           filtredata2.setPredicate(adherant -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (adherant.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (adherant.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (adherant.getAddress().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (adherant.getNomTerrain().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else if (String.valueOf(adherant.getNumTel()).contains(lowercasefilter))
             {
                 return true ;
             
             }else  if (adherant.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }  
         });
     });
     
    SortedList<Adherant> sorteddata2 = new SortedList<>(filtredata2); 
    sorteddata2.comparatorProperty().bind(tvadherant.comparatorProperty());
    tvadherant.setItems(sorteddata2);
    }    

    @FXML
    private void AddAdherant(ActionEvent event) throws IOException {
              Parent root =FXMLLoader.load(getClass().getResource("/GUI/AddAdherant.fxml")); 
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
    }

    @FXML
    private void EditAdherant(ActionEvent event) throws IOException {
                Adherant selected= tvadherant.getSelectionModel().getSelectedItem();
        if (selected== null)
        {
            System.out.println("Aucun Adherant séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Adherant séléctionné");
            alert.showAndWait();
        }else
        {
          String id =String.valueOf(selected.getId());
          String numTel =String.valueOf(selected.getNumTel());
          //****
         FXMLLoader loader  = new FXMLLoader(getClass().getResource("/GUI/ModifyAdherant.fxml"));
          Parent root =loader.load();
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
          ModifyAdherantController controller =loader.getController();
          controller.showInforamtion(selected.getNom(),selected.getPrenom(),selected.getEmail(),numTel,selected.getNomTerrain(),selected.getAddress(),id);  
            
        }
    }

    @FXML
    private void DeleteAdherant(ActionEvent event) {
                         Adherant selected= tvadherant.getSelectionModel().getSelectedItem();
        
        if(selected==null){
        
           System.out.println("Aucun Adherant séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Adherant séléctionné");
            alert.showAndWait();
     
        }else {
           ServiceAdherant ad = new ServiceAdherant();
            String nom=selected.getNom();
           
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Adherant");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer l'Adherant " +" "+ selected.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    ad.supprimer(selected);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Adherant supprimé!");
                    alert1.showAndWait();
                      Notifications n = Notifications.create()
                                    .title("SUCCESS")
                                    .text("  Adherant supprimé")
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                            n.darkStyle();
                            n.show();
       //             UpdateAdherant();
                 
    }}
    }

    @FXML
    private void telechargerAdherant(MouseEvent event) throws FileNotFoundException, SQLException {
        try{
                    String file_name="C:\\PDF\\Adherant.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from adherant"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("nomA"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("prenomA"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("addressA"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("emailA"));
            t.addCell(c4);
             PdfPCell c5 = new PdfPCell(new Phrase("nomTerrainA"));
            t.addCell(c5);
             PdfPCell c6 = new PdfPCell(new Phrase("numTelA"));
            t.addCell(c6);
            t.setHeaderRows(1);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                t.addCell(rs.getString(5));
                t.addCell(rs.getString(6));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
    }

    @FXML
    private void UpdateAdherant(MouseEvent event) {
             nomA.setCellValueFactory(new PropertyValueFactory<>("nom")); 
         prenomA.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
         addressA.setCellValueFactory(new PropertyValueFactory<>("email") );
         emailA.setCellValueFactory(new PropertyValueFactory<>("Address"));
         nomTerain.setCellValueFactory(new PropertyValueFactory<>("nomTerain"));
         NumtelA.setCellValueFactory(new PropertyValueFactory<>("numTel"));
    ServiceAdherant Adh = new ServiceAdherant() ;
    ObservableList<Adherant> list2 =  Adh.lister();
     tvadherant.setItems(list2);
     
     
           FilteredList<Adherant> filtredata2 = new FilteredList<>(list2,b->true);
           JrechercheA.textProperty().addListener((Observable , oldValue ,newValue)-> {
           filtredata2.setPredicate(adherant -> {
             
             if (newValue == null || newValue.isEmpty()){
                 return true ;
             }
             
             String lowercasefilter =newValue.toLowerCase();
             if (adherant.getNom().toLowerCase().contains(lowercasefilter))
             {
                 return true;
             }else  if (adherant.getPrenom().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (adherant.getAddress().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else  if (adherant.getNomTerrain().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else if (String.valueOf(adherant.getNumTel()).contains(lowercasefilter))
             {
                 return true ;
             
             }else  if (adherant.getEmail().toLowerCase().contains(lowercasefilter))
             {
                 return true ;
             }else
             {
             return false;
             }  
         });
     });
     
    SortedList<Adherant> sorteddata2 = new SortedList<>(filtredata2); 
    sorteddata2.comparatorProperty().bind(tvadherant.comparatorProperty());
    tvadherant.setItems(sorteddata2);
    }
    
}
