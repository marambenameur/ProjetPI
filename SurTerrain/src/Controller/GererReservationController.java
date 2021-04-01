/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Reservation;
import Services.ServiceReservation;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class GererReservationController implements Initializable {

    @FXML
    private TableView<Reservation> tbReservation;
    @FXML
    private TableColumn<Reservation, String> Client;
    @FXML
    private TableColumn<Reservation, String > Terrain;
     @FXML
    private TableColumn<Reservation, Date> Date;
    @FXML
    private TableColumn<Reservation, Time> HeureDebut;
    @FXML
    private TableColumn<Reservation, Time> HeureFin;
    @FXML
    private TextField tfrechercheRes;
    @FXML
    private Button btnRecherche;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnIgnorer;
    
    ObservableList<Reservation> olist ;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     loadData();
     tbReservation.refresh();

    }    


    @FXML
    private void rechercherReservation(MouseEvent event) {
        
       loadData();

       ServiceReservation sr= new ServiceReservation();
        



        FilteredList<Reservation> filteredData = new FilteredList<>(olist, b -> true);
        tfrechercheRes.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Reservation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String dataFilter = newValue;

                if (Reservation.getDate().toString().indexOf(dataFilter) != -1) {
                    return true;
                } 
                else if (Reservation .getHeureDebut().toString().indexOf(dataFilter) != -1) {
                    return true;
                } 
                else if (Reservation .getHeureFin().toString().indexOf(dataFilter) != -1) {
                    return true;
                } 
                else {
                    return false;
                }

            });
        });
        
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbReservation.comparatorProperty());
        tbReservation.setItems(sortedData);
        tbReservation.refresh();

        
        
        
//             Connection cnx = DataSource.getInstance().getCnx();
////        olist.clear();
//        String requete = "select * from reservation where  date LIKE '%"
//                + tfrechercheRes.getText() + "%' or idTerrain LIKE '%" 
//                + tfrechercheRes.getText() + "%' or heureDebut LIKE '%" 
//                + tfrechercheRes.getText() + "%' or heureDebut LIKE '%" 
//                + tfrechercheRes.getText() + "%'";
//
//        try {
//            Statement stm = cnx.createStatement();
//            ResultSet rs = stm.executeQuery(requete);
//            while (rs.next()) {
//                olist.add(new Reservation(rs.getDate(2), rs.getTime(3), rs.getTime(4)));
//            }
//        } catch (SQLException ex) {
//            System.out.println("aucune réservation disponible!");
//        }
//
//        tfrechercheRes.getText();
////        Client.setCellValueFactory(new PropertyValueFactory<>("idclient"));
////        Terrain.setCellValueFactory(new PropertyValueFactory<>("idTerrain"));
//        Date.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
//        HeureDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
//        HeureFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
//        tbReservation.setItems(olist);
    }

    @FXML
    private void ingorerResevation(MouseEvent event) {

        Reservation selected = tbReservation.getSelectionModel().getSelectedItem();

        if (selected == null) {

            System.out.println("Aucun réservation séléctionnée");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun réservation séléctionnée");
            alert.showAndWait();

        } else {
            ServiceReservation sr = new Services.ServiceReservation();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer réservation");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sur de vouloir supprimer la réservation ");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                sr.supprimer(selected);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Succés!");
                alert1.setHeaderText(null);
                alert1.setContentText("Réservetion supprimée!");

                alert1.showAndWait();
                tbReservation.refresh();
            }
        }
        tbReservation.refresh();
    }

    public void loadData() {

        ServiceReservation sr = new Services.ServiceReservation();
        this.olist = FXCollections.observableArrayList(sr.afficher());

        Client.setCellValueFactory(new PropertyValueFactory<>("idclient"));
        Terrain.setCellValueFactory(new PropertyValueFactory<>("idTerrain"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        HeureDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        HeureFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        tbReservation.setItems(olist);
    }

    @FXML
    private void ValiderMail(ActionEvent event) throws Exception {
        
//       Reservation selected = tbReservation.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//
//            System.out.println("Aucune réservation séléctionnée");
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Aucune réservation séléctionnée");
//            alert.showAndWait();
//
//        } else {
//            ServiceReservation sr = new Services.ServiceReservation();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Envoyer un email de validation");
//            alert.setHeaderText(null);
//            alert.setContentText("Etes-vous sur de vouloir valider la réservation ");
//            Optional<ButtonType> action = alert.showAndWait();
//            if (action.get() == ButtonType.YES) {
////                sr.supprimer(selected);
//                
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setTitle("Succés!");
//                alert1.setHeaderText(null);
//                alert1.setContentText("Réservetion validée!");
//
//                alert1.showAndWait();
//                
//            }
//        }
        
       sendMail("oussema.khorchani@esprit.tn");
        
     
    }
    public void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "oussema.khorchani@esprit.tn";
        //Your gmail password
        String password = "203JMT1891";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("message envoyé");
        
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Succés!");
        alert1.setHeaderText(null);
        alert1.setContentText("Réservetion validée!");
        
    }

    private  Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Validation de la réservation du terrain");
            String htmlCode = "<h1> Bienvenue à SurTerrain </h1> <br/> <h2><b>Votre réservation est validée avec succée </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(GererReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
