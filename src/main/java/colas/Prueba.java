/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.*;

/**
 *
 * @author eric
 */
public class Prueba {

    private int numeroPruebas;
    private double reloj;

    private IGenerador generadorProximaLlegada;
    private double proximaLlegada;
    private Double finAtFiambreria;
    private Double finAtCarniceria;
    private Servidor svFiambreria;
    private Servidor svCarniceria;

    public Prueba(int numeroPruebas) {
        this.numeroPruebas = numeroPruebas;
        this.reloj = 0;

        this.generadorProximaLlegada = new GeneradorExponencial(0.5);
        this.proximaLlegada = generadorProximaLlegada.nextDouble();      
        this.svCarniceria = new Servidor("Carniceria", new GeneradorUniformePersonalizado(0.5, 2.5));
        this.finAtCarniceria = null;
        this.svFiambreria = new Servidor("Fiambreria", new GeneradorUniformePersonalizado(1, 3));
        this.finAtFiambreria = null;
    }
    
    
    private double getProximaLlegada(){
        return proximaLlegada;
    }
    
    /**
     * El proximo fin atencion carniceria, o el máximo double si es null.
     * @return 
     */
    private double getFinAtencionCarniceria(){
        return finAtCarniceria == null ? Double.MAX_VALUE : finAtCarniceria;
    }
        
    /**
     * El proximo fin atencion fiambreria, o el máximo double si es null.
     * @return 
     */
    private double getFinAtencionFiambreria(){
        return finAtFiambreria == null ? Double.MAX_VALUE : finAtFiambreria;
    }
    

    /**
     * Para cada linea, elige el evento más cercano y lo ejecuta. 
     * Cada evento es responsable de actualizar su proxima ejecucion.
     */
    public void linea() {
        // Es una especie de MAIN que hace las simulaciones.
        for (int i = 0; i < numeroPruebas; i++) {
            // Avanzar reloj al menor evento:            
            if(getProximaLlegada() <= getFinAtencionFiambreria() 
                    && getProximaLlegada() <= getFinAtencionCarniceria()){
                // El menor es proximaLlegada.
                reloj = proximaLlegada;
                llegaCliente();
            } else if (getFinAtencionFiambreria() <= getFinAtencionCarniceria()){
                // El menor es finAtFiambreria.
                reloj = finAtFiambreria;
                atenderFiambreria();
            } else {
                // El menor es finAtCarniceria
                reloj = finAtCarniceria;
                atenderCarniceria();
            }            
        }
    }

    /**
     * Procesa el evento llegada de cliente.
     */
    private void llegaCliente() {
        {
            // Registramos el evento.
            Evento eventoLlegada = new Evento("Llegada Cliente", reloj, this.generadorProximaLlegada);
            
            // Seteamos la proxima llegada.
            this.proximaLlegada = eventoLlegada.getTiempoFin();
            
            // Instanciamos al cliente y lo enviamos a la cola o atencion correspondiente.
            Cliente nuevoCliente = new Cliente(reloj);
            meterClienteACola(nuevoCliente);            
        }
    }

    
    /**
     * Ingresa al cliente a la cola correspondiente.
     * @param cliente 
     */
    private void meterClienteACola(Cliente cliente) {
        if (cliente.esParaVerduleria()) {
            //si es de verdulera entra en la cola mas corta
            if (svFiambreria.cola.size() <= svCarniceria.cola.size()) {
                svFiambreria.agregarCliente(cliente, reloj);
            } else {
                svCarniceria.agregarCliente(cliente, reloj);
            }
        } else if (cliente.esParaFiambreria()) {
            svFiambreria.agregarCliente(cliente, reloj);
        } // si no es de verduleria ni fiambreria entonces es de carniceria
        else {
            svCarniceria.agregarCliente(cliente, reloj);
        }
    }
    
    private void atenderFiambreria() {
        this.finAtFiambreria = svFiambreria.atenderProximo(reloj);      
    }

    private void atenderCarniceria() {
        this.finAtCarniceria = svCarniceria.atenderProximo(reloj);        
    }
}
