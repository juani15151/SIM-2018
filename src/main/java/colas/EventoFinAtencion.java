/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.IGenerador;

/**
 *
 * @author juani
 */
public class EventoFinAtencion extends Evento {

    private Servidor servidor;
    
    public EventoFinAtencion(String nombre, Servidor servidor) {
        super(nombre);
        this.servidor = servidor;
    }

    @Override
    public void ejecutar(double reloj) {
        this.proximaEjecucion = servidor.atenderProximo(reloj);      
    }
    
}
