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

    //esta es una especie de main que hace las simulaciones, le falta para contemplar todos los casos
    public void linea() {
        //Pense en crear los generadores fuera del ciclo, pero dejo adentro para que entiendan mejor el codigo
        //GeneradorExponencial generadorExp = new GeneradorExponencial(0.5);
        //GeneradorUniformePersonalizado genFiambreria = new GeneradorUniformePersonalizado(1,3);
        //GeneradorUniformePersonalizado genCarniceria = new GeneradorUniformePersonalizado(0.5,2.5);

        for (int i = 0; i < numeroPruebas; i++) {
            //cuando el reloj esta en 0 no ejecutamos los fines de atencion para que no se atienda al cliente

            if (proximaLlegada == reloj) {
                llegaCliente();
            }
            if (finAtFiambreria == reloj && reloj != 0) //Evento fin atencion fiambreria
            {
                atenderFiambreria();
            }
            if (finAtCarniceria == reloj && reloj != 0) {
                atenderCarniceria();
            }
            //para el proximo evento ponemos el reloj en el menor de los 3            
            reloj = Math.min(proximaLlegada, finAtFiambreria);
            reloj = Math.min(reloj, finAtCarniceria);
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
