/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

/**
 *
 * @author eric
 */
public class Cliente {

    private Estado estado;
    private double horaInicioEspera;
    private TipoCliente tipoCliente;
    private boolean tieneVerdura;

    public Cliente(double horaInicioEspera) {
        this.horaInicioEspera = horaInicioEspera;
        this.estado = Estado.ESPERANDO;
        definirTipo();
    }

    private void definirTipo() {
        double rnd = (Math.random());
        if (rnd <= 0.29) {
            tipoCliente = TipoCliente.VERDULERIA;
            tieneVerdura = true;

        } else if (rnd <= 0.55) {
            tipoCliente = TipoCliente.CARNICERIA;
            tieneVerdura = Math.random() <= 0.49;
        } else {
            tipoCliente = TipoCliente.CARNICERIA;
            tieneVerdura = Math.random() <= 0.24;
        }
    }

    public Estado estado() {
        return estado;
    }

    public void inicioAtencion() {
        assert estado == Estado.ESPERANDO;
        this.estado = Estado.ATENDIDO;
    }

    public double horaInicioEspera() {
        return horaInicioEspera;
    }

    public TipoCliente tipoCliente() {
        return tipoCliente;
    }

    public boolean tieneVerdura() {
        return tieneVerdura;
    }

    public boolean esParaVerduleria() {
        return tipoCliente == TipoCliente.VERDULERIA;
    }

    public boolean esParaCarniceria() {
        return tipoCliente == TipoCliente.CARNICERIA;
    }

    public boolean esParaFiambreria() {
        return tipoCliente == TipoCliente.FIAMBRERIA;
    }

    public enum TipoCliente {
        VERDULERIA,
        CARNICERIA,
        FIAMBRERIA;
    }

    public enum Estado {
        ESPERANDO,
        ATENDIDO;
    }
}
