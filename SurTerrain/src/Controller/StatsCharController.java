/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ServiceTerrain;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author OussKh
 */
public class StatsCharController implements Initializable {

    @FXML
    private PieChart stat;
    @FXML
    private AnchorPane pane;
    Services.ServiceTerrain st=new ServiceTerrain();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                        new PieChart.Data("réservé", this.statData()),
                        new PieChart.Data("disponible", 100 - this.statData()));
        stat.setData(pieChartData);
        stat.setTitle("Etat des terrains ");
        stat.setLabelLineLength(10);
        stat.setLegendSide(Side.LEFT);
        
        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : stat.getData()) {
           
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    double res=round(data.getPieValue(),2);
                    caption.setText(String.valueOf(res) + "%");
                  
                }
            });

        }       pane.getChildren().add(caption);
    }   
    
    private double statData() {

        int nbtotal = st.nbTerrainTotal();
        int nbTerrainRes = st.nbTerrainParEtat("réservé");
        
        double resultatReservé = ((double) nbTerrainRes / nbtotal) * 100;
//        System.out.println(nbTerrainRes);
//        System.out.println(st.nbTerrainParEtat("disponible"));
//        System.out.println(resultatReservé);
        
        return resultatReservé;   
    }
    
    private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }
}
          