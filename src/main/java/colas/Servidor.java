/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;
import java.util.LinkedList;
import generadores.IGenerador;
/**
 *
 * @author eric
 */
public class Servidor {
    private String nombre;
    private String estado;
    public LinkedList<Cliente> cola;
    private double acumTiempoEspera;
    private int cantidadClientesAtendidos;
    public IGenerador generador;


    
    
    public Servidor(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
        this.cola = new LinkedList<>();
        this.acumTiempoEspera = 0;
        this.cantidadClientesAtendidos = 0;
    }
    

    public String getEstado() {
        return estado;
    }

    public double getAcumTiempoEspera() {
        return acumTiempoEspera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCola(LinkedList<Cliente> cola) {
        this.cola = cola;
    }

    public void setAcumTiempoEspera(double acumTiempoEspera) {
        this.acumTiempoEspera = acumTiempoEspera;
    }

    public void setGenerador(IGenerador generador) {
        this.generador = generador;
    }
    
    public void setCantidadClientesAtendidos(int cantidad){
        this.cantidadClientesAtendidos = cantidad;
    }
    
    
    public void agregarCliente(Cliente cliente){
        cola.add(cliente);
    }
    
    public void acumularTiempo(double tiempo){
        this.acumTiempoEspera += tiempo;
    }
    

}
