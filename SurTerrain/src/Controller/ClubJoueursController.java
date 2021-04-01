/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Joueurs;
import Services.Serviceclubx;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class ClubJoueursController implements Initializable {

    @FXML
    private TableView<Joueurs> tv;
    @FXML
    private TableColumn<?, ?> tvnom;
    @FXML
    private TableColumn<?, ?> tvpren;
    @FXML
    private TableColumn<?, ?> tvage;
    @FXML
    private TableColumn<?, ?> tvemail;
    @FXML
    private TableColumn<?, ?> tvclub;
    @FXML
    private Label clname;
    @FXML
    private Button quitte;
    @FXML
    private TextField idcl;
    @FXML
    private Button btn_jrs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clname.setText("");
    }

    void settext(String x) {
        idcl.setText(x);
    }

    @FXML
    private void quittee(ActionEvent event) {
        Stage stage = (Stage) quitte.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void afficher(ActionEvent event) {
        String j = idcl.getText();
        int id_j = Integer.parseInt(j);

        try {

            tvnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            tvpren.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            tvage.setCellValueFactory(new PropertyValueFactory<>("Age"));
            tvemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tvclub.setCellValueFactory(new PropertyValueFactory<>("nom_club"));

            Serviceclubx cc = new Serviceclubx();
            ObservableList<Joueurs> listj = cc.listjoueurs(id_j);
            tv.setItems(listj);

            clname.setText(cc.getnomclub(id_j));
            clname.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
