/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pruebas.PruebaChiCuadradoNormal;

/**
 *
 * @author juani
 */
public class GeneradorNormalConvolucionTest {
    
    int MUESTRAS = 10000;
    GeneradorNormalConvolucion instance;
    int MEDIA = 0;
    int DESVIACION = 1;
    
    public GeneradorNormalConvolucionTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new GeneradorNormalConvolucion(DESVIACION, MEDIA);
    }
    
    private double getVarianza(){
        return Math.pow((double) DESVIACION, 2);
    }

    @Test
    public void testMedia() {
        double TOLERANCIA = 0.05 * DESVIACION;

        double suma = 0;
        for (int i = 0; i < this.MUESTRAS; i++) {
            suma += this.instance.nextDouble();
        }
        double media = suma / this.MUESTRAS;
        if (MEDIA == 0){
            assertTrue(media >= -TOLERANCIA && media <= TOLERANCIA);
        } else {
            assertTrue(media >= MEDIA * (1 - TOLERANCIA) && media <= MEDIA * (1 + TOLERANCIA));
        }
        
    }
    
    @Test
    public void testVarianza() {
        double TOLERANCIA = 0.25;
        double sumatoriaDesviaciones = 0;
        for (int i = 0; i < this.MUESTRAS; i++) {
            sumatoriaDesviaciones += Math.pow(this.instance.nextDouble() - MEDIA, 2);
        }
        double varianza = sumatoriaDesviaciones / this.MUESTRAS;
        System.out.println(String.format("Varianza Observada: %s", varianza));
        assertTrue(varianza >= getVarianza() * (1 - TOLERANCIA) && varianza <= getVarianza() * (1 + TOLERANCIA));
    }

    @Test
    public void pruebaChiCuadrado() {
        // La prueba ChiCuadrado mide si los valores se aproximan lo suficiente a 
        // la distribucion indicada (uniforme en este caso).
        PruebaChiCuadradoNormal test = new PruebaChiCuadradoNormal(instance, 10);
        assertTrue(test.runTest());
    }
    
}
