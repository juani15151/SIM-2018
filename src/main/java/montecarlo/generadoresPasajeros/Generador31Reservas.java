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
public class Generador31Reservas implements IGenerador, IGeneradorPasajeros{
    private IGenerador generator = new GeneradorUniforme();
    private double rnd;
    @Override
    public double nextDouble() {
         return (double) nextCantidadPasajeros();
    }

    @Override
    public int nextCantidadPasajeros() {
        rnd = generator.nextDouble();
        if(rnd < 0.10){ // p = 0.1
            return 28;
        }
        if(rnd < 0.35){ // p = 0.25
            return 29;
        }
        if(rnd < 0.85){ // p = 0.50
            return 30;
        }
        else { // p = 0.15
            return 31;
        }
    }

    @Override
    public double lastRND() {
        return rnd ;
    }
    
}
