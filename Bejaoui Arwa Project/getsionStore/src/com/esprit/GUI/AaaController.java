/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class AaaController implements Initializable {

    @FXML
    private TableColumn<?, ?> col_lib;
    @FXML
    private TableColumn<?, ?> col_cat;
    @FXML
    private TableColumn<?, ?> col_qt;
    @FXML
    private TableColumn<?, ?> col_prix;
    @FXML
    private Button consult_art;
    @FXML
    private ImageView refresh_article;
    @FXML
    private Button ajout_art;
    @FXML
    private TextField rech_artcile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
