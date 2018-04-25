/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

/**
 *
 * @author juani
 */
public interface IGenerador {
    
    /**
     * Retorna un numero pseudo-aleatorio segun la distribucion del generador.
     */
    public abstract double nextDouble();
    
    
    /**
     * Retorna el valor de la media de la distribución, si se conoce.
     * Si no se puede determinar el valor de la media, lanza una excepcion.
     */
    public abstract double getMedia();  
    
}
