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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class RechercheController implements Initializable {

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
    private Button recherche;
    ObservableList<Demande> list = FXCollections.observableArrayList();
    @FXML
    private TextField rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void recherche(MouseEvent event) {
        
           Connection cnx = DataSource.getInstance().getCnx();
        list.clear();
        String requete = "select * from demande where  date LIKE " + rech.getText() ;

        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                list.add(new Demande(rst.getInt(1), rst.getString(2), rst.getInt(3),rst.getInt(4)));
            }
        } catch (SQLException ex) {
            System.out.println("aucun terrain disponible!");
        }
        ServiceDemande st = new ServiceDemande();
        rech.getText();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        terrain.setCellValueFactory(new PropertyValueFactory<>("idterrain"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("idequipe"));
        table.setItems(list);
    
    }
    
}
