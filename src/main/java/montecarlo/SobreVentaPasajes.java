/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import montecarlo.generadoresPasajeros.IGeneradorPasajeros;


/**
 *
 * @author juani
 */
public class SobreVentaPasajes {

    // Parametros
    private int capacidadVuelo = 30;
    private IGeneradorPasajeros generadorPasajeros;
    private double utilidadPorPasajero = 100.0;
    private double costoSobreVenta = 150.0;  // El costo de que se presente 1 pasajero extra.

    // Vector de estado.
    private int reloj;
    private double rndCantidadPasajeros;
    private int cantidadPasajeros;
    private double utilidad;
    private double utilidadAcumulada;

    /**
     * @param generadorPasajeros el generador de cantidad de pasajeros.
     */
    public SobreVentaPasajes(IGeneradorPasajeros generadorPasajeros) {
        this.generadorPasajeros = generadorPasajeros;
        reloj = 0;
        utilidadAcumulada = 0;
    }

    /**
     * Avanza el reloj en 'n' iteraciones.
     * @param n: cantidad de iteraciones a realizar.
     */
    public void iterar(int n) {
        for (; n >= 0; n--) {
            iterar();
        }
    }

    /**
     * Avanza el reloj en 1.
     * Se calcula todo lo correspondiente a esa fila.
     */
    public void iterar() {        
        reloj++;        
        // Cantidad de pasajeros.
        cantidadPasajeros = generadorPasajeros.nextCantidadPasajeros();
        rndCantidadPasajeros = generadorPasajeros.lastRND();
        // Calculo de utilidad:
        int pasajerosAbordados = cantidadPasajeros >= capacidadVuelo ? capacidadVuelo : cantidadPasajeros;
        int pasajerosSobrevendidos = cantidadPasajeros - pasajerosAbordados;
        utilidad = pasajerosAbordados * utilidadPorPasajero - pasajerosSobrevendidos * costoSobreVenta;
        // Acumuladores
        utilidadAcumulada += utilidad;
    }

    /**
     * Este metodo tiene que retornar el vector de estado de forma que se pueda
     * agregar facilmente a una tabla. (Hay que adecuar lo que retorna, puede ser
     * otro objeto, no solo Object[]...
     */
    public Object[] getEstado() {
        return null; // TODO
    }
    
    /**
     * @return la utilidadPromedio o Double.NaN si no se realizaron iteraciones.
     */
    public double utilidadPromedio(){
        return utilidadAcumulada / reloj;
    }
}
