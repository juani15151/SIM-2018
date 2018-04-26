/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;

/**
 *
 * @author Camila
 */
public class PruebaChiCuadradoPoisson extends PruebaChiCuadradoAjustable {

    public PruebaChiCuadradoPoisson(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        // Calculamos la probabilidad puntual y la aproximamos al intervalo.
        double p = probabilidadPuntual(intervalo.marcaDeClase(), muestra.media());
        return p * intervalo.recorrido();
    }

    private double probabilidadPuntual(double x, double lambda) {
        // En poisson lamba == media.
        return ((Math.pow(lambda, x)) * Math.exp(-lambda)) / (factorial(x));
    }

    private double factorial(double x) {
        double resultado = x;
        for (double i = x - 1; i > 1; i--) {
            resultado *= i;
        }
        return resultado;
    }

    @Override
    int cantidadValoresEmpiricos() {
        return 1;
    }

}
