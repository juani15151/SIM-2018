/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;
import generadores.IGenerador;
/**
 *
 * @author eric
 */
public class Evento {
    
    private String nombreEvento;
    private double tiempoInicio;
    private double tiempoDuracion;
    private double tiempoFin;
    public IGenerador generador;

    public Evento(String nombreEvento,double tiempoInicio ,IGenerador generador) {
        this.nombreEvento = nombreEvento;
        this.tiempoInicio = tiempoInicio;
        this.generador = generador;
        this.tiempoDuracion = generador.nextDouble();
        this.tiempoFin = tiempoInicio + tiempoDuracion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public double getTiempoInicio() {
        return tiempoInicio;
    }

    public double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public double getTiempoFin() {
        return tiempoFin;
    }

   
    
}
