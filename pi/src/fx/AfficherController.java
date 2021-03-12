/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import datasource.DataSource;
import entity.Demande;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<Demande> table;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> terrain;
    @FXML
    private TableColumn<?, ?> equipe;
    @FXML
    private Button afficher;
    ObservableList<Demande> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM demande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceDemande sp = new ServiceDemande();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        terrain.setCellValueFactory(new PropertyValueFactory<>("idterrain"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("idequipe"));
        table.setItems(list);
    }
    
}
