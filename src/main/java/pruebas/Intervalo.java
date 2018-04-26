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
public class Intervalo implements Comparable {
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
    
    public Intervalo union(Intervalo intervalo){
        if(this.fin == intervalo.inicio){
            return new Intervalo(this.inicio, intervalo.fin);
        } else if (intervalo.fin == this.inicio){
            return new Intervalo(intervalo.inicio, this.fin);
        } else {
            throw new IllegalArgumentException("El intervalo debe ser contiguo");
        }
    }

    @Override
    public int compareTo(Object o) {
        Intervalo otro = (Intervalo) o;
        // Asumimos que en nuestro programa los intervalos nunca se solapan.
        if(this.getInicio() < otro.getInicio()){
            return -1;
        } else if(this.getInicio() > otro.getInicio()){
            return 1;
        } else {  // this.getInicio() == o.getInicio()
            // TODO: Si los inicios coinciden habria que comparar los finales.
            return 0;
        }              
    }
}
