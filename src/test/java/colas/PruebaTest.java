/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juani
 */
public class PruebaTest {
    
    public PruebaTest() {
    }

    public static void main(String[] args){
        new PruebaTest().testLinea();
    }
    
    /**
     * Test of linea method, of class Prueba.
     */
    @Test
    public void testLinea() {
        Prueba prueba = new Prueba();
        System.out.println(prueba.getEstado());
        for(int i = 0; i < 1000; i++){
            prueba.linea();
            System.out.println(prueba.getEstado());
        }
    }

}
