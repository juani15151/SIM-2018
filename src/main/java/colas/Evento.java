/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

/**
 *
 * @author eric
 */
public abstract class Evento {

    protected String nombre;
    protected Double proximaEjecucion;

    public Evento(String nombre) {
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
    }

    public Double proximaEjecucion() {
        return proximaEjecucion;
    }

    /**
     * Indica si el evento se ejecutará antes o al mismo tiempo que el evento
     * informado por parametro. Un evento con proximaEjecucion en null
     *
     * @param e el evento a comparar.
     * @return
     */
    public boolean before(Evento e) {
        if (proximaEjecucion() == null) {
            return e.proximaEjecucion() == null;
        } else {
            return e.proximaEjecucion() == null || this.proximaEjecucion() <= e.proximaEjecucion();
        }
    }
    
    /**
     * Ejecuta el evento.
     * Como reloj == proximaEjecucion, se pasa eso por parametro.
     */
    public void ejecutar(){
        ejecutar(proximaEjecucion());
    }
    
    /**
     * Ejecuta el evento.
     * Debe actualizar la proxima ejecución.
     */
    public abstract void ejecutar(double reloj);
}
