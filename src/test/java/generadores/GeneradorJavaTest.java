/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pruebas.PruebaChiCuadradoUniforme;

/**
 *
 * @author juani
 */
public class GeneradorJavaTest {
    GeneradorJava instance;
    
    public GeneradorJavaTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new GeneradorJava(100l);
    }

    
    @Test
    public void pruebaChiCuadrado() {
        // La prueba ChiCuadrado mide si los valores se aproximan lo suficiente a 
        // la distribucion indicada (uniforme en este caso).
        PruebaChiCuadradoUniforme test = new PruebaChiCuadradoUniforme(instance, 10);
        assertTrue(test.runTest());
    }
    
}
