/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.List;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 *
 * @author juani
 */
public class Muestra extends AbstractList<Double> {
    
    private List<Double> valoresObservados = new ArrayList();
    
    private double maximoValor = Double.NEGATIVE_INFINITY;
    private double minimoValor = Double.POSITIVE_INFINITY;
    private double sumatoria;

    @Override
    public Double get(int index) {
        return valoresObservados.get(index);
    }

    @Override
    public int size() {
        return valoresObservados.size();
    }
    
    public double maximo(){
        return maximoValor;
    }
    
    public double minimo(){
        return minimoValor;        
    }
    
    public double recorrido(){
        return maximoValor - minimoValor;
    }
    
    public double media(){
        return sumatoria / size();
    }
    
    public double varianza(){
        double sumatoriaDesviaciones = 0.0;
        for (double num : valoresObservados) {
            sumatoriaDesviaciones += Math.pow(num - media(), 2);
        }
        return sumatoriaDesviaciones / size();
    }
    
    public double desviacion(){
        return Math.sqrt(varianza());
    }
    
    @Override
    public boolean add(Double valor){
        valoresObservados.add(valor);
        if(valor > maximoValor) maximoValor = valor;
        if(valor < minimoValor) minimoValor = valor;
        sumatoria += valor;
        return true;
    }
    
    public int[] frecuenciaPorIntervalo(List<Intervalo> intervalos){
        int[] frecuenciasObservadas = new int[intervalos.size()];
        
        for(double value : valoresObservados){
            for(int i = 0; i < intervalos.size(); i++){
                if(intervalos.get(i).contains(value)){
                    frecuenciasObservadas[i]++;
                    break;
                }
            }
        }
        
        return frecuenciasObservadas;
    }
    
}
