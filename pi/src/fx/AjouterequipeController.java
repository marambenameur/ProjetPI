/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import datasource.DataSource;
import entity.Equipe;
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
import service.ServiceEquipe;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Afficher(ActionEvent event) {
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
    }
    

