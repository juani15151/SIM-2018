/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.generadoresPasajeros;
import generadores.IGenerador;
import generadores.GeneradorUniforme;
/**
 *
 * @author Camila
 */
public class Generador33Reservas implements IGenerador, IGeneradorPasajeros{
     private IGenerador generator = new GeneradorUniforme();
    private double rnd;

    @Override
    public double nextDouble() {
      return (double) nextCantidadPasajeros();
    }

    @Override
    public int nextCantidadPasajeros() {
        rnd = generator.nextDouble();
        
        if(rnd < 0.05){ // p = 0.05
            return 29;
        }
        if(rnd < 0.25){ // p = 0.2
            return 30;
        }
        if(rnd < 0.7){ // p = 0.45
            return 31;
        }
        if(rnd < 0.9){ // p = 0.2
            return 32;
        }
        else { // p = 0.1
            return 33;
        }
    }

    @Override
    public double lastRND() {
        return rnd ;
    }
}
