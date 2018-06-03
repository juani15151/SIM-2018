/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.*;
import java.util.LinkedList;

/**
 *
 * @author eric
 */
public class Prueba {

    private int numeroPruebas;
    private double reloj;

    private IGenerador generadorProximaLlegada;
    private double proximaLlegada;
    private IGenerador generadorFinAtencionFiambreria;
    private Double finAtFiambreria;
    private IGenerador generadorFinAtencionCarniceria;
    private Double finAtCarniceria;
    private Servidor svFiambreria;
    private Servidor svCarniceria;

    public Prueba(int numeroPruebas) {
        this.numeroPruebas = numeroPruebas;
        this.reloj = 0;

        this.generadorProximaLlegada = new GeneradorExponencial(0.5);
        this.proximaLlegada = generadorProximaLlegada.nextDouble();
        this.generadorFinAtencionFiambreria = new GeneradorUniformePersonalizado(1, 3);
        this.finAtFiambreria = null;
        this.generadorFinAtencionCarniceria = new GeneradorUniformePersonalizado(0.5, 2.5);
        this.finAtCarniceria = null;
        this.svCarniceria = new Servidor("Carniceria", "Libre");
        this.svFiambreria = new Servidor("Fiambreria", "Libre");
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

    private void llegaCliente() {
        {
            //Evento Llegada Cliente
            Evento eventoLlegada = new Evento("Llegada Cliente", reloj, this.generadorProximaLlegada);
            //El tiempo entre llegada del evento llegada cliente
            eventoLlegada.getTiempoDuracion();
            //El tiempo de la proxima llegada
            this.proximaLlegada = eventoLlegada.getTiempoFin();
            //Al principio de la simulacion siempre el evento es la proxima
            //llegada de cliente, asi que no hay un cliente antes
            if (reloj != 0) {
                //Creacion de el cliente que recien llega
                Cliente nuevoCliente = new Cliente(reloj);
                //Lo ingresamos a la cola que le corresponda
                meterClienteACola(nuevoCliente);
            }
        }
    }

    private void atenderFiambreria() {
        if (svFiambreria.cola.isEmpty()) {
            //se termino de atender y no hay mas clientes en la cola
            svFiambreria.setEstado("Libre");
        } else //se termino de atender y hay clientes en la cola
        {
            Evento eventoFinAtFiambreria = new Evento("Fin de atencion Fiambreria", reloj, this.generadorFinAtencionFiambreria);
            //tiempo de atencion
            eventoFinAtFiambreria.getTiempoDuracion();
            //cliente que se atiende
            Cliente clienteEnAtencion = svFiambreria.cola.removeFirst();
            clienteEnAtencion.setEstado("Siendo atendido");
            //actualizamos el acumulador de tiempo de espera para este servidor
            double tiempoEspera = reloj - clienteEnAtencion.getHoraInicioEspera();
            svFiambreria.acumularTiempo(tiempoEspera);
            //tiempo final
            finAtFiambreria = eventoFinAtFiambreria.getTiempoFin();
            if (clienteEnAtencion.tieneVerdura()) {
                finAtFiambreria += 0.2;
            }

        }

    }

    private void atenderCarniceria() {
        //Evento fin atencion Carniceria
        if (svCarniceria.cola.isEmpty()) {
            //se termino de atender y no hay mas clientes en la cola
            svCarniceria.setEstado("Libre");
        } else //se termino de atender y hay clientes en la cola
        {
            Evento eventoFinAtCarniceria = new Evento("Fin de atencion Carniceria", reloj, this.generadorFinAtencionCarniceria);
            //tiempo de atencion
            eventoFinAtCarniceria.getTiempoDuracion();
            //cliente que se atiende
            Cliente clienteEnAtencion = svCarniceria.cola.removeFirst();
            clienteEnAtencion.setEstado("Siendo atendido");
            //actualizamos el acumulador de tiempo de espera para este servidor
            double tiempoEspera = reloj - clienteEnAtencion.getHoraInicioEspera();
            svCarniceria.acumularTiempo(tiempoEspera);
            //tiempo final
            finAtCarniceria = eventoFinAtCarniceria.getTiempoFin();
            if (clienteEnAtencion.tieneVerdura()) {
                finAtCarniceria += 0.2;
            }
        }
    }

    private void meterClienteACola(Cliente cliente) {
        //primero vemos si es de verduleria
        if ("Verduleria".equals(cliente.getTipoCliente())) {
            //si es de verdulera entra en la cola mas corta
            if (svFiambreria.cola.size() <= svCarniceria.cola.size()) {
                svFiambreria.cola.add(cliente);
            } else {
                svCarniceria.cola.add(cliente);
            }
        } else if ("Fiambreria".equals(cliente.getTipoCliente())) {
            svFiambreria.cola.add(cliente);
        } // si no es de verduleria ni fiambreria entonces es de carniceria
        else {
            svCarniceria.cola.add(cliente);
        }
    }

}
