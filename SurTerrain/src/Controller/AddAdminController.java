/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Admin;
import Services.ServiceAdherant;
import Services.ServiceAdmin;
import Services.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class AddAdminController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private Label message;

    ServiceAdmin admin = new ServiceAdmin();
    ServiceAdherant adherant = new ServiceAdherant();
    ServiceClient client = new ServiceClient();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void Cancel(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        username.setText("");
        email.setText("");
        pass.setText("");
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        String name = nom.getText();
        String pr = prenom.getText();
        String Username = username.getText();
        String emaill = email.getText();
        String mdp = pass.getText();
        if (name.isEmpty() == false && pr.isEmpty() == false && Username.isEmpty() == false && emaill.isEmpty() == false && mdp.isEmpty() == false) {
            if (admin.findByUsername(Username) == false) {
                if (isEmailAdress(emaill)) {

                    if (admin.findByEmail(emaill) == false && client.findByEmail(emaill) == false && adherant.findByEmail(emaill) == false) {

                        ServiceAdmin service = new ServiceAdmin();
                        Admin A = new Admin(name, pr, Username, emaill, mdp);
                        service.ajouter(A);
                        Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
                        alertAD.setTitle("Succes");
                        alertAD.setHeaderText(null);
                        alertAD.setContentText("Votre Compté a été Creé ");
                        Optional<ButtonType> action = alertAD.showAndWait();
                        if (action.get() == ButtonType.OK) {
                            Parent roott = FXMLLoader.load(getClass().getResource("/GUI/AddAdmin.fxml"));
                            Scene scene = new Scene(roott);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.close();
                            Notifications n = Notifications.create()
                                    .title("SUCCESS")
                                    .text("  Admin Ajouté")
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                            n.darkStyle();
                            n.show();

                        }

                    } else {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setContentText("Email Utilisé");
                        a.setHeaderText("Valeur invalide");
                        a.show();
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("Verifier Votre Email");
                    a.setHeaderText("Valeur invalide");
                    a.show();
                }

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Username Utilisé");
                a.setHeaderText("Valeur invalide");
                a.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champs Vide ");
            alert.showAndWait();
        }

    }

    public static boolean isEmailAdress(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

}
