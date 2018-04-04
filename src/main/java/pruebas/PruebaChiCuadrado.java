/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juani
 */
public abstract class PruebaChiCuadrado {

    protected int tamañoMuestra = 1000;

    protected IGenerador generador;
    protected final int cantidadIntervalos;

    public PruebaChiCuadrado(IGenerador generador, int cantidadIntervalos) {
        this.generador = generador;
        this.cantidadIntervalos = cantidadIntervalos;
    }

    public int getTamañoMuestra(){
        return this.tamañoMuestra;
    }
    
    public void setTamañoMuestra(int value){
        this.tamañoMuestra = value;
    }
    
    /**
     * Son la cantidad de valores definidos por el usuario (ej. media,
     * varianza).
     */
    abstract int getCantidadValoresEmpiricos();

    /**
     *
     * @param numeroIntervalo
     * @return la frecuencia esperada del intervalo indicado.
     */
    abstract double getFrecuenciaEsperada(int numeroIntervalo);

    /**
     * Cada vez que se invoca, genera una serie de numeros aleatorios y hace la
     * prueba de ChiCuadrado sobre esos valores.
     *
     * @return Si paso el test de ChiCuadrado o no.
     */
    public boolean runTest() {
        // calcular frecuencias de cada intervalo
        return this.runTest(this.observarFrecuenciasPorIntervalo());
    }
    
    public boolean runTest(int[] frecuenciaIntervalos){
        return this.calcularChi(frecuenciaIntervalos) <= this.chiAceptado();
    }

    public int[] observarFrecuenciasPorIntervalo() {
        // Usamos intervalos uniformes, pero no necesariamente deben serlo.
        double tamañoInter = 1.0 / this.cantidadIntervalos;
        int[] frecuenciaIntervalos = new int[this.cantidadIntervalos];

        for (double valorObservado : this.generarValores()) {
            for (int i = 0; i < this.cantidadIntervalos; i++) {
                if (valorObservado >= tamañoInter * i && valorObservado < tamañoInter * (i + 1)) {
                    frecuenciaIntervalos[i]++;
                }
            }
        }
        return frecuenciaIntervalos;
    }

    private List<Double> generarValores() {
        List<Double> valores = new ArrayList<>(this.tamañoMuestra);
        System.out.println(String.format("Generando %d valores aleatorios", tamañoMuestra));
        for (int i = 0; i < this.tamañoMuestra; i++) {
            double muestra = this.generador.nextDouble();
            valores.add(muestra);
            // Mostrar por consola (10 por linea).
            if(i % 10 == 0) {
                System.out.println(String.format("%1.4f, ", muestra));
            } else {
                System.out.print(String.format("%1.4f, ", muestra));
            }            
        }
        return valores;
    }

    public double calcularChi(int[] frecuenciaIntervalos) {
        double sumatoriaDesviacionesRelativas = 0.0;
        for (int i = 0; i < this.cantidadIntervalos; i++) {
            int frecObservada = frecuenciaIntervalos[i];
            double desviacionRelativa = Math.pow(frecObservada - this.getFrecuenciaEsperada(i), 2);
            desviacionRelativa /= this.getFrecuenciaEsperada(i);
            sumatoriaDesviacionesRelativas += desviacionRelativa;
        }
        return sumatoriaDesviacionesRelativas;
    }

    public double chiAceptado() {
        // Tabla tabulada de ChiCuadrado con nivel de confianza = 0.95
        double[] tablaChi95 = {3.8, 6, 7.8, 9.5, 11.1, 12.6, 14.1, 15.5, 16.9,
            18.3, 19.7, 21, 22.4, 23.7, 25, 26.3, 27.6, 28.9, 30.1, 31.4, 32.7, 33.9,
            35.2, 36.4, 37.7, 38.9, 41.1, 41.3, 42.6, 43.8};

        int gradosLibertad = this.cantidadIntervalos - 1 - this.getCantidadValoresEmpiricos();
        assert gradosLibertad <= tablaChi95.length; // Solo tabulamos hasta 30 grados libertad.
        return tablaChi95[gradosLibertad];
    }
}
