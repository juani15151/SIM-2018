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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import pruebas.PruebaChiCuadrado;
import pruebas.PruebaChiCuadradoUniforme;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class GraficoBarrasController implements Initializable {

    @FXML
    private BarChart<Integer, Integer> chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generateFromGenerador(ActionEvent event) {
        this.generate(new GeneradorUniforme(100));
    }

    @FXML
    private void generateFromRandom(ActionEvent event) {
        this.generate(new GeneradorJava(100l));
    }
    
    private void generate(IGenerador generador){
        PruebaChiCuadrado test = new PruebaChiCuadradoUniforme(generador, 10);
        int[] frecuencias = test.observarFrecuenciasPorIntervalo();       
        
        XYChart.Series<Integer, Integer> barras = new XYChart.Series<>();
        
        for(int i = 0; i < 10; i++){

            barras.getData().add(new XYChart.Data<>(0, frecuencias[i]));
        }
        
        chart.getData().add(barras);
    }
    
}
