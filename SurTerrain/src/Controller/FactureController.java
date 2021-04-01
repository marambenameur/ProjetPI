/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import models.Detail_facture;
import models.Entete_facture;
import services.ServiceDetailFacture;
import services.ServiceEnteteFacture;
import Utils.DataSource;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
//import tray.animations.AnimationType;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;
//import tray.models.CustomStage;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class FactureController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox type;
    @FXML
    private Label label;
    @FXML
    private TextField ref;
    @FXML
    private TextField tier;
   @FXML
    private TableView<Detail_facture> tabarticle;
    @FXML
    private TableColumn<Detail_facture, Integer> col_refart;
    @FXML
    private TableColumn<Detail_facture, String> col_lib;
    @FXML
    private TableColumn<Detail_facture, Integer> col_qt;
    @FXML
    private Button annuler;
    @FXML
    private Button modiffact;
    @FXML
    private Button ajoutfact;
    @FXML
    private Button ajoutart;
    @FXML
    private Button suppart;
    @FXML
    private Label facturelable;
    private Button retourne;
    @FXML
    private Button facture;
    @FXML
    private Button suppfact;
    @FXML
    private TableView<Entete_facture> tabfacture;
    @FXML
    private TableColumn<Entete_facture, Integer> col_reffac;
    @FXML
    private TableColumn<Entete_facture, Date> col_date;
    @FXML
    private TableColumn<Entete_facture, String> col_type;
    @FXML
    private TableColumn<Entete_facture, Integer> col_tier;
    @FXML
    private RadioButton ajoutcheck;
    @FXML
    private RadioButton modifcheck;
    @FXML
    private RadioButton consultcheck;
    @FXML
    private TextField rechtype;
    private ImageView imagefacture;
    @FXML
    private Button imprim;
    @FXML
    private Button trie;
    @FXML
    private Button mail;
    @FXML
    private Button sms;
       @FXML
    private Label error;
          @FXML
    private TextField refarticle;
    private Integer index = -1;
    @FXML
    private ToggleGroup arwa;
    
       /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Tooltip tooltipButton = new Tooltip();
        tooltipButton.setText("Numéro de piéce");
        ref.setTooltip(tooltipButton);
        final Tooltip tooltipButton1 = new Tooltip();
        tooltipButton1.setText("Date facturation");
        date.setTooltip(tooltipButton1);
        final Tooltip tooltipButton2 = new Tooltip();
        tooltipButton2.setText("Type Facture");
        type.setTooltip(tooltipButton2);
        final Tooltip tooltipButton3 = new Tooltip();
        tooltipButton3.setText("Tier Facture");
        tier.setTooltip(tooltipButton3);
         final Tooltip tooltipButton4 = new Tooltip();
        tooltipButton4.setText("Table des articles");
        tabarticle.setTooltip(tooltipButton4);
         final Tooltip tooltipButton5 = new Tooltip();
        tooltipButton5.setText("Tables des factures");
        tabfacture.setTooltip(tooltipButton5);
           final Tooltip tooltipButton6 = new Tooltip();
        tooltipButton6.setText("Recherche");
        rechtype.setTooltip(tooltipButton6);
          final Tooltip tooltipButton7 = new Tooltip();
        tooltipButton7.setText("SMS");
        sms.setTooltip(tooltipButton7);
        final Tooltip tooltipButton8 = new Tooltip();
        tooltipButton8.setText("PDF");
        imprim.setTooltip(tooltipButton8);
        final Tooltip tooltipButton9 = new Tooltip();
        tooltipButton9.setText("Trie");
        trie.setTooltip(tooltipButton9);
        final Tooltip tooltipButton10 = new Tooltip();
        tooltipButton10.setText("Ajouter un article");
        ajoutart.setTooltip(tooltipButton10);
        final Tooltip tooltipButton11 = new Tooltip();
        tooltipButton11.setText("Supprimer un article");
        suppart.setTooltip(tooltipButton11);
        final Tooltip tooltipButton12 = new Tooltip();
        tooltipButton12.setText("Ajouter une facture");
        ajoutfact.setTooltip(tooltipButton12);
        final Tooltip tooltipButton13 = new Tooltip();
        tooltipButton13.setText("Modifier une facture");
        modiffact.setTooltip(tooltipButton13);
        final Tooltip tooltipButton14 = new Tooltip();
        tooltipButton14.setText("Supprimer une facture");
        suppfact.setTooltip(tooltipButton14);
        final Tooltip tooltipButton15 = new Tooltip();
        tooltipButton15.setText("Annuler une facture");
        annuler.setTooltip(tooltipButton15);
        final Tooltip tooltipButton16 = new Tooltip();
        tooltipButton16.setText("Ajout");
        ajoutcheck.setTooltip(tooltipButton16);
        final Tooltip tooltipButton17 = new Tooltip();
        tooltipButton17.setText("Modification");
        modifcheck.setTooltip(tooltipButton17);
        final Tooltip tooltipButton18 = new Tooltip();
        tooltipButton18.setText("Consultation");
        consultcheck.setTooltip(tooltipButton18);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), facturelable);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), label);
        fadeTransition1.setFromValue(1.0);
        fadeTransition1.setToValue(0.0);
        fadeTransition1.setCycleCount(Animation.INDEFINITE);
        fadeTransition1.play();
        //animation imagefacture
        RotateTransition rt = new RotateTransition(Duration.seconds(3), imagefacture);
        rt.setByAngle(360);
        rt.setCycleCount(40);
        rt.setAutoReverse(true);
        rt.play();
        //animation label
        suppart.setDisable(true);
        ajoutart.setDisable(true);
        annuler.setDisable(true);
        ajoutfact.setDisable(true);
        modiffact.setDisable(true);
        ObservableList<String> list1 = FXCollections.observableArrayList("vente", "achat");
        type.setItems(list1);
        col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
        ServiceEnteteFacture sa = new ServiceEnteteFacture();
        ObservableList<Entete_facture> list = sa.getFactureList();
        tabfacture.setItems(list);
    }  
       

    @FXML
    private void refvalid(ActionEvent event) throws SQLException {
         Connection cnx = DataSource.getInstance().getCnx();
        List<Entete_facture> list = new ArrayList<>();

        String requete = "SELECT ref_facture FROM entete_facture where ref_facture='" + ref.getText() + "'";
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
        if (list.isEmpty()) {
            if ((!ref.getText().matches("[0-9]")) || (ref.getText().length() == 0)) {
                ref.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                          new animatefx.animation.Shake(ref).play();

                error.setText("le numéro de piéce  est un entier ");

            } else if ((!ref.getText().matches("[a-z,A-Z]"))) {
                ref.setStyle(null);
                error.setText(null);
            }
        } else {
            ref.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                    error.setText(null);

//            new animatefx.animation.Shake(ref).play();
            error.setText("Cette facture existe déja ");
        }

    }


  @FXML
    private void tiervalid(ActionEvent event) {
         if ((tier.getText().matches("[A-Z,a-z]")) || (tier.getText().length() == 0)) {
            tier.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(tier).play();
            error.setText("Le tier est un  entier ");

        } else if ((tier.getText().matches("[0-9]"))) {
            tier.setStyle(null);
            error.setText(null);
        }
    }

    @FXML
    private void getselectedarticle(MouseEvent event) {

        index = tabarticle.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        refarticle.setText(col_refart.getCellData(index).toString());
        col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
//        ServiceDetailFacture sa = new ServiceDetailFacture();
//
//        ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
//        tabarticle.setItems(list);
        col_refart.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_refart.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setRef_article(event.getNewValue());

            }
        });
        col_lib.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lib.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, String>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, String> event) {
                Detail_facture df = event.getRowValue();
                df.setLibelle(event.getNewValue());

            }
        });

        col_qt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_qt.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setQt(event.getNewValue());
            }
        });

    }

    @FXML
    private void annuler(ActionEvent event) {

        ref.setText(null);
        date.setValue(null);
        type.setPromptText(null);
        tier.setText(null);
        tabarticle.setItems(null);
        tier.setStyle(null);
        ref.setStyle(null);
         error.setText(null);
        
    }

    @FXML
    private void modifierfacture(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == modiffact) {

            ServiceEnteteFacture sa = new ServiceEnteteFacture();

            sa.modifier(new Entete_facture(type.getSelectionModel().getSelectedItem().toString(), Date.valueOf(date.getValue()), Integer.valueOf(tier.getText()), Integer.valueOf(ref.getText())));
            JOptionPane.showMessageDialog(null, "Facture Modifiée");
            ObservableList<String> list1 = FXCollections.observableArrayList("achat", "vente");
            type.setItems(list1);
            col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
            ObservableList<Entete_facture> list = sa.getFactureList();
            tabfacture.setItems(list);
            ref.setText(null);
            date.setValue(null);
            type.setPromptText(null);
            tier.setText(null);
        }
    }

    @FXML
    private void ajouterfacture(javafx.event.ActionEvent mouseEvent) throws SQLException {
        if (mouseEvent.getSource() == ajoutfact) {

            ServiceEnteteFacture sa = new ServiceEnteteFacture();
            sa.ajouter(new Entete_facture(type.getSelectionModel().getSelectedItem().toString(), Date.valueOf(date.getValue()), (int) Integer.parseInt(tier.getText()), (int) Integer.parseInt(ref.getText())));

            JOptionPane.showMessageDialog(null, "Facture Ajoutée");
            ObservableList<String> list1 = FXCollections.observableArrayList("achat", "vente");
            type.setItems(list1);
            col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
            ObservableList<Entete_facture> list = sa.getFactureList();
            tabfacture.setItems(list);
            ref.setText(null);
            date.setValue(null);
            type.setPromptText(null);
            tier.setText(null);
        }
    }

     @FXML
    private void supprimerarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == suppart) {
            ServiceDetailFacture sa = new ServiceDetailFacture();
            sa.supprimer(new Detail_facture(Integer.parseInt(ref.getText()), Integer.parseInt(refarticle.getText())));

            //tabarticle.getItems().removeAll(tabarticle.getSelectionModel().getSelectedItem());
            JOptionPane.showMessageDialog(null, "Article Supprimée");
            col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));

            ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
            tabarticle.setItems(list);

        }
    }


    @FXML
    private void retourneaccuiel(ActionEvent event) {
    }

  @FXML
    private void supprimerfacture(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == suppfact) {

            ServiceEnteteFacture sa = new ServiceEnteteFacture();
            sa.supprimer(new Entete_facture(Integer.parseInt(ref.getText())));

            JOptionPane.showMessageDialog(null, "Facture Supprimée");
            ObservableList<String> list1 = FXCollections.observableArrayList("achat", "vente");
            type.setItems(list1);
            col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
            ObservableList<Entete_facture> list = sa.getFactureList();
            tabfacture.setItems(list);
            ref.setText(null);
            date.setValue(null);
            type.setPromptText(null);
            tier.setText(null);
            tabarticle.setItems(null);

        }
    }


    @FXML
    private void getselctedfacture(MouseEvent event) {

        if (ajoutcheck.isSelected()) {
            ajoutcheck.setSelected(true);

            ref.setEditable(false);
            date.setEditable(false);
            type.setEditable(false);
            tier.setEditable(false);

            label.setText("ajout");
            label.setLayoutX(152);

            suppart.setDisable(false);
            modiffact.setDisable(true);
            ajoutfact.setDisable(false);
            ajoutart.setDisable(false);
            suppart.setDisable(false);
            annuler.setDisable(false);
            index = tabfacture.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            ref.setText(col_reffac.getCellData(index).toString());
            date.setValue(col_date.getCellData(index).toLocalDate());
            type.setPromptText(col_type.getCellData(index));
            tier.setText(col_tier.getCellData(index).toString());
            ref.setText(col_reffac.getCellData(index).toString());
            date.setValue(col_date.getCellData(index).toLocalDate());
            type.setPromptText(col_type.getCellData(index).toString());
            tier.setText(col_tier.getCellData(index).toString());
            col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
            ServiceDetailFacture sa = new ServiceDetailFacture();

            ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
            tabarticle.setItems(list);
        } else if (modifcheck.isSelected()) {
            index = tabfacture.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }

            ref.setText(col_reffac.getCellData(index).toString());
            date.setValue(col_date.getCellData(index).toLocalDate());
            type.setPromptText(col_type.getCellData(index).toString());
            tier.setText(col_tier.getCellData(index).toString());
            col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
            ServiceDetailFacture sa = new ServiceDetailFacture();

            ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
            tabarticle.setItems(list);

        } else {
            index = tabfacture.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            ref.setEditable(false);
            date.setEditable(false);
            type.setEditable(false);
            tier.setEditable(false);

            ref.setText(col_reffac.getCellData(index).toString());
            date.setValue(col_date.getCellData(index).toLocalDate());
            type.setPromptText(col_type.getCellData(index).toString());
            tier.setText(col_tier.getCellData(index).toString());
            col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
            ServiceDetailFacture sa = new ServiceDetailFacture();

            ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
            tabarticle.setItems(list);

        }
    }

      @FXML
    private void ajoutercheck(ActionEvent event) {

        label.setText("Ajout ");
        label.setLayoutX(152);
        ref.setEditable(true);
        date.setEditable(true);
        type.setEditable(true);
        tier.setEditable(true);

        ajoutart.setDisable(false);
        ajoutfact.setDisable(false);
        annuler.setDisable(false);
        modiffact.setDisable(true);
        suppart.setDisable(false);
        ref.setText(null);
        date.setValue(null);
        tier.setText(null);
        type.setPromptText(null);
        col_refart.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_refart.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setRef_article(event.getNewValue());
            }
        });
        col_lib.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lib.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, String>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, String> event) {
                Detail_facture df = event.getRowValue();
                df.setLibelle(event.getNewValue());
            }
        });

        col_qt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_qt.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setQt(event.getNewValue());
            }
        });
    }

    @FXML
    private void modifiercheck(ActionEvent event) {

        label.setText("Modification");
        label.setLayoutX(100);
        ref.setEditable(false);
        date.setEditable(true);
        type.setEditable(true);
        tier.setEditable(true);
        modiffact.setDisable(false);
        ajoutfact.setDisable(true);
        annuler.setDisable(false);
        suppart.setDisable(false);
        ajoutart.setDisable(false);
        ref.setText(null);
        date.setValue(null);
        tier.setText(null);
        type.setPromptText(null);
        col_refart.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_refart.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setRef_article(event.getNewValue());

            }
        });
        col_lib.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lib.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, String>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, String> event) {
                Detail_facture df = event.getRowValue();
                df.setLibelle(event.getNewValue());

            }
        });

        col_qt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_qt.setOnEditCommit(new EventHandler<CellEditEvent<Detail_facture, Integer>>() {
            @Override
            public void handle(CellEditEvent<Detail_facture, Integer> event) {
                Detail_facture df = event.getRowValue();
                df.setQt(event.getNewValue());
            }
        });
        tabarticle.setItems(null);

    }

    @FXML
    private void consultercheck(ActionEvent event) {
        label.setText("Consultation");
        label.setLayoutX(100);
        ref.setEditable(false);
        date.setEditable(false);
        type.setEditable(false);
        tier.setEditable(false);

        ref.setText(null);
        date.setValue(null);
        type.setPromptText(null);

        tier.setText(null);
        annuler.setDisable(true);
        suppart.setDisable(true);
        ajoutart.setDisable(true);
        modiffact.setDisable(true);
        ajoutfact.setDisable(true);
        tabarticle.setItems(null);

    }

     @FXML
    void recherchetype() {
        col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
        ServiceEnteteFacture sa = new ServiceEnteteFacture();
        ObservableList<Entete_facture> list = sa.getFactureList();
        tabfacture.setItems(list);

        FilteredList<Entete_facture> filteredData = new FilteredList<>(list, b -> true);
		rechtype.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Entete_facture) -> {
								
				if (newValue == null ) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Entete_facture.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                                			}
                                
                        
				   else  
				    	 return false; 
			});
		});
         
		SortedList<Entete_facture> sortedData = new SortedList<>(filteredData);
              
		sortedData.comparatorProperty().bind(tabfacture.comparatorProperty());
		
		
		tabfacture.setItems(sortedData);
    }


    @FXML
    void pdf(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == imprim) {
            try {

                String file_name = "C:\\Users\\ArwaBj\\Downloads\\SurTerrain\\src\\facture.pdf";
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
                document.open();
                //Image
                Image img = Image.getInstance("C:\\Users\\ArwaBj\\Downloads\\SurTerrain\\src\\imagedesk\\grp.png");
                img.setAlignment(Image.MIDDLE);

                document.add(img);
                Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22);
                f1.setColor(BaseColor.RED);
                f1.isBold();
                //Title
                Paragraph para = new Paragraph("Facture", f1);
                document.add(new Paragraph(""));
                document.add(new Paragraph(""));
                document.add(new Paragraph(""));
                document.add(new Paragraph(""));
                document.add(new Paragraph(""));
                document.add(new Paragraph(""));
                para.setAlignment(Element.ALIGN_CENTER);
                document.add(para);

//entet facture
                Connection cnx = DataSource.getInstance().getCnx();
                String requete = "SELECT * FROM entete_facture where ref_facture= '" + ref.getText() + "'";
                PreparedStatement pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18);
                    f2.setColor(BaseColor.BLACK);
                    Paragraph typepara = new Paragraph("N° :" + rs.getInt("ref_facture") + "\n" + "Type :" + rs.getString("type") + "\n" + "Tier :" + rs.getInt("tier") + "\n" + "Date Facture: " + rs.getString("date_exp"), f2);
                    typepara.setAlignment(Element.ALIGN_CENTER);
                    document.add(typepara);
                    Paragraph ngm = new Paragraph("************************************************************************");
                    ngm.setAlignment(Element.ALIGN_CENTER);
                    document.add(ngm);
                    document.add(new Paragraph(""));
                }
                Font f3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22);
                f3.setColor(BaseColor.GREEN);
                Paragraph para1 = new Paragraph("Article Mvmt", f3);
                para1.setAlignment(Element.ALIGN_CENTER);
                document.add(para1);
                Paragraph para2 = new Paragraph(" ");
                document.add(para2);
                PdfPTable tbl = new PdfPTable(3);
                tbl.getDefaultCell().setBorderWidth(0.5f);
                tbl.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
                ObservableList<Detail_facture> list = FXCollections.observableArrayList();
                String requete2 = " SELECT * FROM detail_facture where ref_facture= '" + ref.getText() + "'";
                PreparedStatement pst1 = cnx.prepareStatement(requete2);
                ResultSet rs1 = pst1.executeQuery();

                while (rs1.next()) {
                    Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
                    f2.setColor(BaseColor.BLACK);
                    Paragraph typepara1 = new Paragraph(" numéro d'article:  " + rs1.getInt("ref_article") + " Libelle Facture : " + rs1.getString("libelle") + " La Quantité: " + rs1.getInt("qt") + "\n", f2);

                    typepara1.setAlignment(Element.ALIGN_CENTER);
                    document.add(typepara1);
                }
                 Paragraph ngm = new Paragraph("************************************************************************");
                    ngm.setAlignment(Element.ALIGN_CENTER);
                    document.add(ngm);
                    document.add(new Paragraph(""));
                String requete3 = " SELECT count(detail_facture.qt*article.prix) FROM detail_facture , article where article.ref= detail_facture.ref_article and article.ref='" + ref.getText() + "'";
                PreparedStatement pst2 = cnx.prepareStatement(requete3);
                ResultSet rs2 = pst2.executeQuery();

                while (rs1.next()) {
                    Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
                    f2.setColor(BaseColor.BLACK);
                    Paragraph typepara7 = new Paragraph(" Prix Total:  " + rs2 + "\n", f2);

                    typepara7.setAlignment(Element.ALIGN_CENTER);
                    document.add(typepara7);
                }
                JOptionPane.showMessageDialog(null, "Facture préparée");

                document.close();

            } catch (Exception ex) {
                System.out.println(ex
                );
            }

        }
    }

    @FXML
    private void trier(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == trie) {
            col_reffac.setCellValueFactory(new PropertyValueFactory<>("ref_facture"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_tier.setCellValueFactory(new PropertyValueFactory<>("tier"));
                    ServiceEnteteFacture sa = new ServiceEnteteFacture();

            ObservableList<Entete_facture> list = sa.trier_entetefacture();
            tabfacture.setItems(list);
            
        }
    }

         @FXML
    void sendmail(javafx.event.ActionEvent mouseEvent) {
     if (mouseEvent.getSource() == mail) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/mailing.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void smsbtn(javafx.event.ActionEvent mouseEvent) throws MalformedURLException, UnsupportedEncodingException, IOException {
        if (mouseEvent.getSource() == sms) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Gui/sms.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

   

   @FXML
    private void ajouterarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == ajoutart) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajoutartfact.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccuielController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        col_refart.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
        ServiceDetailFacture sa = new ServiceDetailFacture();
//
        ObservableList<Detail_facture> list = sa.getArticleList(Integer.parseInt(ref.getText()));
        tabarticle.setItems(list);
    }
  
    
}
