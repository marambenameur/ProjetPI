/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.esprit.models.Event;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author maram
 */
public class main extends Application {
      static Event  e;
     
    @Override
    public void start(Stage primaryStage ) throws IOException {
//      Parent root = FXMLLoadercategoriecategorie
//                .load(getClass().getResource("../gui/addEvent.fxml"));
//            Scene scene = new Scene(root);
//            primaryStage.setTitle("Hello World");
//            primaryStage.setScene(scene);
//            primaryStage.show();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("../Admin/AfficherCategories.fxml"));
     Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World");
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
