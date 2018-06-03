/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eric
 */
public class Prueba {
    private double reloj;

    private IGenerador generadorProximaLlegada;
    private double proximaLlegada;
    private Double finAtFiambreria;
    private Double finAtCarniceria;
    private Servidor svFiambreria;
    private Servidor svCarniceria;
    private List<Evento> eventos = new LinkedList<Evento>();

    public Prueba() {
        this.reloj = 0;        
        this.svCarniceria = new Servidor("Carniceria", new GeneradorUniformePersonalizado(0.5, 2.5));        
        this.svFiambreria = new Servidor("Fiambreria", new GeneradorUniformePersonalizado(1, 3));        
        // Evento llegada.
        eventos.add(new EventoLlegada("Llegada Cliente", new GeneradorExponencial(0.5), svFiambreria, svCarniceria));
        // Eventos Fin atencion.
        eventos.add(new EventoFinAtencion("Fin At. Fiambreria", svFiambreria));
        eventos.add(new EventoFinAtencion("Fin At. Carniceria", svCarniceria));
    }
        
    /**
     * Elige el evento más cercano y lo ejecuta. 
     * Cada evento es responsable de actualizar su proxima ejecucion.
     */
    public void linea() {
        // Determinar el evento que se ejecuta antes.
        Evento menor = eventos.get(0);
        for (Evento e : eventos){
            if(e.before(menor)){
                menor = e;
            }
        }
        // Avanzar el reloj a ese evento y ejecutarlo.
        reloj = menor.proximaEjecucion();
        menor.ejecutar();                
    }
    
    /**
     * Retorna el estado actual de la simulacion (la linea en curso).
     * La idea es que la interfaz invoque a linea() y despues a getEstado() si lo
     * quiere mostrar.
     */
    public void getEstado(){
        // TODO.
    }    
}
