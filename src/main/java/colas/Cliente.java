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
    private String estado;
    private double horaInicioEspera;
    private String tipoCliente;

    public Cliente(double horaInicioEspera) {
        this.horaInicioEspera = horaInicioEspera;
        this.estado = "Esperando atención";
        definirTipo();
    }

    public String getEstado() {
        return estado;
    }

    public double getHoraInicioEspera() {
        return horaInicioEspera;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setHoraInicioEspera(double horaInicioEspera) {
        this.horaInicioEspera = horaInicioEspera;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
    
    
    private void definirTipo(){
        int rnd = (int) (Math.random() * 100);
        if (rnd <= 29) 
        {
            this.tipoCliente = "Verduleria" ;
        }
        else if (rnd <= 55)
        {
            this.tipoCliente = "Carniceria" ;
        }
        else 
        {
            this.tipoCliente = "Fiambreria";
        }
    }
    
    public boolean tieneVerdura(){
        int rnd = (int) (Math.random() * 100);
        if ("Verduleria".equals(this.tipoCliente)) return true;
        else if ("Carniceria".equals(this.tipoCliente)){
            return rnd <= 49;
        }
        else{
            return rnd <= 24;
        }      
    }
    
}
