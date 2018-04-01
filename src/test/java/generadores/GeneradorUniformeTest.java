/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juani
 */
public class GeneradorUniformeTest {
    int MUESTRAS = 1000;
    
    public GeneradorUniformeTest() {
    }

    /**
     * Test of nextDouble method, of class GeneradorUniformeMixto.
     */
    @Test
    public void testSeed() {
        GeneradorUniforme instance1 = new GeneradorUniforme(100);
        GeneradorUniforme instance2 = new GeneradorUniforme(100);
        
        for(int i = 0; i < 100; i++){
            assertEquals(instance1.nextDouble(), instance2.nextDouble(), 0.0);
        }
        
    }
    
    @Test
    public void testMedia() {
        // La media de un generador lineal debe ser 0.5
        double TOLERANCIA = 0.05;
        
        GeneradorUniforme instance = new GeneradorUniforme(100);
        double suma = 0;
        for(int i = 0; i < this.MUESTRAS; i++){
            suma += instance.nextDouble();
        }
        double media = suma / this.MUESTRAS;
        assertTrue(media >= 0.5 * (1 - TOLERANCIA) && media <= 0.5 * (1 + TOLERANCIA));        
    }
    
    @Test
    public void testVarianza() {
        // La varianza de un generador lineal debe ser 1/12       
        double TOLERANCIA = 0.25;
                GeneradorUniforme instance = new GeneradorUniforme(100);
        double sumatoriaDesviaciones = 0;
        for(int i=0; i < this.MUESTRAS; i++){
            sumatoriaDesviaciones += Math.pow(instance.nextDouble() - 0.5, 2);
        }
        double varianza = sumatoriaDesviaciones / this.MUESTRAS;
        assertTrue(varianza >= 0.5 * ((double)1/12 - TOLERANCIA) && varianza <= 0.5 * ((double)1/12 + TOLERANCIA));        
    }
    
}
