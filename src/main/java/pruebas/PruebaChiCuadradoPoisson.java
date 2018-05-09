/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;
import java.util.List;

/**
 *
 * @author Camila
 */
public class PruebaChiCuadradoPoisson extends PruebaChiCuadrado {

    public PruebaChiCuadradoPoisson(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    public PruebaChiCuadradoPoisson(IGenerador generador, int cantidadIntervalos, int tamañoMuestra) {
        super(generador, cantidadIntervalos, tamañoMuestra);
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        double inicio = intervalo.getInicio();
        if (inicio < 0) {
            inicio = 0.0;
        }
        double fin = intervalo.getFin();
        if (fin == Double.POSITIVE_INFINITY) {
            fin = inicio + 100.0;
        }

        inicio = Math.ceil(inicio);  // Redondeo arriba.
        // El fin no requiere redondeo porque x avanza de a 1.
        double media = muestra.media();
        double sumatoriaProbabilidadPuntuales = 0.0;
        for (int x = (int) inicio; x < fin; x++) {
            double probabilidadPuntual = probabilidadPuntual(x, media);
            sumatoriaProbabilidadPuntuales += probabilidadPuntual;
        }

        return sumatoriaProbabilidadPuntuales;
    }

    private double probabilidadPuntual(int x, double lambda) {
        if (x <= 0) {
            return 0.0;
        }        // En poisson lamba == media.
        double probabilidad = ((Math.pow(lambda, x)) * Math.exp(-lambda)) / (factorial(x));
        if (probabilidad == Double.NaN) {
            probabilidad = 0.0;  // Muy cercano a 0.
        }
        return probabilidad;
    }

    private double factorial(int x) {
        assert x >= 0;  // No existe el factorial de numeros negativos (ni de fracciones).
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
