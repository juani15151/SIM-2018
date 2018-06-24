/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import java.util.List;

/**
 * @author juani
 */
public class VectorEstado {

    private Double reloj;
    // Eventos
    private Double proximaLlegada;
    private Double carniceriaFinAtencion;
    private Double fiambreriaFinAtencion;
    private Double proximaInterrupcion;
    // Servidores
    private String carniceriaEstado;
    private int carniceriaTamañoCola;
    private Double carniceriaTiempoEsperaAcumulado;
    private int carniceriaClientesIniciados;
    private String fiambreriaEstado;
    private int fiambreriaTamañoCola;
    private Double fiambreriaTiempoEsperaAcumulado;
    private int fiambreriaClientesIniciados;
    // Colas (Primero, Segundo y Ultimo).
    private String carniceriaEstadoCliente1;
    private Double carniceriaHoraCliente1;
    private String carniceriaEstadoCliente2;
    private Double carniceriaHoraCliente2;
    private String carniceriaEstadoClienteN;
    private Double carniceriaHoraClienteN;
    private String fiambreriaEstadoCliente1;
    private Double fiambreriaHoraCliente1;
    private String fiambreriaEstadoCliente2;
    private Double fiambreriaHoraCliente2;
    private String fiambreriaEstadoClienteN;
    private Double fiambreriaHoraClienteN;

    public VectorEstado(Double reloj, Evento llegada, Evento carniceria, Evento fiambreria, Evento interrupcion,
                        Servidor carniceriaServ, Servidor fiambreriaServ) {
        this.reloj = reloj;
        // Eventos
        this.proximaLlegada = llegada.proximaEjecucion();
        this.carniceriaFinAtencion = carniceria.proximaEjecucion();
        this.fiambreriaFinAtencion = fiambreria.proximaEjecucion();
        this.proximaInterrupcion = interrupcion.proximaEjecucion();
        // Carniceria
        carniceriaEstado = carniceriaServ.getEstado().toString();
        carniceriaTamañoCola = carniceriaServ.cola.size();
        carniceriaTiempoEsperaAcumulado = carniceriaServ.getAcumTiempoEspera();
        carniceriaClientesIniciados = carniceriaServ.getCantidadClientesAtendidos();
        // Cola Carniceria
        List<Cliente> cola = carniceriaServ.cola;
        switch (cola.size()) {
            default:
            case 2:
                carniceriaEstadoClienteN = cola.get(cola.size() - 1).estado().toString();
                carniceriaHoraClienteN = cola.get(cola.size() - 1).horaInicioEspera();
            case 1:
                carniceriaEstadoCliente2 = cola.get(0).estado().toString();
                carniceriaHoraCliente2 = cola.get(0).horaInicioEspera();
            case 0:
                if (carniceriaServ.getClienteActual() != null) {
                    carniceriaEstadoCliente1 = carniceriaServ.getClienteActual().estado().toString();
                    carniceriaHoraCliente1 = carniceriaServ.getClienteActual().horaInicioEspera();
                }
        }
        // Fiambreria        
        fiambreriaEstado = fiambreriaServ.getEstado().toString();
        fiambreriaTamañoCola = fiambreriaServ.cola.size();
        fiambreriaTiempoEsperaAcumulado = fiambreriaServ.getAcumTiempoEspera();
        fiambreriaClientesIniciados = fiambreriaServ.getCantidadClientesAtendidos();
        cola = fiambreriaServ.cola;
        switch (cola.size()) {
            default:
            case 2:
                fiambreriaEstadoClienteN = cola.get(cola.size() - 1).estado().toString();
                fiambreriaHoraClienteN = cola.get(cola.size() - 1).horaInicioEspera();
            case 1:
                fiambreriaEstadoCliente2 = cola.get(0).estado().toString();
                fiambreriaHoraCliente2 = cola.get(0).horaInicioEspera();
            case 0:
                if (fiambreriaServ.getClienteActual() != null) {
                fiambreriaEstadoCliente1 = fiambreriaServ.getClienteActual().estado().toString();
                fiambreriaHoraCliente1 = fiambreriaServ.getClienteActual().horaInicioEspera();
                }
        }

    }

    public Double getReloj() {
        return reloj;
    }

    public Double getProximaLlegada() {
        return proximaLlegada;
    }

    public Double getCarniceriaFinAtencion() {
        return carniceriaFinAtencion;
    }

    public Double getFiambreriaFinAtencion() {
        return fiambreriaFinAtencion;
    }

    public String getCarniceriaEstado() {
        return carniceriaEstado;
    }

    public int getCarniceriaTamañoCola() {
        return carniceriaTamañoCola;
    }

    public Double getCarniceriaTiempoEsperaAcumulado() {
        return carniceriaTiempoEsperaAcumulado;
    }

    public int getCarniceriaClientesIniciados() {
        return carniceriaClientesIniciados;
    }

    public String getFiambreriaEstado() {
        return fiambreriaEstado;
    }

    public int getFiambreriaTamañoCola() {
        return fiambreriaTamañoCola;
    }

    public Double getFiambreriaTiempoEsperaAcumulado() {
        return fiambreriaTiempoEsperaAcumulado;
    }

    public int getFiambreriaClientesIniciados() {
        return fiambreriaClientesIniciados;
    }

    public String getCarniceriaEstadoCliente1() {
        return carniceriaEstadoCliente1;
    }

    public Double getCarniceriaHoraCliente1() {
        return carniceriaHoraCliente1;
    }

    public String getCarniceriaEstadoCliente2() {
        return carniceriaEstadoCliente2;
    }

    public Double getCarniceriaHoraCliente2() {
        return carniceriaHoraCliente2;
    }

    public String getCarniceriaEstadoClienteN() {
        return carniceriaEstadoClienteN;
    }

    public Double getCarniceriaHoraClienteN() {
        return carniceriaHoraClienteN;
    }

    public String getFiambreriaEstadoCliente1() {
        return fiambreriaEstadoCliente1;
    }

    public Double getFiambreriaHoraCliente1() {
        return fiambreriaHoraCliente1;
    }

    public String getFiambreriaEstadoCliente2() {
        return fiambreriaEstadoCliente2;
    }

    public Double getFiambreriaHoraCliente2() {
        return fiambreriaHoraCliente2;
    }

    public String getFiambreriaEstadoClienteN() {
        return fiambreriaEstadoClienteN;
    }

    public Double getFiambreriaHoraClienteN() {
        return fiambreriaHoraClienteN;
    }

    public Double getProximaInterrupcion() {
        return proximaInterrupcion;
    }

}
