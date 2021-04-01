/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Terrain;
import Services.ServiceReservation;
import Services.ServiceTerrain;
import Utils.DataSource;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class TerrainController implements Initializable {

    @FXML
    private TableView<Terrain> lv;
    @FXML
    private TextField tfrechercheTerrain;
    @FXML
    private TableColumn<Terrain, String> NomTerrain;
    @FXML
    private TableColumn<Terrain, String> Adresse;
    @FXML
    private TableColumn<Terrain, String> Etat;
    @FXML
    private Button btnRecherche;
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnpdf;

    ObservableList<Terrain> olist = FXCollections.observableArrayList();
    ServiceReservation sr = new Services.ServiceReservation();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
        lv.setEditable(true);
        NomTerrain.setEditable(true);
        Adresse.setEditable(true);
        Etat.setEditable(true);

        NomTerrain.setCellValueFactory(new PropertyValueFactory<>("nomTerrain"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        lv.setItems(olist);

        NomTerrain.setCellFactory(TextFieldTableCell.<Terrain>forTableColumn());
        Adresse.setCellFactory(TextFieldTableCell.<Terrain>forTableColumn());
        Etat.setCellFactory(TextFieldTableCell.<Terrain>forTableColumn());

        NomTerrain.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Terrain, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Terrain, String> event) {
                Terrain terrain = event.getRowValue();
                terrain.setAdresse(event.getNewValue());
                ModifierTerrain("NomTerrain", event.getNewValue(), sr.getIdTerrainByName(terrain.getNomTerrain()));
            }
        });
        Adresse.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Terrain, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Terrain, String> event) {
                Terrain terrain = event.getRowValue();
                terrain.setAdresse(event.getNewValue());
                ModifierTerrain("Adresse", event.getNewValue(), sr.getIdTerrainByName(terrain.getNomTerrain()));
            }
        });
        Etat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Terrain, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Terrain, String> event) {
                Terrain terrain = event.getRowValue();
                terrain.setEtat(event.getNewValue());
                ModifierTerrain("Etat", event.getNewValue(), sr.getIdTerrainByName(terrain.getNomTerrain()));
            }
        });

    }

    @FXML
    private void rechercherTerrain(MouseEvent event) {

        loadData();
        FilteredList<Terrain> filteredData = new FilteredList<>(olist, b -> true);
        tfrechercheTerrain.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Terrain -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Terrain.getNomTerrain().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Terrain.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Terrain.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<Terrain> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(lv.comparatorProperty());
        lv.setItems(sortedData);
        lv.refresh();
        try {
            RefrechAuto();
        } catch (SQLException ex) {
            Logger.getLogger(TerrainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ModifierTerrain(String column, String newValue, int id) {

        Connection cnx = DataSource.getInstance().getCnx();
        Terrain terrain = new Terrain();
        try (PreparedStatement stmt = cnx.prepareStatement("UPDATE terrain SET " + column + " = '" + newValue + "' WHERE idTerrain = " + id);) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTerrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerTerrain(MouseEvent event) {
        Terrain selected = lv.getSelectionModel().getSelectedItem();

        if (selected == null) {

            System.out.println("Aucun terrain séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun terrain séléctionné");
            alert.showAndWait();

        } else {
            ServiceTerrain st = new Services.ServiceTerrain();
            String nomTerrain = selected.getNomTerrain();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Evenement");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sur de vouloir supprimer le terrain " + " " + selected.getNomTerrain());
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {

                st.supprimer(selected);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Succés!");
                alert1.setHeaderText(null);
                alert1.setContentText("Terrain supprimé!");

                alert1.showAndWait();
                lv.refresh();
                try {
                    RefrechAuto();
                } catch (SQLException ex) {
                    Logger.getLogger(TerrainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void actionBoutton(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnModifier) {
            loadStage("/GUI/ModifierTerrain.fxml");
        } else if (mouseEvent.getSource() == btnAjouter) {
            loadStage("/GUI/AjouterTerrain.fxml");
        } else if (mouseEvent.getSource() == btnStat) {
            loadStage("/GUI/StatsChar.fxml");
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
    private void Refrech(ActionEvent event) throws SQLException {
        olist.clear();
        Connection cnx = DataSource.getInstance().getCnx();
        ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM terrain");

        while (rs.next()) {
            olist.add(new Terrain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
    }

    private void RefrechAuto() throws SQLException {
        olist.clear();
        Connection cnx = DataSource.getInstance().getCnx();
        ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM terrain");

        while (rs.next()) {
            olist.add(new Terrain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
    }


    @FXML
    private void actionGeneratePdf(ActionEvent event) throws FileNotFoundException, SQLException {
        try {
            String file_name="C:\\PDF\\ListeTerrains.pdf"; 
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();

//            document.add(new Chunk(""));
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select nomTerrain,adresse,etat from terrain"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            
//            ServiceTerrain st = new ServiceTerrain();
//            List<Terrain> list = st.afficher();
            
            PdfPTable table = new PdfPTable(3);
//            table.setWidthPercentage(100); //Width 100%
//            table.setSpacingBefore(10f); //Space before table
//            table.setSpacingAfter(10f); //Space after table

//            for (Terrain t : list) {
                PdfPCell c1 = new PdfPCell(new Phrase("Nom du terrain" ));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c1);

                PdfPCell c2 = new PdfPCell(new Phrase("Adresse" ));
//                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c2);

                PdfPCell c3 = new PdfPCell(new Phrase("Etat"));
//                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(c3);
//            }

            table.setHeaderRows(1);
            while(rs.next()){
                table.addCell(rs.getString(1));
                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                
                document.add(table);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(null, "PDF téléchargée vérifier votre dossier");

    }

    private void loadData() {

        ServiceTerrain st = new ServiceTerrain();
        this.olist = FXCollections.observableArrayList(st.afficher());

    }
}
