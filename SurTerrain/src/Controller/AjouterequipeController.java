/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataSource;
import Models.Equipe;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Services.ServiceEquipe;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AjouterequipeController implements Initializable {

    @FXML
    private TableView<Equipe> table;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private Button Afficher;
ObservableList<Equipe> list = FXCollections.observableArrayList();
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmod;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM equipe";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Equipe(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceEquipe se = new ServiceEquipe();
        id.setCellValueFactory(new PropertyValueFactory<>("idequipe"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        table.setItems(list);
    }    

    @FXML
    private void Afficher(ActionEvent event) {
        
//        Connection cnx = DataSource.getInstance().getCnx();
//        try {
//            String requete = "SELECT * FROM equipe";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                list.add(new Equipe(rs.getInt(1), rs.getString(2), rs.getInt(3)));
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        ServiceEquipe se = new ServiceEquipe();
//        id.setCellValueFactory(new PropertyValueFactory<>("idequipe"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//        
//        table.setItems(list);
    }

  

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    
    }

    @FXML
    private void btnaction(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnmod) {
            loadStage("/GUI/modifierequipe.fxml");
        } else if (mouseEvent.getSource() == btnajouter) {
            loadStage("/GUI/afficherequipe.fxml");
        }
           else if (mouseEvent.getSource() == btnsupp) {
            loadStage("/GUI/supprimerequipe.fxml");}
    }

   

}
    

