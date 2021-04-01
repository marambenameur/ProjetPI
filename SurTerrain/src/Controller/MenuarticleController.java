/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import models.Article;
import services.ServiceArticle;
import Utils.DataSource;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class MenuarticleController implements Initializable {

    @FXML
    private TextField rechart;
    @FXML
    private TextField lib;
    @FXML
    private TableView<Article> tab_art;
    @FXML
    private TableColumn<Article, Integer> col_ref;
    @FXML
    private TableColumn<Article, String> col_lib;
    @FXML
    private TableColumn<Article, String> col_cat;
    @FXML
    private TableColumn<Article, Integer> col_qt;
    @FXML
    private TableColumn<Article, Integer> col_prix;
    @FXML
    private TableColumn<Article, String> col_img;
    @FXML
    private Button suppart;

    @FXML
    private Button triprix;
    @FXML
    private Button modifart;
    @FXML
    private Button ajoutart;
    @FXML
    private Label label;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField ref;
    @FXML
    private TextField qt;
    @FXML
    private TextField prix;
    @FXML
    private TextField imgtext;
    @FXML
    private ComboBox cat;
    @FXML
    private Button annulerart;
    @FXML
    private RadioButton radioajout;
    @FXML
    private ToggleGroup arwa;
    @FXML
    private RadioButton radiomodif;
    @FXML
    private RadioButton radioconsult;
    private Integer index = -1;
    ObservableList<Article> listearticle;
    private Image image;
    @FXML
    private Button stat;
    @FXML
    private Label articleanim;
    @FXML
    private ImageView imagearticle;
    @FXML
    private Label error;
    ValidationSupport validationSupport = new ValidationSupport();
    private AnchorPane myanchronepane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         final Tooltip tooltipButton = new Tooltip();
        tooltipButton.setText("Référence article");
        ref.setTooltip(tooltipButton);
         final Tooltip tooltipButton1 = new Tooltip();
        tooltipButton1.setText("Liballe article");
        lib.setTooltip(tooltipButton1);
         final Tooltip tooltipButton2 = new Tooltip();
        tooltipButton2.setText("Catégorie article");
        cat.setTooltip(tooltipButton2);
         final Tooltip tooltipButton3 = new Tooltip();
        tooltipButton3.setText("Quantité article");
        qt.setTooltip(tooltipButton3);
         final Tooltip tooltipButton4 = new Tooltip();
        tooltipButton4.setText("URL image");
        imgtext.setTooltip(tooltipButton4);
        final Tooltip tooltipButton5 = new Tooltip();
        tooltipButton5.setText("Ajouter article");
        ajoutart.setTooltip(tooltipButton5);
         final Tooltip tooltipButton6 = new Tooltip();
        tooltipButton6.setText("Numéro de piéce de la facture");
        modifart.setTooltip(tooltipButton6);
         final Tooltip tooltipButton7 = new Tooltip();
        tooltipButton7.setText("Annuler un article");
        annulerart.setTooltip(tooltipButton7);
         final Tooltip tooltipButton8 = new Tooltip();
        tooltipButton8.setText("Tableau des articles");
        tab_art.setTooltip(tooltipButton8);
         final Tooltip tooltipButton9 = new Tooltip();
        tooltipButton9.setText("Recherche");
        rechart.setTooltip(tooltipButton9);
         final Tooltip tooltipButton10 = new Tooltip();
        tooltipButton10.setText("Tier article");
        triprix.setTooltip(tooltipButton10);
         final Tooltip tooltipButton11 = new Tooltip();
        tooltipButton11.setText("Statistiques");
        stat.setTooltip(tooltipButton11);
         final Tooltip tooltipButton12 = new Tooltip();
        tooltipButton12.setText("Supprimer article");
        suppart.setTooltip(tooltipButton12);
        ServiceArticle sa = new ServiceArticle();
                                 validationSupport.setErrorDecorationEnabled(true);

        rechercherarticle();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), articleanim);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), label);
        fadeTransition1.setFromValue(1.0);
        fadeTransition1.setToValue(0.0);

        fadeTransition1.setCycleCount(Animation.INDEFINITE);
        fadeTransition1.play();
        //animation imagearticle
        RotateTransition rt = new RotateTransition(Duration.seconds(3), imagearticle);
        rt.setByAngle(360);
        rt.setCycleCount(40);
        rt.setAutoReverse(true);
        rt.play();
        label.setText("Consultation Article");
        label.setLayoutX(55);
        modifart.setDisable(true);
        ajoutart.setDisable(true);
        annulerart.setDisable(true);
        ObservableList<String> list1 = FXCollections.observableArrayList("crampons", "veste", "accessoir", "proteine");
        cat.setItems(list1);
        col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
        ObservableList<Article> list = sa.getArticlesList();
        tab_art.setItems(list);

    }

    @FXML
    private void supprimeraticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == suppart) {

            ServiceArticle sa = new ServiceArticle();
            sa.supprimer(new Article(Integer.parseInt(ref.getText())));
            JOptionPane.showMessageDialog(null, "Article Supprimée");
            ObservableList<String> list1 = FXCollections.observableArrayList("crampons", "veste", "accessoir", "proteine");
            cat.setItems(list1);
            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
            ObservableList<Article> list = sa.getArticlesList();
            tab_art.setItems(list);
            ref.setText(null);
            lib.setText(null);
            cat.setPromptText(null);
            imgtext.setText(null);
            qt.setText(null);
            prix.setText(null);
            imgview.setImage(null);

        }
    }

    @FXML
    private void rechercherarticle() {
        ServiceArticle sa = new ServiceArticle();
        col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
        ObservableList<Article> list = sa.getArticlesList();
        tab_art.setItems(list);
        //ObservableList<Article> list = FXCollections.observableArrayList();

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Article> filteredData = new FilteredList<>(list, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        rechart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Article Article) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Article.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches libelle
                } else if (Article.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Article.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Article> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tab_art.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tab_art.setItems(sortedData);
    }

    @FXML
    void refvalid(javafx.event.ActionEvent mouseEvent) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
        List<Article> list = new ArrayList<>();

        String requete = "SELECT ref FROM article where ref='" + ref.getText() + "'";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Article(rs.getInt(1)));

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

                error.setText("la référence de l'article est un entier ");

            } else if ((!ref.getText().matches("[a-z,A-Z]"))) {
                ref.setStyle(null);
                error.setText(null);
            }
        } else {
            ref.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//                    error.setText(null);

//            new animatefx.animation.Shake(ref).play();
            error.setText("Cette article existe déja ");
        }


    }

    @FXML
    private void libvalid(ActionEvent event) {
        if ((lib.getText().matches("[0-9]")) || (lib.getText().length() == 0)) {
            lib.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(lib).play();
            error.setText("Le libelle  de l'article est une chaine de caractéres ");

        } else if ((lib.getText().matches("[A-Z,a-z]"))) {
            lib.setStyle(null);
            error.setText(null);
        }

    
}

@FXML
        private void qtvalid(ActionEvent event) {
        if ((qt.getText().matches("[A-Z,a-z]")) || (qt.getText().length() == 0)) {
            qt.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(qt).play();
            error.setText("La quantité   de l'article est un entier ");

        } else if ((qt.getText().matches("[0-9]"))) {
            qt.setStyle(null);
            error.setText(null);
        }

    }

    @FXML
        private void prixvalid(ActionEvent event) {
        if ((prix.getText().matches("[A-Z,a-z]")) || (prix.getText().length() == 0)) {
            prix.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(prix).play();
            error.setText("Le prix de l'article est un entier ");

        } else if ((prix.getText().matches("[0-9]"))) {
            prix.setStyle(null);
            error.setText(null);
        }

    }

    @FXML
        private void imgvalid(ActionEvent event) {
        if ((imgtext.getText().matches("[0-9]")) || (imgtext.getText().length() == 0)) {
            imgtext.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
//            new animatefx.animation.Shake(imgtext).play();
            error.setText("Le path d'image de l'article est une chaine de caractéres ");

        } else if ((imgtext.getText().matches("[A-Z,a-z]"))) {
            imgtext.setStyle(null);
            error.setText(null);
        }

    }

    @FXML
        private void catvalid(ActionEvent event) {

    }

   

            



        private void afficherarticle(ActionEvent event) {
        col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
        ServiceArticle sa = new ServiceArticle();
        ObservableList<Article> list = sa.getArticlesList();
        tab_art.setItems(list);
    }

    @FXML
        private void trierarticleprix(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == triprix) {
            ServiceArticle sa = new ServiceArticle();

            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            ObservableList<Article> list = sa.trier_article();
            tab_art.setItems(list);
        }
    }

    @FXML
        private void modifierarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == modifart) {

            ServiceArticle sa = new ServiceArticle();
            sa.modifier(new Article(lib.getText(), cat.getSelectionModel().getSelectedItem().toString(), imgtext.getText(), Integer.valueOf(prix.getText()), Integer.valueOf(qt.getText()), Integer.valueOf(ref.getText())));
            JOptionPane.showMessageDialog(null, "Article Modifiée");
            ObservableList<String> list1 = FXCollections.observableArrayList("crampons", "veste", "accessoir", "proteine");
            cat.setItems(list1);
            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
            ObservableList<Article> list = sa.getArticlesList();
            tab_art.setItems(list);
            ref.setText(null);
            lib.setText(null);
            cat.setPromptText(null);
            imgtext.setText(null);
            qt.setText(null);
            prix.setText(null);
            imgview.setImage(null);
        }
    }

    @FXML
        private void ajouterarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == ajoutart) {
      
           
            ServiceArticle sa = new ServiceArticle();
            sa.ajouter(new Article(lib.getText(), cat.getSelectionModel().getSelectedItem().toString(), imgtext.getText(), Integer.valueOf(prix.getText()), Integer.valueOf(qt.getText()), Integer.valueOf(ref.getText())));
            JOptionPane.showMessageDialog(null, "Article Ajoutée");
             

            ObservableList<String> list1 = FXCollections.observableArrayList("crampons", "veste", "accessoir", "proteine");
            cat.setItems(list1);
            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_qt.setCellValueFactory(new PropertyValueFactory<>("qt_article"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("image_article"));
            ObservableList<Article> list = sa.getArticlesList();
            tab_art.setItems(list);
            ref.setText(null);
            lib.setText(null);
            cat.setPromptText(null);
            imgtext.setText(null);
            qt.setText(null);
            prix.setText(null);
            imgview.setImage(null);
        }
    }

    @FXML
    void getSelected(MouseEvent event) {

        if ((radioajout.isSelected())) {
            ref.setEditable(false);
            lib.setEditable(false);
            imgtext.setEditable(false);
            qt.setEditable(false);
            prix.setEditable(false);
            radioajout.setSelected(false);
            label.setText("consultation");
            label.setLayoutX(100);

            ref.setText(null);
            lib.setText(null);
            imgtext.setText(null);
            qt.setText(null);
            cat.setPromptText(null);
            prix.setText(null);
            imgview.setImage(null);

            modifart.setDisable(true);
            ajoutart.setDisable(true);
            annulerart.setDisable(true);

            index = tab_art.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            ref.setText(col_ref.getCellData(index).toString());
            lib.setText(col_lib.getCellData(index).toString());
            cat.setPromptText(col_cat.getCellData(index));
            qt.setText(col_qt.getCellData(index).toString());
            prix.setText(col_prix.getCellData(index).toString());
            imgtext.setText(col_img.getCellData(index).toString());
        } else if ((radiomodif.isSelected())) {
            index = tab_art.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            ref.setEditable(false);
            lib.setEditable(true);
            cat.setEditable(true);
            imgtext.setEditable(true);
            qt.setEditable(true);
            prix.setEditable(true);

            ref.setText(col_ref.getCellData(index).toString());
            lib.setText(col_lib.getCellData(index).toString());
            cat.setPromptText(col_cat.getCellData(index).toString());
            qt.setText(col_qt.getCellData(index).toString());
            prix.setText(col_prix.getCellData(index).toString());
            imgtext.setText(col_img.getCellData(index).toString());

        } else {
             label.setText("Consultation ");
        label.setLayoutX(100);
            index = tab_art.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            ref.setEditable(false);
            lib.setEditable(false);
            cat.setEditable(false);
            imgtext.setEditable(false);
            qt.setEditable(false);
            prix.setEditable(false);

            ref.setText(col_ref.getCellData(index).toString());
            lib.setText(col_lib.getCellData(index));
            cat.setPromptText(col_cat.getCellData(index));
            qt.setText(col_qt.getCellData(index).toString());
            prix.setText(col_prix.getCellData(index).toString());
            imgtext.setText(col_img.getCellData(index));
            image = new Image(col_img.getCellData(index));

            imgview.setImage(image);

        }

    }

    @FXML
    void annuler(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == annulerart) {
            ref.setText(null);
            lib.setText(null);
            imgtext.setText(null);
            qt.setText(null);
            cat.setPromptText(null);
            prix.setText(null);
            imgview.setImage(null);
            
            
        }
    }
    
  
    @FXML
        private void ajoutcheck(ActionEvent event) {
        label.setText("Ajout ");
        label.setLayoutX(120);
        ref.setEditable(true);
        lib.setEditable(true);
        cat.setEditable(true);
        imgtext.setEditable(true);
        qt.setEditable(true);
        prix.setEditable(true);
        modifart.setDisable(true);
        ajoutart.setDisable(false);
        annulerart.setDisable(false);
        ref.setText(null);
        lib.setText(null);
        imgtext.setText(null);
        cat.setPromptText(null);
        qt.setText(null);
        prix.setText(null);
        imgview.setImage(null);
    }
  
    @FXML
        private void modifcheck(ActionEvent event) {
        label.setText("Modification ");
        label.setLayoutX(100);
        ref.setEditable(false);
        lib.setEditable(true);
        cat.setEditable(true);
        imgtext.setEditable(true);
        qt.setEditable(true);
        prix.setEditable(true);
        ajoutart.setDisable(true);
        modifart.setDisable(false);
        ref.setText(null);
        lib.setText(null);
        imgtext.setText(null);
        qt.setText(null);
        cat.setPromptText(null);

        prix.setText(null);
        imgview.setImage(null);
    }

    @FXML
        private void consultcheck(ActionEvent event) {

        label.setText("Consultation ");
        label.setLayoutX(100);
        ref.setEditable(false);
        lib.setEditable(false);
        cat.setEditable(false);
        imgtext.setEditable(false);
        qt.setEditable(false);
        prix.setEditable(false);

        ref.setText(null);
        lib.setText(null);
        cat.setPromptText(null);

        imgtext.setText(null);
        qt.setText(null);
        prix.setText(null);
        imgview.setImage(null);
        modifart.setDisable(true);
        ajoutart.setDisable(true);
        annulerart.setDisable(true);
    }

    @FXML
        private void statistique(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == stat) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/stat.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(AccuielController.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
