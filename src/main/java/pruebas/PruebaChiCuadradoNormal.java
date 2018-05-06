/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;
import java.math.BigDecimal;

/**
 *
 * @author eric
 */
public class PruebaChiCuadradoNormal extends PruebaChiCuadrado {

    public PruebaChiCuadradoNormal(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }
    
    @Override
    int cantidadValoresEmpiricos() {
        return 2;
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        return probabilidadIntervalo(intervalo.getInicio(), intervalo.getFin(), muestra.media(), muestra.desviacion());
    }
    
    private double probabilidadIntervalo(double inicio, double fin, double media, double desv) {        
        //calculo marca de clase   
        double x;
        if (inicio == Double.NEGATIVE_INFINITY) x = fin;
        else if (fin == Double.POSITIVE_INFINITY) x = inicio;
        else x = (inicio + fin) / 2;
        x = Math.abs(x);       

        //armo el numerador y denominador por separado
        double numerador = Math.exp((-0.5)*(Math.pow((x-media)/desv , 2)));
        double denominador = desv * Math.sqrt(2.0*Math.PI);
        //retorno la division, la distribucion normal es por tabla, se puede calcular
        //igual con marca de clase aunque es menos exacto        
        return numerador/denominador;
    }
    
    
}
