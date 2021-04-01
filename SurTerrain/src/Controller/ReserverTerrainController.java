/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Reservation;
import Models.Terrain;
import Services.ServiceReservation;
import Services.ServiceTerrain;
import Utils.DataSource;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class ReserverTerrainController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private JFXDatePicker tfdate;
    @FXML
    private JFXTimePicker tfHeure;
    @FXML
    private TextField tfrechercheTerrain;
    @FXML
    private Button btnRecherche;
    @FXML
    private JFXTimePicker tfHeure1;
    @FXML
    private TableView<Terrain> tableView;
    @FXML
    private TableColumn<Terrain,String> Terrain;
    @FXML
    private TableColumn<Terrain,String> Adresse;
   
    
    ObservableList<Terrain> list = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Connection cnx = DataSource.getInstance().getCnx();
        
        try {
            String requete = "SELECT * FROM terrain";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
                list.add(new Terrain(rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceTerrain st = new Services.ServiceTerrain();
        Terrain.setCellValueFactory(new PropertyValueFactory<>("nomTerrain"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse")); 
        tableView.setItems(list);
    }    

    @FXML
    private void ajouterReservation(MouseEvent event) {
        
        ServiceReservation sr = new ServiceReservation();
        Terrain selected = tableView.getSelectionModel().getSelectedItem();
        String nom = selected.getNomTerrain();  
        System.out.println(nom);
        sr.ajouter2(new Reservation(Date.valueOf(tfdate.getValue()),Time.valueOf(tfHeure.getValue()),Time.valueOf(tfHeure1.getValue())),nom);
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Succés!");
                alert1.setHeaderText(null);
                alert1.setContentText("Réservation effectuée!");
                alert1.showAndWait();
    }


    @FXML
    private void retourHome(MouseEvent event) {
    }

    @FXML
    private void recercherTerrain(MouseEvent event) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM terrain";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
                list.add(new Terrain(rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        ServiceTerrain st = new Services.ServiceTerrain();

        FilteredList<Terrain> filteredData = new FilteredList<>(list, b -> true);
        tfrechercheTerrain.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Terrain -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Terrain.getNomTerrain().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Terrain.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<Terrain> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

    }
    
}
