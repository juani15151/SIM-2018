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
public class GeneradorUniformeMixtoTest {
    
    public GeneradorUniformeMixtoTest() {
    }

    /**
     * Test of nextDouble method, of class GeneradorUniformeMixto.
     */
    @Test
    public void testSeed() {
        GeneradorUniformeMixto instance1 = new GeneradorUniformeMixto(100);
        GeneradorUniformeMixto instance2 = new GeneradorUniformeMixto(100);
        
        for(int i = 0; i < 100; i++){
            assertEquals(instance1.nextDouble(), instance2.nextDouble(), 0.0);
        }
        
    }
    
}
