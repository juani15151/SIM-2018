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

    @Override
    protected List<Intervalo> generarIntervalos(Muestra muestra, int cantidadIntervalos) {
        List<Intervalo> intervalos = super.generarIntervalos(muestra, cantidadIntervalos);
        intervalos.set(0, new Intervalo(0.01, intervalos.get(2).getInicio()));
        return intervalos;
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        // Calculamos la probabilidad puntual y la aproximamos al intervalo.
        double marca = intervalo.marcaDeClase();
        if (marca < 0) {
            marca = intervalo.getFin() / 2.0;  // Para intervalos que empiezen antes del 0.
        }
        if (marca == Double.POSITIVE_INFINITY) {
            marca = intervalo.getInicio();
        }

        double p = probabilidadPuntual((int) marca, muestra.media());
        double recorrido = intervalo.recorrido();
        if (recorrido == Double.POSITIVE_INFINITY){
            return p;
        }
        return p * recorrido;
    }

    private double probabilidadPuntual(int x, double lambda) {
        if (x <= 0) {
            return 0.0;  // En realidad en 0 tiende a infinito...
        }        // En poisson lamba == media.
        return ((Math.pow(lambda, x)) * Math.exp(-lambda)) / (factorial(x));
    }

    private double factorial(int x) {
        assert x >= 0;  // No existe el factorial de numeros negativos (ni de fracciones).
        if (x == Double.POSITIVE_INFINITY) {
            return x;
        }
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
