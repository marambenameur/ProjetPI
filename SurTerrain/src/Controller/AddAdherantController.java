/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Adherant;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class AddAdherantController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private TextField numTel;
    @FXML
    private TextField address;
    @FXML
    private TextField NomT;

    ServiceAdmin admin = new ServiceAdmin();
    ServiceAdherant adherant = new ServiceAdherant();
    ServiceClient client = new ServiceClient();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Cancel(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        address.setText("");
        email.setText("");
        pass.setText("");
        numTel.setText("");
        NomT.setText("");
        cin.setText("");
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        String name = nom.getText();
        String pr = prenom.getText();
        String adrs = address.getText();
        String emaill = email.getText();
        String mdp = pass.getText();
        String Cin = cin.getText();
        String nomt = NomT.getText();
        String NumTel = numTel.getText();
        if (name.isEmpty() == false && pr.isEmpty() == false && adrs.isEmpty() == false && emaill.isEmpty() == false && mdp.isEmpty() == false && Cin.isEmpty() == false && NumTel.isEmpty() == false && nomt.isEmpty() == false) {
            if (validatePhoneNumber(Cin)) {
                if (validatePhoneNumber(NumTel)) {
                    if (isEmailAdress(emaill)) {

                        if (admin.findByEmail(emaill) == false && client.findByEmail(emaill) == false && adherant.findByEmail(emaill) == false) {

                            ServiceAdherant serviceA = new ServiceAdherant();
                            Adherant A = new Adherant(name, pr, Integer.parseInt(Cin), emaill , nomt,adrs, Integer.parseInt(NumTel), mdp);
                            serviceA.ajouter(A);
                            Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
                            alertAD.setTitle("Succes");
                            alertAD.setHeaderText(null);
                            alertAD.setContentText("Votre Compte a été Creé ");
                            Optional<ButtonType> action = alertAD.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                Parent roott = FXMLLoader.load(getClass().getResource("/GUI/AddAdherant.fxml"));
                                Scene scene = new Scene(roott);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.close();
                                  Notifications n = Notifications.create()
                                    .title("SUCCESS")
                                    .text("  Adherant Ajouté")
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                            n.darkStyle();
                            n.show();
                                              
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
                        a.setContentText("Le téléphone doit contenir 8 numéros");
                        a.setHeaderText("Valeur invalide");
                        a.show();
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("Le CIN doit contenir 8 numéros");
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
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{8}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

    }

    public static boolean isEmailAdress(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

}
