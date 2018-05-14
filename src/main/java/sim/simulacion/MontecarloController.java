package sim.simulacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import montecarlo.SobreVentaPasajes;
import montecarlo.generadoresPasajeros.Generador32Reservas;
import montecarlo.generadoresPasajeros.IGeneradorPasajeros;

/**
 * FXML Controller class
 *
 * @author juani
 */
public class MontecarloController implements Initializable {

    
    private int cantidadIteraciones;
    private int graficarDesde;
    private int graficarHasta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /**
     * Retorna el generador que desea utilizar el usuario.
     * @return 
     */
    private IGeneradorPasajeros getGenerador(){
        // TODO
        // Normalmente este metodo debe devolver el valor elegido en un
        // combo de generadores (Gen 31, 32, 33 o 34).
        return new Generador32Reservas();
    }
    
    public void run(){
        reset();
        SobreVentaPasajes simulacion = new SobreVentaPasajes(getGenerador());
        // Iteramos hasta el primero que hay que graficar.
        simulacion.iterar(graficarDesde - 1);
        for(int i = graficarDesde; i < graficarHasta; i++){
            simulacion.iterar();
            // TODO: Graficar estado.
            simulacion.getEstado();
        }
    }
    
    /**
     * Reinicia la tabla y todos los controles necesarios para el inicio de una
     * simulacion.
     */
    private void reset(){
        // TODO: Reiniciar tabla.
    }
    
}
