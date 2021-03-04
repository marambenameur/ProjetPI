/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hamidou
 */
public class maingui extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader Loader= new FXMLLoader(getClass().getResource("../clubfootballgui/addpersonne.fxml"));
        Parent root= Loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("ahmed");
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
