/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;

/**
 *
 * @author eric
 */
public class PruebaChiCuadradoNormal extends PruebaChiCuadrado {
    double desv;

    public PruebaChiCuadradoNormal(IGenerador generador, int cantidadIntervalos, double desv) {
        super(generador, cantidadIntervalos);
        this.desv = desv;
    }

    
    @Override
    int cantidadValoresEmpiricos() {
        return 2;
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        return probabilidadIntervalo(intervalo.getInicio(), intervalo.getFin(), muestra.media(), desv);
    }
    
    private double probabilidadIntervalo(double inicio, double fin, double media, double desv) {
        //calculo marca de clase
        double x = (inicio + fin) / 2;
        //armo el numerador y denominador por separado
        double numerador = Math.exp((-1/2)*(Math.pow((x-media)/desv , 2)));
        double denominador = desv * Math.sqrt(2*Math.PI);
        //retorno la division, la distribucion normal es por tabla, se puede calcular
        //igual con marca de clase aunque es menos exacto
        return numerador/denominador;
    }
    
    
}
