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
import javax.swing.JOptionPane;
import pruebas.Muestra;
import pruebas.PruebaChiCuadradoUniformeAB;
import pruebas.*;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class GraficoBarrasController implements Initializable {

    private final IntegerProperty tamañoMuestra = new SimpleIntegerProperty(1024);
    private final IntegerProperty cantidadIntervalos = new SimpleIntegerProperty(16);
    private final IntegerProperty cantidadIntervalosGrafico = new SimpleIntegerProperty(16);

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
    private Button btn_graficar;
    @FXML
    private Label txt_cantIntGraficar;
    @FXML
    private TextField fieldCantidadIntervalosAGraficar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cambia el Ancho de las barras.
        chart.categoryGapProperty().set(0);
        chart.barGapProperty().set(0);

        fieldTamañoMuestra.textProperty().bindBidirectional(this.tamañoMuestra, new NumberStringConverter());
        fieldCantidadIntervalos.textProperty().bindBidirectional(this.cantidadIntervalos, new NumberStringConverter());
        fieldCantidadIntervalosAGraficar.textProperty().bindBidirectional(this.cantidadIntervalosGrafico, new NumberStringConverter());
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
        if(!validarCantidadIntervalos()){
        if(cmb_distribuciones.getItems().get(0).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorUniforme();
        this.generate(new PruebaChiCuadradoUniformeAB(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
         if(cmb_distribuciones.getItems().get(1).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorNormal(10,20);
        this.generate(new PruebaChiCuadradoNormal(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
          if(cmb_distribuciones.getItems().get(2).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorNormalConvolucion(10,20);
        this.generate(new PruebaChiCuadradoNormal(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
           if(cmb_distribuciones.getItems().get(3).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorExponencial();
        this.generate(new PruebaChiCuadradoExponencial(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }
            if(cmb_distribuciones.getItems().get(4).equals(cmb_distribuciones.getValue())){
        IGenerador generador = new GeneradorPoisson(20);
        this.generate(new PruebaChiCuadradoPoisson(generador, cantidadIntervalos.get(), tamañoMuestra.get()));
        }}
        else{
            JOptionPane.showMessageDialog(null, "Cantidad de intervalos debe ser mayor a 0 y menor a 28");
            limpiarCampos();
        }
        
    }

   

    private boolean validarCantidadIntervalos(){
        return Integer.parseInt(fieldCantidadIntervalos.getText()) > 28 || Integer.parseInt(fieldCantidadIntervalos.getText()) <= 0;
    }
    
    private void limpiarCampos(){
        fieldCantidadIntervalos.setText("1");
    }
    
    
    
    private void generate(PruebaChiCuadrado test) {
        resetChart();
        this.pasoChi.set(test.runTest());
        
        List<Intervalo> intervalosUniformes = generarIntervalosUniformes(test.getMuestra(), cantidadIntervalosGrafico.get());        
        setXAxis(intervalosUniformes);
        int[] frecuenciasObservadas = test.getMuestra().frecuenciaPorIntervalo(intervalosUniformes);
        double[] frecuenciasEsperadas = buscarFrecuenciasEsperadas(test.getMuestra(), intervalosUniformes, test);

        this.chiObservado.set(test.getChiObservado());

        XYChart.Series<String, Double> graficoDistribucionGenerada = new XYChart.Series<>();
        graficoDistribucionGenerada.setName("Distribucion Generada");

        XYChart.Series<String, Double> graficoDistribucionIdeal = new XYChart.Series<>();
        graficoDistribucionIdeal.setName("Distribucion Ideal");
        for (int i = 0; i < intervalosUniformes.size(); i++) {
            graficoDistribucionGenerada.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), (double) frecuenciasObservadas[i]));
            graficoDistribucionIdeal.getData().add(new XYChart.Data<>(chartXAxis.getCategories().get(i), (double) frecuenciasEsperadas[i]));
        }

        chart.getData().add(graficoDistribucionGenerada);
        chart.getData().add(graficoDistribucionIdeal);

    }
    
    private double[] buscarFrecuenciasEsperadas(Muestra muestra, List<Intervalo> intervalos, PruebaChiCuadrado prueba){
        double[] frecuenciasEsperadas = new double[intervalos.size()];
        for (int i = 0; i < intervalos.size(); i++) {
            // Seleccionar 1 intervalo
            Intervalo intervalo = intervalos.get(i);
            frecuenciasEsperadas[i] = prueba.frecuenciaEsperada(intervalo, muestra);             
        }
        return frecuenciasEsperadas;
    }
    
    private List<Intervalo> generarIntervalosUniformes(Muestra muestra, int cantidadIntervalos){
                // Creamos los intervalos pedidos y agregamos 2 intervalos al inicio y final.
        List<Intervalo> intervalos = new ArrayList<>(cantidadIntervalos + 2);

        // Agregamos intervalo desde el infinito negativo a la muestra.
        intervalos.add(new Intervalo(Double.NEGATIVE_INFINITY, muestra.minimo()));

        // Definimos intervalos iguales.
        double tamañoIntervalo = muestra.recorrido() / cantidadIntervalos;
        if(cmb_distribuciones.getItems().get(4).equals(cmb_distribuciones.getValue())){ // Poisson
            tamañoIntervalo = Math.ceil(tamañoIntervalo);
        }
        double limiteInferior = Math.floor(muestra.minimo());

        for (int i = 1; i <= cantidadIntervalos; i++) {
            double limiteSuperior = limiteInferior + tamañoIntervalo;
            intervalos.add(new Intervalo(limiteInferior, limiteSuperior));
            limiteInferior = limiteSuperior;
        }

        // Agregamos intervalos hasta el infinito positivo.  
        intervalos.add(new Intervalo(muestra.maximo(), Double.POSITIVE_INFINITY));

        // Valida y une los intervalos pequeños.
        return intervalos;
    }

    private void setXAxis(List<Intervalo> intervalos) {
        List<String> labelList = new ArrayList(intervalos.size());
        Double j = 0.0;
        for(Intervalo i : intervalos){
            String limites = String.format("%1.2f-%1.2f", i.getInicio(), i.getFin());
            labelList.add(limites);
            //labelList.add((j++).toString());
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
