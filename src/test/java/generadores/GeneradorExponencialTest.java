/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pruebas.PruebaChiCuadradoExponencial;

/**
 *
 * @author juani
 */
public class GeneradorExponencialTest {
    
    int MUESTRAS = 10000;
    GeneradorExponencial instance;
    double MEDIA = 5.0;    
    
    public GeneradorExponencialTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new GeneradorExponencial(MEDIA);
    }

    @Test
    public void testMedia() {
        double TOLERANCIA = 0.05;

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
    public void pruebaChiCuadrado() {
        // La prueba ChiCuadrado mide si los valores se aproximan lo suficiente a 
        // la distribucion indicada (uniforme en este caso).
        PruebaChiCuadradoExponencial test = new PruebaChiCuadradoExponencial(instance, 10);
        assertTrue(test.runTest());
    }
    
}
