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
    private ArrayList<Intervalo> intervalos;
    protected double maxValue;
    protected double minValue;
    protected double media;

    public PruebaChiCuadrado(IGenerador generador, int cantidadIntervalos) {
        this.generador = generador;
        this.cantidadIntervalos = cantidadIntervalos;
    }

    /**
     * Retorna una lista con intervalos que incluyen todos los valores posibles.
     * La lista incluye intervalos que van desde - infinito a + infinito.
     *
     * @param cantidadIntervalos cantidad deseada de intervalos en el rango
     * definido.
     * @return
     */
    ArrayList<Intervalo> generarIntervalos(double minValue, double maxValue, int cantidadIntervalos) {
        intervalos = new ArrayList<>(cantidadIntervalos + 2);
        double tamañoIntervalo = maxValue - minValue;
        intervalos.add(new Intervalo(Double.NEGATIVE_INFINITY, minValue));
        for (int i = 1; i <= cantidadIntervalos; i++) {
            double limiteInferior = minValue * i;
            intervalos.add(new Intervalo(limiteInferior, limiteInferior + tamañoIntervalo));
        }        
        intervalos.add(new Intervalo(maxValue, Double.POSITIVE_INFINITY));
        
        this.unirIntervalosInvalidos(intervalos);
        return intervalos;
    }

    private void unirIntervalosInvalidos(ArrayList<Intervalo> intervalos) {      
        // Los cambios se aplican a la instancia intervalos, no hace falta retornarla.
        for (int i = 0; i < intervalos.size(); i++) {
            Intervalo intervalo = intervalos.get(i);
            while (this.getFrecuenciaEsperada(intervalo) <= 5) {
                if (i + 1 < intervalos.size()) {
                    intervalo = intervalo.union(intervalos.remove(i + 1));
                    intervalos.set(i, intervalo);
                } else if (i - 1 >= 0) {
                    intervalo = intervalo.union(intervalos.get(i - 1));
                    intervalos.set(i - 1, intervalo);
                    intervalos.remove(i);
                    i--;
                } else {
                    System.out.println("ERROR. Hay un intervalo con frecuencia esperada <= 5.");
                }
            }
        }
    }

    public int getTamañoMuestra() {
        return this.tamañoMuestra;
    }

    public void setTamañoMuestra(int value) {
        this.tamañoMuestra = value;
    }

    public int getCantidadIntervalos() {
        return cantidadIntervalos;
    }

    /**
     * Son la cantidad de valores definidos por el usuario (ej. media,
     * varianza).
     */
    abstract int getCantidadValoresEmpiricos();

    /**
     *
     * @param numeroIntervalo el numero de intervalo, a partir de 0 (cero).
     * @return la frecuencia esperada del intervalo indicado.
     */
    abstract double getFrecuenciaEsperada(Intervalo intervalo);

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

    public boolean runTest(int[] frecuenciaIntervalos) {
        return this.calcularChi(frecuenciaIntervalos) <= this.chiAceptado();
    }

    /**
     * Calcula las frecuencias observadas para cada intervalo. Cada posicion del
     * arreglo de frecuencias corresponde al arreglo en la misma posicion en el
     * arreglo de intervalos.
     *
     * @return
     */
    public int[] observarFrecuenciasPorIntervalo() {
        // Usamos intervalos uniformes, pero no necesariamente deben serlo.
        List<Double> valoresObservados = generarValores();
        double maximo = Double.NEGATIVE_INFINITY;
        double minimo = Double.POSITIVE_INFINITY;
        double suma = 0.0;
        for (double valorObservado : valoresObservados) {
            suma += valorObservado;
            if (valorObservado > maximo) {
                maximo = valorObservado;
            }
            if (valorObservado < minimo) {
                minimo = valorObservado;
            }
        }
        media = suma / valoresObservados.size();

        intervalos = this.generarIntervalos(minimo, maximo, cantidadIntervalos);
        int[] frecuenciaIntervalos = new int[intervalos.size()];

        for (double valorObservado : this.generarValores()) {
            for (int i = 0; i < this.getCantidadIntervalos(); i++) {
                if (intervalos.get(i).contains(valorObservado)) {
                    frecuenciaIntervalos[i]++;
                    break;
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
            if (i % 10 == 0) {
                System.out.println(String.format("%1.4f, ", muestra));
            } else {
                System.out.print(String.format("%1.4f, ", muestra));
            }
        }
        return valores;
    }

    public double calcularChi(int[] frecuenciaIntervalos) {
        double sumatoriaDesviacionesRelativas = 0.0;
        for (int i = 0; i < intervalos.size(); i++) {
            int frecObservada = frecuenciaIntervalos[i];
            double desviacionRelativa = Math.pow(frecObservada - this.getFrecuenciaEsperada(intervalos.get(i)), 2);
            desviacionRelativa /= this.getFrecuenciaEsperada(intervalos.get(i));
            sumatoriaDesviacionesRelativas += desviacionRelativa;
        }
        return sumatoriaDesviacionesRelativas;
    }

    public double chiAceptado() {
        // Tabla tabulada de ChiCuadrado con nivel de confianza = 0.95
        double[] tablaChi95 = {3.8, 6, 7.8, 9.5, 11.1, 12.6, 14.1, 15.5, 16.9,
            18.3, 19.7, 21, 22.4, 23.7, 25, 26.3, 27.6, 28.9, 30.1, 31.4, 32.7, 33.9,
            35.2, 36.4, 37.7, 38.9, 41.1, 41.3, 42.6, 43.8};

        int gradosLibertad = this.getCantidadIntervalos() - 1 - this.getCantidadValoresEmpiricos();
        assert gradosLibertad <= tablaChi95.length; // Solo tabulamos hasta 30 grados libertad.
        return tablaChi95[gradosLibertad];
    }
}
