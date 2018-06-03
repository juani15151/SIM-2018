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
public class EventoLlegada extends Evento {
    
    private IGenerador generador;
    private Servidor servidorFiambreria;
    private Servidor servidorCarniceria;
    
    public EventoLlegada(String nombre, IGenerador generador, Servidor svFiambreria, Servidor svCarniceria) {
        super(nombre);
        this.generador = generador;
        proximaEjecucion = generador.nextDouble();
        this.servidorFiambreria = svFiambreria;
        this.servidorCarniceria = svCarniceria;
    }

    @Override
    public void ejecutar(double reloj) {                   
        // Instanciamos al cliente y lo enviamos a la cola o atencion correspondiente.
        Cliente nuevoCliente = new Cliente(reloj);
        meterClienteACola(nuevoCliente, reloj);     
        
        // Seteamos la proxima llegada.
        proximaEjecucion += generador.nextDouble();
    }
    
    /**
     * Ingresa al cliente a la cola correspondiente.
     * @param cliente 
     */
    private void meterClienteACola(Cliente cliente, double reloj) {
        if (cliente.esParaVerduleria()) {
            //si es de verdulera entra en la cola mas corta
            if (servidorFiambreria.cola.size() <= servidorCarniceria.cola.size()) {
                servidorFiambreria.agregarCliente(cliente, reloj);
            } else {
                servidorCarniceria.agregarCliente(cliente, reloj);
            }
        } else if (cliente.esParaFiambreria()) {
            servidorFiambreria.agregarCliente(cliente, reloj);
        } // si no es de verduleria ni fiambreria entonces es de carniceria
        else {
            servidorCarniceria.agregarCliente(cliente, reloj);
        }
    }
}
