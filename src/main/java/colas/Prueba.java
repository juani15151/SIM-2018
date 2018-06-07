/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
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
        this.svCarniceria = new Servidor("Carniceria", new GeneradorUniformePersonalizado(20, 30));        
        this.svFiambreria = new Servidor("Fiambreria", new GeneradorUniformePersonalizado(20, 30));        
        // Evento llegada.
        eventos.add(new EventoLlegada("Llegada Cliente", new GeneradorExponencial(100), getSvFiambreria(), getSvCarniceria()));
        // Eventos Fin atencion.
        eventos.add(new EventoFinAtencion("Fin At. Fiambreria", getSvFiambreria()));
        eventos.add(new EventoFinAtencion("Fin At. Carniceria", getSvCarniceria()));
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
    public String getEstado(){
        // Retornar como sea más conveniente para la interfaz.
        DecimalFormat f = new DecimalFormat("#0.0000");
        StringBuilder estado = new StringBuilder();
        estado.append("Reloj: ").append(f.format(this.getReloj()));
        estado.append(" - EVENTOS - Prox. Llegada: ").append(f.format(getEventos().get(0).proximaEjecucion()));
        estado.append(" - Prox. Fin At. (Fiambreria): ").append(getEventos().get(1).proximaEjecucion());
        estado.append(" - Prox. Fin At. (Carniceria): ").append(getEventos().get(2).proximaEjecucion());                
        estado.append("\n FIAMBRERIA -");
        estado.append("  Estado: ").append(this.getSvFiambreria().getEstado());
        estado.append("  Cola: ").append(this.getSvFiambreria().cola.size()).append("(");
        for(Cliente c : this.getSvFiambreria().cola) estado.append(c).append(", ");
        estado.append(")");
        estado.append(" - Tpo. Espera Acumulado : ").append(this.getSvFiambreria().getAcumTiempoEspera());
        estado.append(" - Clientes con espera terminada: ").append(this.getSvFiambreria().getCantidadClientesAtendidos());
        estado.append("\n CARNICERIA -");
        estado.append("  Estado: ").append(this.getSvCarniceria().getEstado());
        estado.append("  Cola: ").append(this.getSvCarniceria().cola.size()).append("(");
        for(Cliente c : this.getSvCarniceria().cola) estado.append(c).append(", ");
        estado.append(")");
        estado.append(" - Tpo. Espera Acumulado: ").append(this.getSvCarniceria().getAcumTiempoEspera());
        estado.append(" - Clientes con espera terminada: ").append(this.getSvCarniceria().getCantidadClientesAtendidos());
        return estado.toString();
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
