/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import java.util.Random;

/**
 * Es el generador (Random) de Java, adaptado a la interfaz IGenerador.
 * 
 * @author juani
 */
public class GeneradorJava extends Random implements IGenerador {    

    public GeneradorJava() {
    }

    public GeneradorJava(long seed) {
        super(seed);
    }

    @Override
    public double getMedia() {
        return 0.5;  // Se supone que es un generador uniforme 0-1.
    }
        
}
