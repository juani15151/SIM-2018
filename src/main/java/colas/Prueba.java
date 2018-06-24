/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.GeneradorExponencial;
import generadores.GeneradorUniformePersonalizado;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eric
 */
public class Prueba {
    private double reloj;
    private Servidor svFiambreria;
    private Servidor svCarniceria;
    private List<Evento> eventos = new ArrayList<>();
    private double promedioCarniceria;
    private double promedioFiambreria;

    public Prueba() {
        this.reloj = 0;        
        this.svCarniceria = new Servidor("Carniceria", new GeneradorUniformePersonalizado(0.5, 2.5));        
        this.svFiambreria = new Servidor("Fiambreria", new GeneradorUniformePersonalizado(1, 3));        
        // Evento llegada.
        eventos.add(new EventoLlegada("Llegada Cliente", new GeneradorExponencial(0.5), getSvFiambreria(), getSvCarniceria()));
        // Eventos Fin atencion.
        eventos.add(new EventoFinAtencion("Fin At. Carniceria", getSvCarniceria()));
        eventos.add(new EventoFinAtencion("Fin At. Fiambreria", getSvFiambreria()));
        // Evento Interrupcion
        eventos.add(new EventoInterrupcion("Interrupcion", getSvCarniceria()));
    }
        
    /**
     * Elige el evento más cercano y lo ejecuta. 
     * Cada evento es responsable de actualizar su proxima ejecucion.
     */   
    public void linea() {
        // Determinar el evento que se ejecuta antes.
        Evento menor = getEventos().get(0);
        for (Evento e : getEventos()){
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
    public VectorEstado getEstado(){
        return new VectorEstado( 
                this.getReloj(),
                getEventos().get(0), // Llegada
                getEventos().get(1), // Carniceria
                getEventos().get(2), // Fiambreria
                getEventos().get(3), // Interrupcion
                this.getSvCarniceria(),
                this.getSvFiambreria()
        );        
    }    

    /**
     * @return the reloj
     */
    public double getReloj() {
        return reloj;
    }

    /**
     * @return the svFiambreria
     */
    public Servidor getSvFiambreria() {
        return svFiambreria;
    }

    /**
     * @return the svCarniceria
     */
    public Servidor getSvCarniceria() {
        return svCarniceria;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * @return the promedioCarniceria
     */
    public double getPromedioCarniceria() {
        if(svCarniceria.getCantidadClientesAtendidos() == 0){
            promedioCarniceria=0;
        }
        else{
        promedioCarniceria= svCarniceria.getAcumTiempoEspera()/svCarniceria.getCantidadClientesAtendidos();
        }
        return promedioCarniceria;
    }

    /**
     * @return the promedioFiambreria
     */
    public double getPromedioFiambreria() {
        if(svFiambreria.getCantidadClientesAtendidos() == 0){
            promedioFiambreria =0;
        }
        else{
        promedioFiambreria = svFiambreria.getAcumTiempoEspera() / svFiambreria.getCantidadClientesAtendidos();
        }
        return promedioFiambreria;
    }

    
}
