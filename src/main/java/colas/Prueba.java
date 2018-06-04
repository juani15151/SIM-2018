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
    public String getEstado(){
        // Retornar como sea más conveniente para la interfaz.
        DecimalFormat f = new DecimalFormat("#0.0000");
        StringBuilder estado = new StringBuilder();
        estado.append("Reloj: ").append(f.format(this.reloj));
        estado.append(" EVENTOS - Prox. Llegada: ").append(
                f.format(eventos.get(0).proximaEjecucion()));
        estado.append(" Prox. Fin At. (Fiambreria): ").append(eventos.get(1).proximaEjecucion());
        estado.append(" Prox. Fin At. (Carniceria): ").append(eventos.get(2).proximaEjecucion());                
        estado.append("\n FIAMBRERIA -");
        estado.append(" Estado: ").append(this.svFiambreria.getEstado());
        estado.append(" Cola: ").append(this.svFiambreria.cola.size()).append("(");
        for(Cliente c : this.svFiambreria.cola) estado.append(c).append(", ");
        estado.append(")");
        estado.append(" T. Espera Ac. : ").append(this.svFiambreria.getAcumTiempoEspera());
        estado.append(" Clientes ini.: ").append(this.svFiambreria.getCantidadClientesAtendidos());
        estado.append("\n CARNICERIA -");
        estado.append(" Estado: ").append(this.svCarniceria.getEstado());
        estado.append(" Cola: ").append(this.svCarniceria.cola.size()).append("(");
        for(Cliente c : this.svCarniceria.cola) estado.append(c).append(", ");
        estado.append(")");
        estado.append(" T. Espera Ac.: ").append(this.svCarniceria.getAcumTiempoEspera());
        estado.append(" Clientes ini.: ").append(this.svCarniceria.getCantidadClientesAtendidos());
        return estado.toString();
    }        
}
