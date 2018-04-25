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
public class PruebaChiCuadradoPoisson extends PruebaChiCuadrado{
    double x;
    double lambda;
    public PruebaChiCuadradoPoisson(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    int getCantidadValoresEmpiricos() {
        return 1;
    }

    @Override
    double getFrecuenciaEsperada(int numeroIntervalo) {
        double tamañoIntervalo = this.tamañoMuestra / this.cantidadIntervalos;
        double inicioIntervalo = tamañoIntervalo * numeroIntervalo;
        double finIntervalo = inicioIntervalo + tamañoIntervalo;
        double marcaClase = (inicioIntervalo + finIntervalo) / 2 ;
        return frecuenciaIntervalo(x,lambda)/marcaClase;
    }
    
    private double frecuenciaIntervalo(double x, double lambda ){
        return ((Math.pow(lambda,x)) * Math.exp(-lambda))/(this.factorial(x)) ;
    }
    private double factorial(double x){
        double fact = 1;
        for (int i = 0; i <= x; i++) {
            fact *= i;
            
        }
        return x;
    }
}
