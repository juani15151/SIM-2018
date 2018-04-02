package sim.simulacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import generadores.GeneradorJava;
import generadores.GeneradorUniforme;
import generadores.IGenerador;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import pruebas.PruebaChiCuadrado;
import javafx.collections.FXCollections;
import pruebas.PruebaChiCuadradoUniforme;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class GraficoBarrasController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private CategoryAxis chartXAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> labelList = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            labelList.add(String.valueOf(i));
        }

        ObservableList<String> labels = FXCollections.observableArrayList();
        labels.addAll(labelList);

        // Assign the month names as categories for the horizontal axis.
        chartXAxis.setCategories(labels);
    }

    @FXML
    private void generateFromGenerador(ActionEvent event) {
        this.generate(new GeneradorUniforme());
    }

    @FXML
    private void generateFromRandom(ActionEvent event) {
        this.generate(new GeneradorJava());
    }

    private void generate(IGenerador generador) {
        
        PruebaChiCuadrado test = new PruebaChiCuadradoUniforme(generador, 10);
        int[] frecuencias = test.observarFrecuenciasPorIntervalo();

        XYChart.Series<String, Integer> barras = new XYChart.Series<>();

        for (int i = 0; i < 10; i++) {

            barras.getData().add(new XYChart.Data<>(String.valueOf(i), frecuencias[i]));
        }

        chart.getData().add(barras);
    }

}
