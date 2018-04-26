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
public class PruebaChiCuadradoPoisson extends PruebaChiCuadrado {

    public PruebaChiCuadradoPoisson(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    int getCantidadValoresEmpiricos() {
        return 1;
    }

    @Override
    double getFrecuenciaEsperada(Intervalo intervalo) {
        return probabilidadPuntual(intervalo.getMark(), media) * intervalo.getSize() * this.tamañoMuestra;
    }

    private double probabilidadPuntual(double x, double lambda) {
        return ((Math.pow(lambda, x)) * Math.exp(-lambda)) / (factorial(x));
    }

    private double factorial(double x) {
        double resultado = x;        
        for (double i = x - 1; i > 1; i--) {
            resultado *= i;
        }
        return resultado;
    }
}
