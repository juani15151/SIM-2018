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
    
    private static final double TOLERANCIA = 0.000000000001;

    private double inicio; // incluido
    private double fin; // no incluido

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

    /**
     * El tamaño del intervalo.
     * @return 
     */
    public double recorrido() {
        return fin - inicio;
    }

    /**
     * Retorna la marca de clase (el valor al medio del intervalo).
     * @return
     */
    public double marcaDeClase() {
        return (inicio + fin) / 2.0;
    }

    public boolean contains(double value) {
        return value >= inicio - TOLERANCIA && value < fin;
    }

    /**
     * Une este intervalo con uno contiguo.
     * @param intervalo 
     */
    public void join(Intervalo intervalo) {        
        assert this != intervalo; // No unir intervalos a si mismos.       
        
        // No se compara por == porque los double difieren en los ultimos decimales.
        if (Math.abs(this.fin - intervalo.inicio) <= TOLERANCIA) {
            this.fin = intervalo.fin;
        } else if (Math.abs(intervalo.fin - this.inicio) <= TOLERANCIA) {
            this.inicio = intervalo.inicio;
        } else {
            throw new IllegalArgumentException("El intervalo debe ser contiguo");
        }
    }

    @Override
    public int compareTo(Object o) {
        Intervalo otro = (Intervalo) o;
        // Asumimos que en nuestro programa los intervalos nunca se solapan.
        if (this.getInicio() < otro.getInicio()) {
            return -1;
        } else if (this.getInicio() > otro.getInicio()) {
            return 1;
        } else {  // this.getInicio() == o.getInicio()
            // TODO: Si los inicios coinciden habria que comparar los finales.
            return 0;
        }
    }
    
    public String toString(){
        return String.format("Intervalo [%s - %s)", inicio, fin);
    }
}
