/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author juani
 */
public class RoundTest {
    
    public RoundTest() {
    }

    /**
     * Test of round method, of class Round.
     */
    @Test
    public void testTruncate() {
        Assert.assertEquals("Error al truncar.", 0.5, Round.truncate(0.555, 1), 0.0);
        Assert.assertEquals("Error al truncar.", 0.55, Round.truncate(0.555, 2), 0.0);
        Assert.assertEquals("Error al truncar.", -0.5, Round.truncate(-0.555, 1), 0.0);
        Assert.assertEquals("Error al truncar.", -0.55, Round.truncate(-0.555, 2), 0.0);
    }
    
}
