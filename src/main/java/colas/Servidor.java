/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import generadores.IGenerador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eric
 */
public class Servidor {

    private String nombre;
    private Estado estado;
    private Cliente clienteActual;
    public List<Cliente> cola;
    private double acumTiempoEspera;
    private int cantidadClientesAtendidos;
    public IGenerador generador;
    private Double finAtencion;

    // Para controlar interrupcion.
    private Double tiempoRestante; // Lo que faltaba para terminar de atender.

    public Servidor(String nombre, IGenerador generadorTiempoAtencion) {
        this.nombre = nombre;
        this.estado = Estado.LIBRE;
        this.cola = new ArrayList<>(5);
        this.acumTiempoEspera = 0;
        this.cantidadClientesAtendidos = 0;  // Cantidad de cliente que se empezaron a atender.
        this.generador = generadorTiempoAtencion;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getAcumTiempoEspera() {
        return acumTiempoEspera;
    }

    public int getCantidadClientesAtendidos() {
        return cantidadClientesAtendidos;
    }

    public Double getFinAtencion() {
        return finAtencion;
    }

    public void agregarCliente(Cliente cliente, double reloj) {
        cola.add(cliente);
        if (estado == Estado.LIBRE) {
            atenderProximo(reloj);
        }
    }

    /**
     * Empieza a atender al proximo cliente (si tiene) y retorna el proximo fin
     * de atencion.
     *
     * @param reloj
     * @return
     */
    public Double atenderProximo(double reloj) {
        // Liberar al actual.
        if (estado == Estado.OCUPADO) {
            clienteActual.finAtencion();
        }

        if (cola.isEmpty()) {
            assert estado == Estado.OCUPADO;  // Sino se invoco 2 veces.
            estado = Estado.LIBRE;
            clienteActual = null;
            finAtencion = null; // No empezo a atender a nadie, asique no hay fin de at.            
        } else {
            estado = Estado.OCUPADO;
            clienteActual = cola.remove(0);
            clienteActual.inicioAtencion(reloj);
            acumularTiempo(clienteActual.tiempoEspera()); // Acumula la espera.
            finAtencion = calcularProximoFinAtencion(clienteActual, reloj);
        }
        return finAtencion;
    }

    private Double calcularProximoFinAtencion(Cliente cliente, double reloj) {
        Double proximoFin = reloj;
        if (!cliente.esParaVerduleria()) { // Si es de Carniceria o fiambreria.
            proximoFin += generador.nextDouble();
        }
        if (cliente.tieneVerdura()) {
            // Incluye tanto a los cliente de solo verduleria y a los de carniceria + verduleria.
            proximoFin += 0.2;
        }

        return proximoFin;
    }

    private void acumularTiempo(double tiempo) {
        acumTiempoEspera += tiempo;
        cantidadClientesAtendidos++;
    }

    public void interrumpir(double reloj) {
        estado = Estado.INTERRUMPIDO;
        if (finAtencion != null) {
            tiempoRestante = finAtencion - reloj;
            finAtencion = null;
        }
        // Sino, el fin de atencion y el restante siguen en null.
    }

    public void reanudar(double reloj) {
        if (tiempoRestante != null) {
            estado = Estado.OCUPADO;
            finAtencion = reloj + tiempoRestante;
        } else {
            estado = Estado.LIBRE;
            // Por si durante la interrumcion entro un cliente a cola.
            if (!cola.isEmpty()) {
                atenderProximo(reloj);
            }
        }
    }

    public enum Estado {
        LIBRE,
        OCUPADO,
        INTERRUMPIDO;
    }

}
