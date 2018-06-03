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
    private Estado estado;
    public LinkedList<Cliente> cola;
    private double acumTiempoEspera;
    private int cantidadClientesAtendidos;
    public IGenerador generador;


    
    
    public Servidor(String nombre, IGenerador generadorTiempoAtencion) {
        this.nombre = nombre;
        this.estado = Estado.LIBRE;
        this.cola = new LinkedList<>();
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
            
    public void agregarCliente(Cliente cliente, double reloj){
        assert cliente.esParaCarniceria() || cliente.esParaVerduleria();
        cola.add(cliente);
        if (estado == Estado.LIBRE){
            atenderProximo(reloj);
        } 
    }
    
    /**
     * Empieza a atender al proximo cliente (si tiene) y retorna el proximo fin de atencion.
     * @param reloj
     * @return 
     */
    public Double atenderProximo(double reloj){
        if(cola.isEmpty()){
            assert estado == Estado.OCUPADO;  // Sino se invoco 2 veces.
            estado = Estado.LIBRE;
            return null; // No empezo a atender a nadie, asique no hay fin de at.
        } else {
           Cliente cliente = cola.remove();
           cliente.inicioAtencion(reloj);
           acumularTiempo(cliente.tiempoEspera());     
           return calcularProximoFinAtencion(cliente, reloj);
        }
    }
    
    private Double calcularProximoFinAtencion(Cliente cliente, double reloj){
        Double proximoFin = reloj;
        if(cliente.esParaCarniceria()){
            proximoFin += generador.nextDouble();
        }
        if(cliente.tieneVerdura()){
            // Incluye tanto a los cliente de solo verduleria y a los de carniceria + verduleria.
            proximoFin += 0.2;
        }
        return proximoFin;
    }
    
    private void acumularTiempo(double tiempo){
        acumTiempoEspera += tiempo;
        cantidadClientesAtendidos++;
    }
    
    public enum Estado {
        LIBRE,
        OCUPADO;
    }
    

}
