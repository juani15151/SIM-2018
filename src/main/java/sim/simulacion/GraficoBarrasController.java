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
import java.util.Set;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import pruebas.PruebaChiCuadrado;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;
import pruebas.PruebaChiCuadradoUniformeAB;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class GraficoBarrasController implements Initializable {

    private final IntegerProperty tamañoMuestra = new SimpleIntegerProperty(1024);
    private final IntegerProperty cantidadIntervalos = new SimpleIntegerProperty(16);

    private final DoubleProperty frecuenciaEsperada = new SimpleDoubleProperty();
    private final DoubleProperty chiObservado = new SimpleDoubleProperty();
    private final BooleanProperty pasoChi = new SimpleBooleanProperty();

    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private CategoryAxis chartXAxis;
    @FXML
    private TextField fieldTamañoMuestra;
    @FXML
    private TextField fieldCantidadIntervalos;
    @FXML
    private Label frecuenciaEsperadaLabel;
    @FXML
    private Label chiObservadoLabel;
    @FXML
    private Label pasoChiLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cambia el Ancho de las barras.
        this.setXAxis();
        chart.categoryGapProperty().set(0);
        chart.barGapProperty().set(0);

        fieldTamañoMuestra.textProperty().bindBidirectional(this.tamañoMuestra, new NumberStringConverter());
        fieldCantidadIntervalos.textProperty().bindBidirectional(this.cantidadIntervalos, new NumberStringConverter());
        frecuenciaEsperadaLabel.textProperty().bindBidirectional(this.frecuenciaEsperada, new NumberStringConverter());
        chiObservadoLabel.textProperty().bindBidirectional(this.chiObservado, new NumberStringConverter());
        pasoChiLabel.textProperty().bindBidirectional(this.pasoChi, new BooleanStringConverter());
    }

    @FXML
    private void generateFromGenerador(ActionEvent event) {
        this.generate(new GeneradorUniforme());
    }

    @FXML
    private void generateFromRandom(ActionEvent event) {
        this.generate(new GeneradorJava());
    }
    
    @FXML
    private void graficar(ActionEvent onAction){
    
}
    private void generate(IGenerador generador) {
//        resetChart();
//        setXAxis();
//        PruebaChiCuadrado test =  new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get());
//        test.setTamañoMuestra(tamañoMuestra.get());
//        int[] frecuencias = test.observarFrecuenciasPorIntervalo();
//        this.frecuenciaEsperada.set((double) tamañoMuestra.get() / cantidadIntervalos.get());
//        this.chiObservado.set(test.calcularChi(frecuencias));
//        this.pasoChi.set(test.runTest(frecuencias));
//
//        XYChart.Series<String, Double> barras = new XYChart.Series<>();
//        barras.setName("Distribucion Generada");
//        for (int i = 0; i < cantidadIntervalos.get(); i++) {
//            barras.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), (double) frecuencias[i]));
//        }

//        
//        chart.getData().add(barras);
//        chart.getData().add(generarDistribucionUniforme());
    }

    private XYChart.Series<String, Double> generarDistribucionUniforme() {
        XYChart.Series<String, Double> barras = new XYChart.Series<>();
        barras.setName("Distribucion Uniforme");
        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            barras.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), frecuenciaEsperada.get()));
        }

        return barras;
    }

    private void setXAxis() {
        List<String> labelList = new ArrayList(cantidadIntervalos.get());
        double tamañoIntervalo =  1.0 / this.cantidadIntervalos.doubleValue();
        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            String limites = String.format("%1.2f-%1.2f", tamañoIntervalo * i, tamañoIntervalo * (i+1));
            labelList.add(limites);
        }

        ObservableList<String> labels = FXCollections.observableArrayList();
        labels.addAll(labelList);
        // Assign the month names as categories for the horizontal axis.
        chartXAxis.setCategories(labels);
    }

    private void resetChart() {
        chart.getData().clear();
        chartXAxis.getCategories().clear();
    }

}
