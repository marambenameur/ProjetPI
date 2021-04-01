/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Utils.DataSource;
import Models.Demande;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import Services.ServiceDemande;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author ABS1
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<Demande> table;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> terrain;
    @FXML
    private TableColumn<?, ?> equipe;

    ObservableList<Demande> list = FXCollections.observableArrayList();
    @FXML
    private Button bntmod;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField rech;
    @FXML
    private Button telecharger;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> Etat;
    @FXML
    private Button match;
    @FXML
    private Button ref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM demande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceDemande sp = new ServiceDemande();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        terrain.setCellValueFactory(new PropertyValueFactory<>("nomequipe"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("nomequipe"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(list);
        rech.getText();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("nomequipe"));
        terrain.setCellValueFactory(new PropertyValueFactory<>("nomterrain"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        table.setItems(list);

        FilteredList<Demande> filteredData = new FilteredList<>(list, b -> true);
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Demande -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Demande.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(Demande.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Demande.getNomequipe()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Demande.getNomterrain()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Demande.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<Demande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        // TODO
    }

    @FXML
    private void btnaction(javafx.event.ActionEvent mouseEvent) throws IOException, SQLException {
        Demande selected = table.getSelectionModel().getSelectedItem();

        if (mouseEvent.getSource() == bntmod) {
            if (selected == null) {

                System.out.println("Aucune demande séléctionné");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Aucune demande séléctionné");
                alert.showAndWait();

            } else {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Modifier.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage window = new Stage();
                window.setScene(scene);
                window.initStyle(StageStyle.UTILITY);
                window.show();
                ModifierController controller = loader.getController();
                String id = String.valueOf(selected.getId());
                controller.showInformation(id, selected.getDate(), selected.getNomterrain(), selected.getNomequipe(), selected.getEmail());
            }

        } else if (mouseEvent.getSource() == btnajouter) {
            loadStage("/GUI/ajouter.fxml");
        } else if (mouseEvent.getSource() == btnsupp) {

            ServiceDemande sd = new ServiceDemande();
            sd.supprimer(selected);
            Notifications n = Notifications.create()
                    .title("Success")
                    .text("  demande suprimer")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(javafx.util.Duration.seconds(3));
            n.darkStyle();
            n.show();

        } else if (mouseEvent.getSource() == match) {
            Connection cnx = DataSource.getInstance().getCnx();
            int i = selected.getId();

            String t = "libre";
            int t1 = t.length();
            if (selected.getEtat().length() == t1) {
                String s = "charger";
                try {
                    Statement stm = cnx.createStatement();
                    String requete = "UPDATE demande SET etat= '" + s + "' WHERE id='" + i + "'";
                    stm.executeUpdate(requete);
                    Notifications n = Notifications.create()
                            .title("Success")
                            .text("  Matched ")
                            .position(Pos.TOP_CENTER)
                            .hideAfter(javafx.util.Duration.seconds(3));
                    n.darkStyle();
                    n.show();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                Notifications n = Notifications.create()
                        .title("Echec")
                        .text("  demande charger")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(javafx.util.Duration.seconds(3));
                n.darkStyle();
                n.show();
            }

        }
    }

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void telecharger(ActionEvent event) throws IOException, FileNotFoundException, DocumentException, SQLException {

        try {
            String file_name = "C:\\PDF\\demande.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String req = "Select * from demande";
            ps = cnx.prepareCall(req);
            rs = ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("id"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("date"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("nomequipe"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("nomterrain"));
            t.addCell(c4);
            t.setHeaderRows(1);
            while (rs.next()) {
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(null, "PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier 😃");
    }

}
