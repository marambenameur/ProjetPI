/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Adherant;
import Models.Client;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class AddCompteController implements Initializable {

    @FXML
    private TextField JNom;
    @FXML
    private TextField Jprenom;
    @FXML
    private TextField JAdresse;
    @FXML
    private TextField JNumTel;
    @FXML
    private TextField JEmail;
    @FXML
    private PasswordField Jmdp;
    @FXML
    private Button Creer;
    @FXML
    private Button annuler;
    @FXML
    private TextField JCin;
    @FXML
    private TextField JNum_Terain;
    @FXML
    private Label LabelforSorted;
    @FXML
    private CheckBox CheckClient;
    @FXML
    private CheckBox ChekAdherant;
    @FXML
    private PasswordField Jmdp2;
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
    public void Add_Client(ActionEvent event) throws IOException {
        String name = JNom.getText();
        String prenom = Jprenom.getText();
        String Adr = JAdresse.getText();
        String NumTel = JNumTel.getText();
        String Email = JEmail.getText();
        String mdp = Jmdp.getText();
        String mdp1 = Jmdp2.getText();

        if (name.isEmpty() == false && prenom.isEmpty() == false && Adr.isEmpty() == false && NumTel.isEmpty() == false && Email.isEmpty() == false && mdp.isEmpty() == false && mdp1.isEmpty() == false) {
            if (validatePhoneNumber(NumTel)) {
                if (isEmailAdress(Email)) {
                    if (admin.findByEmail(Email) == false && client.findByEmail(Email) == false && adherant.findByEmail(Email) == false) {
                        if (mdp.equals(mdp1) && mdp.length() > 8) {
                            if (CheckClient.isSelected() == true || ChekAdherant.isSelected() == true) {
                                if (CheckClient.isSelected()) {
                                    ServiceClient service = new ServiceClient();
                                    Client C = new Client(name, prenom, Adr, Integer.parseInt(NumTel), Email, mdp);
                                    service.ajouter(C);
                                    Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
                                    alertAD.setTitle("Succes");
                                    alertAD.setHeaderText(null);
                                    alertAD.setContentText("Votre Compté a été Creé ");
                                    Optional<ButtonType> action = alertAD.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
                                        Scene scene = new Scene(root);
                                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        window.setScene(scene);
                                        window.show();
                                    }
                                } else {

                                    String Cin = JCin.getText();
                                    String nomT = JNum_Terain.getText();

                                    if (Cin.isEmpty() == false && nomT.isEmpty() == false) {
                                        if (validatePhoneNumber(Cin)) {

                                            ServiceAdherant serviceA = new ServiceAdherant();
                                            Adherant A = new Adherant(name, prenom, Integer.parseInt(Cin), Email, nomT,Adr , Integer.parseInt(NumTel), mdp);
                                            serviceA.ajouter(A);
                                            Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
                                            alertAD.setTitle("Succes");
                                            alertAD.setHeaderText(null);
                                            alertAD.setContentText("Votre Compte a été Creé ");
                                            Optional<ButtonType> action = alertAD.showAndWait();
                                            if (action.get() == ButtonType.OK) {
                                                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
                                                Scene scene = new Scene(root);
                                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                window.setScene(scene);
                                                window.show();
                                            }

                                        } else {
                                            Alert a = new Alert(Alert.AlertType.WARNING);
                                            a.setContentText("Verifier Votre Cin");
                                            a.setHeaderText("Valeur invalide");
                                            a.show();
                                        }

                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Erreur");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Champ Vide !! Verifier Que Votre champs prennent des Valeur ");
                                        alert.showAndWait();
                                    }

                                }
                            } else {
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("Choisissez Une Role ");
                                a.setHeaderText("Valeur invalide");
                                a.show();

                            }

                        } else {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("Verifier Que Votres Mots de passes sont egaux et Supperieur a 8 (>8) ");
                            a.setHeaderText("Valeur invalide");
                            a.show();
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
                a.setContentText("Le téléphone doit contenir 8 numéros");
                a.setHeaderText("Valeur invalide");
                a.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champ Vide !! Verifier Que Votre champs prennent des Valeur ");
            alert.showAndWait();
        }
    }

    @FXML
    public void Selection(ActionEvent event) {
        if (CheckClient.isSelected() == true) {
            ChekAdherant.setDisable(true);
        } else {
            ChekAdherant.setDisable(false);
        }
    }

    @FXML
    public void Visible(ActionEvent event) {

        if (ChekAdherant.isSelected()) {
            CheckClient.setDisable(true);
            JCin.setVisible(true);
            JNum_Terain.setVisible(true);
        } else {
            CheckClient.setDisable(false);
            JCin.setVisible(false);
            JNum_Terain.setVisible(false);

        }
    }

    @FXML
    public void Cancel(ActionEvent event) {
        JNom.setText("");
        Jprenom.setText("");
        JAdresse.setText("");
        JNumTel.setText("");
        JEmail.setText("");
        Jmdp.setText("");
        Jmdp2.setText("");
        JCin.setText("");
        JNum_Terain.setText("");

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

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
