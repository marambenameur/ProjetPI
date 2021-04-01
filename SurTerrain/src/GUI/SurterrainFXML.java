/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author OussKh
 */
public class SurterrainFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MenuAdherant.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Image applicationIcon = new Image(getClass().getResourceAsStream("/assets/logo.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setTitle("SurTerrain");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
