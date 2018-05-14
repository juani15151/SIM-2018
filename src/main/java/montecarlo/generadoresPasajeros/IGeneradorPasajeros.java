/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.generadoresPasajeros;

/**
 *
 * @author juani
 */
public interface IGeneradorPasajeros {
    
    /**
     * Devuelve una cantidad de pasajeros al azar. 
     * Acorde a probabilidades definidas.
     * @return cantidad de pasajeros.
     */
    public int nextCantidadPasajeros();
    
    /**
     * Devuelve el ultimo valor RND utilizado por el generador.
     * Normalmente es el valor base usado en el metodo nextDouble();
     * @return 
     */
    public double lastRND();
}
