/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author juani
 */
public class Intervalo {
    private final Double inicio; // incluido
    private final Double fin; // no incluido

    public Intervalo(double inicio, double fin) {        
        this.inicio = inicio;
        this.fin = fin;
    }

    public double getInicio() {
        return inicio;
    }

    public double getFin() {
        return fin;
    }
    
    public double getSize() {
        return fin - inicio;
    }
    
    /**
     * Retorna la marca de clase (el valor al medio del intervalo).
     * @return 
     */
    public double getMark(){
        return (inicio + fin) / 2.0;
    }
    
    public boolean contains(double value){
        return value >= inicio && value < fin;
    }
}
