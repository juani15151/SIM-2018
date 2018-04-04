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
import javax.swing.JOptionPane;
import pruebas.PruebaChiCuadradoUniforme;
import utils.Validaciones;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class GraficoBarrasController implements Initializable {

    private final IntegerProperty tamañoMuestra = new SimpleIntegerProperty(100);
    private final IntegerProperty cantidadIntervalos = new SimpleIntegerProperty(10);

    private final DoubleProperty frecuenciaEsperada = new SimpleDoubleProperty();
    private final DoubleProperty chiObservado = new SimpleDoubleProperty();
    private final BooleanProperty pasoChi = new SimpleBooleanProperty();

    @FXML
    private BarChart<String, Integer> chart;
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
        if (validarNoNumerico()) {
            if (validarNoNegativo()) {
                if (menor31()) {
                    this.generate(new GeneradorUniforme());
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR, EL TAMAÑO DE MUESTRA DEBE SER MENOR O IGUAL A 31");
                    limpiarCampos();
                }

            } else {
                JOptionPane.showMessageDialog(null, "ERROR, SOLO VALORES positivos");
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, SOLO VALORES NUMERICOS");
            limpiarCampos();
        }
    }

    @FXML
    private void generateFromRandom(ActionEvent event) {
        if (validarNoNumerico()) {
            if (validarNoNegativo()) {
                if (menor31()) {
                    this.generate(new GeneradorJava());
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR, EL TAMAÑO DE MUESTRA DEBE SER MENOR O IGUAL A 31");
                    limpiarCampos();
                }

            } else {
                JOptionPane.showMessageDialog(null, "ERROR, SOLO VALORES POSITIVOS MAYORES A 0");
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, SOLO VALORES NUMERICOS");
            limpiarCampos();
        }
    }

    private void generate(IGenerador generador) {
        PruebaChiCuadrado test = new PruebaChiCuadradoUniforme(generador, cantidadIntervalos.get());
        test.setTamañoMuestra(tamañoMuestra.get());
        int[] frecuencias = test.observarFrecuenciasPorIntervalo();
        this.frecuenciaEsperada.set((double) tamañoMuestra.get() / cantidadIntervalos.get());
        this.chiObservado.set(test.calcularChi(frecuencias));
        this.pasoChi.set(test.runTest(frecuencias));

        XYChart.Series<String, Integer> barras = new XYChart.Series<>();

        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            barras.getData().add(new XYChart.Data<>(String.valueOf(i), frecuencias[i]));
        }

        resetChart();
        setXAxis();
        chart.getData().add(barras);
    }

    private void setXAxis() {
        List<String> labelList = new ArrayList(cantidadIntervalos.get());
        for (int i = 0; i < cantidadIntervalos.get(); i++) {
            labelList.add(String.valueOf(i));
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

    private boolean validarNoNumerico() {
        return Validaciones.isNumeric(fieldCantidadIntervalos.getText())
                && Validaciones.isNumeric(fieldTamañoMuestra.getText());
    }

    private boolean validarNoNegativo() {
        return (Validaciones.isPositive(Integer.parseInt(fieldCantidadIntervalos.getText()))
                && Validaciones.isPositive(Integer.parseInt(fieldTamañoMuestra.getText())));

    }

    private boolean menor31() {
        return Integer.parseInt(fieldCantidadIntervalos.getText()) <= 31;
    }

    private void limpiarCampos() {
        fieldCantidadIntervalos.setText("10");
        fieldTamañoMuestra.setText("1000");

    }

}
