/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Clubcomp;
import Services.ServiceClubComp;
import Services.Serviceclub;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class ClubCompetitionController implements Initializable {

    @FXML
    private Label compnom;
    @FXML
    private TextField idco;
    @FXML
    private Button btnaff;
    @FXML
    private TableView<Clubcomp> tvc;
    @FXML
    private TableColumn<?, ?> nomcl;
    @FXML
    private Button quitte;
    @FXML
    private ComboBox<String> clubs;
    @FXML
    private Button ajouter;
    Connection cnx = DataSource.getInstance().getCnx();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Serviceclub sc = new Serviceclub();
        ObservableList<String> listc = sc.listercj();
        clubs.setItems(listc);

    }    

     void settext(String x) {
        idco.setText(x);
    }
    @FXML
    private void afficherclubs(ActionEvent event) {
        String comp = idco.getText();
        int id_c = Integer.parseInt(comp);

        try {

            nomcl.setCellValueFactory(new PropertyValueFactory<>("nom_club"));

            ServiceClubComp scc = new ServiceClubComp();
            ObservableList<Clubcomp> listclub = scc.listnomclub(id_c);
            tvc.setItems(listclub);

            compnom.setText(scc.getnomcompetition(id_c));
            compnom.setVisible(true);

        } catch (SQLException ex) {
            System.out.println("aaaaaa");
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void quittee(ActionEvent event) {
        Stage stage = (Stage) quitte.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        int idcompetition = Integer.parseInt(idco.getText());
        ServiceClubComp scc = new ServiceClubComp();
        String nc = null;
        nc = clubs.getSelectionModel().getSelectedItem();
        String req = "SELECT id_club FROM `club` WHERE nom_club like '" + nc + "'";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int idclub = rs.getInt(1);
        if (clubs.getSelectionModel().getSelectedIndex()==-1) {
            JOptionPane.showMessageDialog(null, "selectionné un club ");
        } else {
            scc.ajout(new Clubcomp(idcompetition, idclub, nc));
        }
        tvc.refresh();
    }
    
}
