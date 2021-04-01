/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataSource;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class SideMenuController implements Initializable {

   
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private StackPane anchpane;
    public static StackPane temporaryPane;
    @FXML
    private Label nomuser;
    @FXML
    private Label prenomuser;
    @FXML
    private Label nomusert;
    @FXML
    private Label prenomusert;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label id;
    Connection cnx = DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        temporaryPane = anchpane;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SideBar.fxml"));
            Parent root;
            root = loader.load();
            drawer.setSidePane(root);
            drawer.setDefaultDrawerSize(200);
            
            

        } catch (IOException ex) {
            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
       
        

    }    
   public void myFunction(String text)
    {
        email.setText(text);
        String requete="SELECT * FROM client Where email ='" + email.getText() + "'";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete)  ; 
                 ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                     id.setText(String.valueOf(rs.getInt(1))); 
                     nomusert.setText(rs.getString(2));       
                     prenomusert.setText(rs.getString(3));
                     username.setText(rs.getString(4));
                     nomuser.setText(rs.getString(2));
                     prenomuser.setText(rs.getString(3));
                 
                     }
           
                }  
            catch (SQLException ex) {
            
            }  
        
    }
    @FXML
    private void ModifMotdePass(MouseEvent event) throws IOException {
          FXMLLoader loader  = new FXMLLoader(getClass().getResource("/GUI/ResetClient.fxml"));
          Parent root =loader.load();
          ResetClientController second =loader.getController();
          second.myFunction(email.getText());
          Scene scene= new Scene(root);
          Stage window = new Stage() ;
          window.setScene(scene);
          window.initStyle(StageStyle.UTILITY);
          window.show();
    }

    @FXML
    private void LogOut(MouseEvent event) throws IOException {
         Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")); 
          Scene scene= new Scene(root);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.show();
    }


   
    
}
