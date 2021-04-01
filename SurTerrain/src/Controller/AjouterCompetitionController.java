/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.competition;
import Services.Servicecompetition;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class AjouterCompetitionController implements Initializable {

    @FXML
    private TableView<competition> tvcomp;
    @FXML
    private TableColumn<?, ?> nomco;
    @FXML
    private TableColumn<?, ?> datedeb;
    @FXML
    private TableColumn<?, ?> datefn;
    @FXML
    private Button ajoutcomp;
    @FXML
    private Button mail;
    @FXML
    private TextField nomcomp;
    @FXML
    private DatePicker datedb;
    @FXML
    private Button retourner;
    @FXML
    private Button afficherbd;
    @FXML
    private DatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            settable();
            LocalDateTime date = LocalDateTime.now();
            System.out.println(date);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    public void settable() throws SQLException {

        nomco.setCellValueFactory(new PropertyValueFactory<>("nom_competition"));
        datedeb.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        Servicecompetition scom = new Servicecompetition();
        ObservableList<competition> list1 = scom.lister();
        tvcomp.setItems(list1);
        
    }
    @FXML
    private void ajoutcomp(ActionEvent event) throws SQLException {
         Servicecompetition scom = new Servicecompetition();
        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
        if (datedb.getValue().toString().isEmpty() || datefin.getValue().toString().isEmpty() || nomcomp.getText().isEmpty()) {
            alertAD.setTitle("error");
            alertAD.setHeaderText(null);
            alertAD.setContentText("vérifier les champs");
            alertAD.show();
        } else {
            if (datedb.getValue().getYear() > datefin.getValue().getYear()) {
                alertAD.setTitle("error");
                alertAD.setHeaderText(null);
                alertAD.setContentText("année incorrect!");
                alertAD.show();

            } else {
                if (datedb.getValue().getDayOfMonth() > datefin.getValue().getDayOfMonth() && datedb.getValue().getMonthValue() > datefin.getValue().getMonthValue()) {
                    alertAD.setTitle("error");
                    alertAD.setHeaderText(null);
                    alertAD.setContentText("Jours incorrect!");
                    alertAD.show();

                } else {
                    if (datedb.getValue().getMonthValue() > datefin.getValue().getMonthValue()) {
                        alertAD.setTitle("error");
                        alertAD.setHeaderText(null);
                        alertAD.setContentText("mois incorrect!");
                        alertAD.show();
                    } else {
                        Date db = Date.valueOf(datedb.getValue());
                        Date df = Date.valueOf(datefin.getValue());
                        scom.ajouter(new competition(nomcomp.getText(), db, df));
                        JOptionPane.showMessageDialog(null, "competiton ajouté");
                        settable();
                    }
                }
            }
        }

    }

    @FXML
    private void smtp(ActionEvent event) throws DocumentException, SQLException, IOException {
        String ma = null;
        Servicecompetition scom = new Servicecompetition();
        competition comp = tvcomp.getSelectionModel().getSelectedItem();
        ma = scom.getmail(comp);
        System.out.println(ma);
    }

    @FXML
    private void retour(ActionEvent event) {
        Stage stage = (Stage) retourner.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void affichebd(ActionEvent event) {
         try {
            competition co = tvcomp.getSelectionModel().getSelectedItem();
            
            int idcom = co.getId_competition();
            
            String idco = String.valueOf(idcom);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ClubCompetition.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();

            ClubCompetitionController ccc = loader.getController();

            ccc.settext(idco);

        } catch (IOException ex) {
            System.out.println("lena ?");
            System.out.println(ex.getMessage());
        }
    }
    public boolean dell() throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        
        competition comp = new competition();
        String datef = comp.getDate_fin().toString();
        Servicecompetition scomp = new Servicecompetition();
        
            
        if (datef == date.toString()) {
            scomp.supprimercomp(comp);
           
            return true;
        
        }
        return false;
        
    }
    
}
