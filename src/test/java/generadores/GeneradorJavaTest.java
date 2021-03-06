/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import org.junit.Before;
import pruebas.PruebaChiCuadradoUniformeAB;

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
    @Ignore
    public void pruebaChiCuadrado() {
        // La prueba ChiCuadrado mide si los valores se aproximan lo suficiente a 
        // la distribucion indicada (uniforme en este caso).
        PruebaChiCuadradoUniformeAB test = new PruebaChiCuadradoUniformeAB(instance, 16);
        assertTrue("El generador Java no paso la prueba de chi uniforme.",test.runTest());
    }
    
}
