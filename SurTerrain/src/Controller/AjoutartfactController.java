/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import models.Article;
import models.Detail_facture;
import models.Entete_facture;
import services.ServiceDetailFacture;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputMethodEvent;
import javax.swing.JOptionPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class AjoutartfactController implements Initializable {

    @FXML
    private TextField qt;
    @FXML
    private Button valide;
    @FXML
    private Button annule;
    @FXML
    private ComboBox lib;
    @FXML
    private ComboBox type;
    @FXML
    private TextField refmsg;
    @FXML
    private TextField piece;
    @FXML
    private TextField article;
    @FXML
    private Label error;
    ValidationSupport validationSupport = new ValidationSupport();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Tooltip tooltipButton = new Tooltip();
        tooltipButton.setText("Numéro de piéce de la facture");
        piece.setTooltip(tooltipButton);
        final Tooltip tooltipButton1 = new Tooltip();
        tooltipButton1.setText("Type de la facture");
        type.setTooltip(tooltipButton1);
        final Tooltip tooltipButton2 = new Tooltip();
        tooltipButton2.setText("Référence article");
        article.setTooltip(tooltipButton2);
        final Tooltip tooltipButton3 = new Tooltip();
        tooltipButton3.setText("Libelle d'article");
        lib.setTooltip(tooltipButton3);
        final Tooltip tooltipButton4 = new Tooltip();
        tooltipButton4.setText("Quantité d'article");
        qt.setTooltip(tooltipButton4);
        final Tooltip tooltipButton5 = new Tooltip();
        tooltipButton5.setText("valider article ajoutée");
        valide.setTooltip(tooltipButton5);
        final Tooltip tooltipButton6 = new Tooltip();
        tooltipButton6.setText("annuler article ajoutée");
        annule.setTooltip(tooltipButton6);
        if ((piece.getText().matches("[a-z,A-Z]"))) {

            refmsg.setStyle("-fx-background-color:transparent;-fx-text-fill:red;");
            refmsg.setText("Veuillez saisir un entier  SVP");

        } else if ((piece.getText().matches("[0-9,0-9]"))) {
            refmsg.setText(null);
        } else {
            refmsg.setText(null);
        }
        Connection cnx = DataSource.getInstance().getCnx();
        ObservableList<String> listacombo = FXCollections.observableArrayList();
        String req = "select libelle from article";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(req);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutartfactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutartfactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                listacombo.add(rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjoutartfactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lib.setItems(listacombo);

        ObservableList<String> list1 = FXCollections.observableArrayList("achat", "vente");
        type.setItems(list1);
    }

    @FXML
    private void qtvalid(ActionEvent event) {
        validationSupport.registerValidator(qt, Validator.createEmptyValidator("Veuillez saisir un numéro de piéce SVP"));
        if ((qt.getText().matches("[A-Z,a-z]")) || (qt.getText().length() == 0)) {
            qt.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(qt).play();
            error.setText("La quantité   de l'article est un entier ");

        } else if ((qt.getText().matches("[0-9]"))) {
            qt.setStyle(null);
            error.setText(null);
        }
    }

    @FXML
    private void valider(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == valide) {

            ServiceDetailFacture sa = new ServiceDetailFacture();
            sa.ajouter(new Detail_facture(lib.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(qt.getText()), type.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(article.getText()), Integer.parseInt(piece.getText())));
            JOptionPane.showMessageDialog(null, "Article Ajoutée");
            ObservableList<String> list1 = FXCollections.observableArrayList("achat", "vente");
            type.setItems(list1);

            piece.setText(null);
            article.setText(null);
            type.setPromptText(null);
            lib.setPromptText(null);
            qt.setText(null);
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        piece.setText(null);
        article.setText(null);
        type.setPromptText(null);
        lib.setPromptText(null);
        qt.setText(null);
        piece.setStyle(null);
        article.setStyle(null);
        type.setStyle(null);
        lib.setStyle(null);
        qt.setStyle(null);
        error.setText(null);
    }

    @FXML
    private void libvalid(ActionEvent event) {
        validationSupport.registerValidator(lib, Validator.createEmptyValidator("Veuillez saisir un libelle SVP"));

    }

    @FXML
    private void typevalid(ActionEvent event) {
        validationSupport.registerValidator(type, Validator.createEmptyValidator("Veuillez saisir un type SVP"));

    }

    @FXML
    private void piecevalid(InputMethodEvent event) {
    }

    @FXML
    private void factvalid(ActionEvent event) throws SQLException {
        validationSupport.registerValidator(piece, Validator.createEmptyValidator("Veuillez saisir un numéro de piéce SVP"));

        Connection cnx = DataSource.getInstance().getCnx();
        List<Entete_facture> list = new ArrayList<>();

        String requete = "SELECT ref_facture FROM entete_facture where ref_facture='" + piece.getText() + "'";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Entete_facture(rs.getInt(1)));

        }
//         if(list.size()>0){
//           ref.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//           new animatefx.animation.Shake(ref).play();
//           error.setText("Cette article existe déja ");
//
//        }
        if (!list.isEmpty()) {
            if ((!piece.getText().matches("[0-9]")) || (piece.getText().length() == 0)) {
                piece.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                new animatefx.animation.Shake(piece).play();

                error.setText("le numéro de piéce  est un entier ");

            } else if ((!piece.getText().matches("[a-z,A-Z]"))) {
                piece.setStyle(null);
                error.setText(null);
            }
        } else {
            piece.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                    error.setText(null);

//            new animatefx.animation.Shake(piece).play();
            error.setText("Cette facture n'existe pas ");
        }

    }

    @FXML
    private void refartvalid(ActionEvent event) throws SQLException {
        validationSupport.registerValidator(article, Validator.createEmptyValidator("Veuillez saisir un article SVP"));

        Connection cnx = DataSource.getInstance().getCnx();
        List<Article> list = new ArrayList<>();

        String requete = "SELECT ref FROM article where ref='" + article.getText() + "' ";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Article(rs.getInt(1)));

        }
//         if(list.size()>0){
//           ref.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//           new animatefx.animation.Shake(ref).play();
//           error.setText("Cette article existe déja ");
//
//        }
        if (!list.isEmpty()) {
            if ((!article.getText().matches("[0-9]")) || (article.getText().length() == 0)) {
                article.setStyle("-fx-border-color:red ; -fx-border-width:2px;");

                error.setText("la référence de l'article est un entier ");

            } else if ((!article.getText().matches("[a-z,A-Z]"))) {
                article.setStyle(null);
                error.setText(null);
            }
        } else {
            article.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                    error.setText(null);

//            new animatefx.animation.Shake(article).play();
            error.setText("Cette article n'existe pas ");
        }

    }

}
