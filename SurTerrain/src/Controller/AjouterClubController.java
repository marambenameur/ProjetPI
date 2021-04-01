/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.club;
import Services.Serviceclub;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class AjouterClubController implements Initializable {

    @FXML
    private Button retourner;
    @FXML
    private TextField nomc;
    @FXML
    private TextField nbrc;
    @FXML
    private TableView<club> tvclub;
    @FXML
    private TableColumn<?, ?> cnom;
    @FXML
    private TableColumn<?, ?> cnbr;
    @FXML
    private TextField Txtrch;
    @FXML
    private Button ajouterclub;
    @FXML
    private Button modifierclub;
    @FXML
    private Button supprimerclub;
    private int index;
    private String xx;
    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button affclub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateTable();
    }    

    public void UpdateTable() {
        try {
            cnom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
            cnbr.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs"));
            Serviceclub sc = new Serviceclub();
            ObservableList<club> list = sc.listerc();
            tvclub.setItems(list);

        } catch (SQLException ex) {
            System.out.println("met3abeeeech");
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void retour(ActionEvent event) {
              Stage stage = (Stage) retourner.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void GetSelected(MouseEvent event) {
        index = tvclub.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            System.out.println("selection erronné");
            return;
        }
        nomc.setText(cnom.getCellData(index).toString());
        nbrc.setText(cnbr.getCellData(index).toString());
    }

    @FXML
    private void rech(KeyEvent event) throws SQLException {
         Serviceclub sc = new Serviceclub();
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
        cnbr.setCellValueFactory(new PropertyValueFactory<>("nbr_joueurs"));
        ObservableList<club> listclub = sc.listrecherche(Txtrch.getText());
        tvclub.setItems(listclub);
    }

    @FXML
    private void ajoutc(ActionEvent event) {
        Serviceclub sc = new Serviceclub();

        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
        if (nomc.getText().isEmpty() || nbrc.getText().isEmpty()) {

            alertAD.setTitle("error");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Veuiller remplire tous les champs");
            alertAD.show();
        } else {
            int nbr = Integer.parseInt(nbrc.getText());
            if (nbr > 11) {
                alertAD.setTitle("error");
                alertAD.setHeaderText(null);
                alertAD.setContentText("Verifier le nombre de joueurs");
                alertAD.show();
            } else {
                sc.ajouter(new club(nomc.getText(), nbr));
                JOptionPane.showMessageDialog(null, "club ajouté");
                UpdateTable();
            }

        }
    }

    @FXML
    private void modifc(ActionEvent event) {
        Serviceclub sc = new Serviceclub();

        club c = tvclub.getSelectionModel().getSelectedItem();
        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
        if (nomc.getText().isEmpty() || nbrc.getText().isEmpty()) {

            alertAD.setTitle("error");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Veuiller remplire tous les champs");
            alertAD.show();
        } else {
            int nbr = Integer.parseInt(nbrc.getText());
            if (nbr > 11) {
                alertAD.setTitle("error");
                alertAD.setHeaderText(null);
                alertAD.setContentText("Verifier le nombre de joueurs");
                alertAD.show();
            } else {
                sc.modifier(new club(c.getid_club(), nomc.getText(), nbr));
                JOptionPane.showMessageDialog(null, "club modifier");
                UpdateTable();
            }
        }
    }

    @FXML
    private void suppc(ActionEvent event) {
        Serviceclub sc = new Serviceclub();
        club c = tvclub.getSelectionModel().getSelectedItem();
        sc.supprimer(c);
        UpdateTable();
    }

    @FXML
    private void afficherclubs(ActionEvent event) {
        try {
            club c = tvclub.getSelectionModel().getSelectedItem();
            int x = c.getid_club();
            String idcl = String.valueOf(x);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ClubJoueurs.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();

            ClubJoueursController cc = loader.getController();
            cc.settext(idcl);

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    
}
