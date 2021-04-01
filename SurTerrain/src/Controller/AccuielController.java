/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class AccuielController implements Initializable {

    @FXML
    private ImageView factureimg;
    @FXML
    private Label getionstore;
    @FXML
    private ImageView articleimg;
    @FXML
    private Button facture;
    @FXML
    private Button article;
    @FXML
    private Button goback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Tooltip tooltipButton = new Tooltip();
        tooltipButton.setText("Gestion de facture");
        facture.setTooltip(tooltipButton);
        final Tooltip tooltipButton1 = new Tooltip();
        tooltipButton1.setText("Gestion d'article");
        article.setTooltip(tooltipButton1);
        JFileChooser j = new JFileChooser();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), getionstore);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

        RotateTransition rt = new RotateTransition(Duration.seconds(3), factureimg);
        rt.setByAngle(360);
        rt.setCycleCount(10);
        rt.setAutoReverse(true);
        rt.play();
        RotateTransition rt1 = new RotateTransition(Duration.seconds(3), articleimg);
        rt1.setByAngle(360);
        rt1.setCycleCount(10);
        rt1.setAutoReverse(true);
        rt1.play();

    }

    @FXML
    private void gofacture(javafx.event.ActionEvent mouseEvent) {

        if (mouseEvent.getSource() == facture) {

//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/GUI/facture.fxml"));
//                Scene scene = new Scene(root);
//                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.show();
//
//            } catch (IOException ex) {
//                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            switchPane("/GUI/facture.fxml");
        }
    }

    @FXML
    private void goarticle(javafx.event.ActionEvent mouseEvent) {
       // if (mouseEvent.getSource() == article) {

//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/GUI/menuarticle.fxml"));
//                Scene scene = new Scene(root);
//                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.show();
//
//            } catch (IOException ex) {
//                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            switchPane("/GUI/menuarticle.fxml");
       // }
    }
      @FXML
    void gobackaction(javafx.event.ActionEvent mouseEvent) {
if (mouseEvent.getSource() == goback) {

//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("menuarticle.fxml"));
//                Scene scene = new Scene(root); 
//                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.show();
//
//            } catch (IOException ex) {
//                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        switchPane("/GUI/menuarticle.fxml");
        }

    }
    
          private void switchPane(String pane){
        try {
            MenuAdherantController.temporaryPane.getChildren().clear();
            StackPane pane2=FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements =pane2.getChildren();
            MenuAdherantController.temporaryPane.getChildren().setAll(elements);
        } catch (IOException ex) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
