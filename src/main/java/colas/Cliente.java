/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eric
 */
public class Cliente {

    private static final List<Cliente> pool = new LinkedList<>();
    private Estado estado;
    private double horaInicioEspera;
    private double tiempoEspera;
    private TipoCliente tipoCliente;
    private boolean tieneVerdura;

    
    private void initialize(double horaInicioEspera){
        this.horaInicioEspera = horaInicioEspera;
        this.estado = Estado.ESPERANDO;
        definirTipo();        
    }
    
    /**
     * Mantenemos una lista de Clientes eliminados para no crear tantas instancias.
     * @param horaInicioEspera
     * @return 
     */
    public static Cliente getCliente(double horaInicioEspera){
        Cliente c;
        if(pool.size() > 0){
            c = pool.remove(0);            
            assert c.estado == Estado.FIN;
        } else {
            c = new Cliente();            
        }
        c.initialize(horaInicioEspera);
        return c;
    }

    private void definirTipo() {
        double rnd = (Math.random());
        if (rnd < 0.30) {
            tipoCliente = TipoCliente.VERDULERIA;
            tieneVerdura = true;

        } else if (rnd < 0.56) {
            tipoCliente = TipoCliente.CARNICERIA;
            tieneVerdura = Math.random() < 0.50;
        } else {
            tipoCliente = TipoCliente.FIAMBRERIA;
            tieneVerdura = Math.random() < 0.25;
        }
    }

    public Estado estado() {
        return estado;
    }

    public void inicioAtencion(double reloj) {
        assert estado == Estado.ESPERANDO;
        this.estado = Estado.ATENDIDO;
        tiempoEspera = reloj - horaInicioEspera;
    }
    
    public void finAtencion(){
        estado = Estado.FIN;
        pool.add(this);
    }

    public double tiempoEspera(){
        return tiempoEspera;
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
    
    @Override
    public String toString(){
        if(tieneVerdura()){
            return tipoCliente.toString().substring(0,1) + "V";
        } else {
            return tipoCliente.toString().substring(0,1) + "-";
        }
        
    }

    public enum TipoCliente {
        VERDULERIA,
        CARNICERIA,
        FIAMBRERIA;
    }

    public enum Estado {
        ESPERANDO,
        ATENDIDO,
        FIN;
    }
}
