package sim.simulacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import generadores.GeneradorExponencial;
import generadores.GeneradorJava;
import generadores.GeneradorNormal;
import generadores.GeneradorNormalConvolucion;
import generadores.GeneradorPoisson;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;
import pruebas.Muestra;
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
    @FXML
    private ComboBox<String> cmb_distribuciones;
    @FXML
    private Button btn_generar;

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
        cmb_distribuciones.getItems().add("Uniforme");
        cmb_distribuciones.getItems().add("Normal Box Muller");
        cmb_distribuciones.getItems().add("Normal Convolucion");
        cmb_distribuciones.getItems().add("Exponencial Negativa");
        cmb_distribuciones.getItems().add("Poisson");
        
    }
    
    @FXML
    private void generateFromGenerador(ActionEvent event) {
        if(cmb_distribuciones.getItems().get(0).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorUniforme();
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
         if(cmb_distribuciones.getItems().get(1).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorNormal(10,100);
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
          if(cmb_distribuciones.getItems().get(2).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorNormalConvolucion(10,100);
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
           if(cmb_distribuciones.getItems().get(3).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorExponencial();
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
            if(cmb_distribuciones.getItems().get(4).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorPoisson(120);
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
        
    }

   
    private void lala(){
        
    }
    
    private void generate(PruebaChiCuadrado test) {
        resetChart();
        setXAxis();
        this.pasoChi.set(test.runTest());

        int[] frecuenciasObservadas = test.getFrecuenciasObservadas();
        double[] frecuenciasEsperadas = test.getFrecuenciasEsperadas();

        this.frecuenciaEsperada.set((double) tamañoMuestra.get() / cantidadIntervalos.get());
        this.chiObservado.set(test.getChiObservado());

        XYChart.Series<String, Double> graficoDistribucionGenerada = new XYChart.Series<>();
        graficoDistribucionGenerada.setName("Distribucion Generada");

        XYChart.Series<String, Double> graficoDistribucionIdeal = new XYChart.Series<>();
        graficoDistribucionIdeal.setName("Distribucion Ideal");
        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            graficoDistribucionGenerada.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), (double) frecuenciasObservadas[i]));
            graficoDistribucionIdeal.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), (double) frecuenciasEsperadas[i]));
        }

        chart.getData().add(graficoDistribucionGenerada);
        chart.getData().add(graficoDistribucionIdeal);

    }

    private void setXAxis() {
        List<String> labelList = new ArrayList(cantidadIntervalos.get());
        double tamañoIntervalo = 1.0 / this.cantidadIntervalos.doubleValue();
        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            String limites = String.format("%1.2f-%1.2f", tamañoIntervalo * i, tamañoIntervalo * (i + 1));
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
