/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Joueurs;
import Services.ServiceJoueurs;
import Services.Serviceclub;
import Utils.DataSource;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamidou
 */
public class AjouterJoueurController implements Initializable {

    @FXML
    private TextField nomj;
    @FXML
    private TextField prenomj;
    @FXML
    private TextField agej;
    @FXML
    private ComboBox<String> clubj;
    @FXML
    private TextField email;
    @FXML
    private TextField Txtid;
    @FXML
    private Button suppjr;
    @FXML
    private Button ajoutjr;
    @FXML
    private Button rfr;
    @FXML
    private Button mdfjr;
    @FXML
    private TableView<Joueurs> tvjrs;
    @FXML
    private TableColumn<?, ?> jnom;
    @FXML
    private TableColumn<?, ?> jpren;
    @FXML
    private TableColumn<?, ?> jage;
    @FXML
    private TableColumn<?, ?> jemail;
    @FXML
    private TableColumn<?, ?> jclub;
     private String nc;
    Connection cnx = DataSource.getInstance().getCnx();
    private int index;

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
            jnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            jpren.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            jage.setCellValueFactory(new PropertyValueFactory<>("Age"));
            jemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            jclub.setCellValueFactory(new PropertyValueFactory<>("nom_club"));

            ServiceJoueurs sj = new ServiceJoueurs();
            ObservableList<Joueurs> listj = sj.list();

            tvjrs.setItems(listj);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Serviceclub sc = new Serviceclub();
        ObservableList<String> listc = sc.listercj();
        clubj.setItems(listc);
    }
    @FXML
    private void rech(KeyEvent event) throws SQLException {
        ServiceJoueurs sj = new ServiceJoueurs();
        //jrs = sj.list3(Txtid.getText());
        jnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        jpren.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        jage.setCellValueFactory(new PropertyValueFactory<>("Age"));
        jemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        jclub.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
        ObservableList<Joueurs> listj = sj.listrecherche(Txtid.getText());
        tvjrs.setItems(listj);
    }


    @FXML
    private void suppjrs(ActionEvent event) {
        Joueurs j = tvjrs.getSelectionModel().getSelectedItem();
        ServiceJoueurs sj = new ServiceJoueurs();
        sj.supprimer(j);
        JOptionPane.showMessageDialog(null, "Joueur supprimer");
        UpdateTable();
       
    }

    @FXML
    private void ajoutjrs(ActionEvent event) throws SQLException {
         ServiceJoueurs sj = new ServiceJoueurs();

        nc = clubj.getSelectionModel().getSelectedItem();
        String req = "SELECT id_club FROM `club` WHERE nom_club like '" + nc + "'";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int idcl = 0;
        idcl = rs.getInt(1);

        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
        if (nomj.getText().isEmpty() || prenomj.getText().isEmpty() || agej.getText().isEmpty() || email.getText().isEmpty() || clubj.getSelectionModel().isSelected(-1)) {

            alertAD.setTitle("error");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Veuiller remplire tous les champs");
            alertAD.show();
        } else {
            int aagee = Integer.parseInt(agej.getText());

            if (aagee > 20 || isValid(email.getText()) == false) {

                alertAD.setTitle("error");
                alertAD.setHeaderText(null);
                alertAD.setContentText("verifier votre coordonné ");
                alertAD.show();

            } else {

                sj.ajouter(new Joueurs(nomj.getText(), prenomj.getText(), aagee, idcl, email.getText()));
                JOptionPane.showMessageDialog(null, "Joueur ajouté");
            }
        }

        UpdateTable();
    }

    @FXML
    private void sendemail(ActionEvent event) throws DocumentException, SQLException, IOException, MessagingException {
        String ma = null;
        ServiceJoueurs sj = new ServiceJoueurs();
        Joueurs j = tvjrs.getSelectionModel().getSelectedItem();
        ma = sj.getmailj(j);
    }

    @FXML
    private void mdfjrs(ActionEvent event) throws SQLException {
        nc = clubj.getSelectionModel().getSelectedItem();
        int idcl = 0;
        Joueurs j = tvjrs.getSelectionModel().getSelectedItem();
        ServiceJoueurs sj = new ServiceJoueurs();
        String req = "SELECT id_club FROM `club` WHERE nom_club like '" + nc + "'";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        rs.next();
        idcl = rs.getInt(1);
        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
        if (nomj.getText().isEmpty() || prenomj.getText().isEmpty() || agej.getText().isEmpty() || email.getText().isEmpty() || clubj.getSelectionModel().isSelected(-1)) {

            alertAD.setTitle("error");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Veuiller remplire tous les champs");
            alertAD.show();
        } else {
            int aagee = Integer.parseInt(agej.getText());

            if (aagee > 20 || isValid(email.getText()) == false) {
                alertAD.setTitle("error");
                alertAD.setHeaderText(null);
                alertAD.setContentText("Verifier vos coordonées");
                alertAD.show();

            } else {
                sj.modifier(new Joueurs(j.getId(), nomj.getText(), prenomj.getText(), aagee, idcl, email.getText()));
                JOptionPane.showMessageDialog(null, "Joueur modifié");
                UpdateTable();
            }
        }
    }

    @FXML
    private void getselectedd(MouseEvent event) {
             index = tvjrs.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            System.out.println("selection erronnée");
            return;
        }
        nomj.setText(jnom.getCellData(index).toString());
        prenomj.setText(jpren.getCellData(index).toString());
        agej.setText(jage.getCellData(index).toString());
        email.setText(jemail.getCellData(index).toString());
        clubj.setValue(jclub.getCellData(index).toString());
    }
     public static boolean isValid(String email) {

        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }
    
}
