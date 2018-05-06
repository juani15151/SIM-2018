/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author juani
 */
public abstract class PruebaChiCuadrado {

    protected final IGenerador generador;
    protected final int cantidadIntervalos;
    protected final int tamañoMuestra;

    public PruebaChiCuadrado(IGenerador generador, int cantidadIntervalos) {
        this(generador, cantidadIntervalos, 10000);
    }

    public PruebaChiCuadrado(IGenerador generador, int cantidadIntervalos, int tamañoMuestra) {
        this.generador = generador;
        this.cantidadIntervalos = cantidadIntervalos;
        this.tamañoMuestra = tamañoMuestra;
    }

    /**
     * Cada vez que se invoca, genera una serie de numeros aleatorios y hace la
     * prueba de ChiCuadrado sobre esos valores.
     *
     * @return Si paso el test de ChiCuadrado o no.
     */
    public boolean runTest() {
        // Tomar muestra
        Muestra muestra = generarMuestra(this.tamañoMuestra);
        return runTest(muestra);
    }

    public boolean runTest(Muestra muestra) {
        // Generar intervalos
        List<Intervalo> intervalos = generarIntervalos(muestra, this.cantidadIntervalos);
        // Calcular frecuenciasObservadas
        int[] frecuenciasObservadas = muestra.frecuenciaPorIntervalo(intervalos);
        // Calcular chi
        double chiObservado = calcularChi(intervalos, muestra, frecuenciasObservadas);
        double chiMaximoAceptable = chiAceptado(intervalos.size(), this.cantidadValoresEmpiricos());
        // Comparar
        return chiObservado <= chiMaximoAceptable;
    }

    private Muestra generarMuestra(int tamaño) {
        Muestra muestra = new Muestra();
        System.out.println(String.format("Generando %d valores aleatorios", tamaño));

        for (int i = 0; i < tamaño; i++) {
            double valor = this.generador.nextDouble();
            muestra.add(valor);

            // Mostrar por consola (10 por linea).
            if (i % 10 == 0) {
                System.out.println(String.format("%1.4f, ", valor));
            } else {
                System.out.print(String.format("%1.4f, ", valor));
            }

        }
        return muestra;
    }

    /**
     * Retorna una lista con intervalos que incluyen todos los valores posibles.
     * La lista incluye intervalos que van desde - infinito a + infinito.
     *
     * @param cantidadIntervalos cantidad deseada de intervalos en el rango
     * definido.
     * @return
     */
    private List<Intervalo> generarIntervalos(Muestra muestra, int cantidadIntervalos) {
        // Creamos los intervalos pedidos y agregamos 2 intervalos al inicio y final.
        List<Intervalo> intervalos = new ArrayList<>(cantidadIntervalos + 2);

        // Agregamos intervalo desde el infinito negativo a la muestra.
        intervalos.add(new Intervalo(Double.NEGATIVE_INFINITY, muestra.minimo()));

        // Definimos intervalos iguales.
        double tamañoIntervalo = muestra.recorrido() / cantidadIntervalos;
        double limiteInferior = muestra.minimo();

        for (int i = 1; i <= cantidadIntervalos; i++) {
            double limiteSuperior = limiteInferior + tamañoIntervalo;
            intervalos.add(new Intervalo(limiteInferior, limiteSuperior));

            limiteInferior = limiteSuperior;
        }

        // Agregamos intervalos hasta el infinito positivo.  
        intervalos.add(new Intervalo(muestra.maximo(), Double.POSITIVE_INFINITY));

        // Valida y une los intervalos pequeños.
        return unificarIntervalosPequeños(intervalos, muestra);
    }

    private List<Intervalo> unificarIntervalosPequeños(List<Intervalo> intervalos, Muestra muestra) {        
        Collections.sort(intervalos); // Hay que ordenarlos para que al unir sean contiguos.
        List<Intervalo> intervalosValidos = new ArrayList<>(intervalos.size());
        Intervalo paraUnir = null;
        for (Intervalo i : intervalos) {
            if (paraUnir != null) {
                // Si quedo un intervalo previo para unir.
                i.join(paraUnir);
                paraUnir = null;
            }

            if (this.frecuenciaEsperada(i, muestra) > 5) {
                // Es Valido
                intervalosValidos.add(i);
            } else if (intervalosValidos.size() > 0) {
                // Si hay anterior, lo unimos.
                Intervalo ultimoValido = intervalosValidos.get(intervalosValidos.size() - 1);
                ultimoValido.join(i);
            } else {
                // Sino, lo guardamos para agruparlo con el que sigue
                paraUnir = i;
            }
        }
        assert paraUnir == null;
        return intervalosValidos;
    }

    private double calcularChi(List<Intervalo> intervalos, Muestra muestra, int[] frecuenciasObservadas) {
        double sumatoriaDesviacionesRelativas = 0.0;
        for (int i = 0; i < intervalos.size(); i++) {
            // Seleccionar 1 intervalo
            Intervalo intervalo = intervalos.get(i);
            int frecuenciaObservada = frecuenciasObservadas[i];
            // Calcular desviacion relativa
            double frecuenciaEsperada = frecuenciaEsperada(intervalo, muestra);
            double numerador = Math.pow(frecuenciaObservada - frecuenciaEsperada, 2);
            double desviacionRelativa = numerador / frecuenciaEsperada;
            System.out.println(String.format("%s; %s esperados; %s observados ", intervalo, frecuenciaEsperada, frecuenciaObservada));
            sumatoriaDesviacionesRelativas += desviacionRelativa;
        }
        return sumatoriaDesviacionesRelativas;
    }

    public double chiAceptado(int cantidadIntervalos, int valoresEmpiricos) {
        int gradosLibertad = cantidadIntervalos - 1 - valoresEmpiricos;
        return getChi95(gradosLibertad);
    }

    /**
     * Retorna el valor de ChiCuadrado con un valor de confianza de 0,95.
     *
     *
     * @param gradosLibertad
     * @return
     */
    private double getChi95(int gradosLibertad) {
        double[] tablaChi95 = {3.8, 6, 7.8, 9.5, 11.1, 12.6, 14.1, 15.5, 16.9,
            18.3, 19.7, 21, 22.4, 23.7, 25, 26.3, 27.6, 28.9, 30.1, 31.4, 32.7, 33.9,
            35.2, 36.4, 37.7, 38.9, 41.1, 41.3, 42.6, 43.8};

        assert gradosLibertad >= 0;
        assert gradosLibertad <= tablaChi95.length; // Solo tabulamos hasta 30 grados libertad.
        return tablaChi95[gradosLibertad];
    }

    /**
     * Son la cantidad de valores definidos por el usuario (ej. media,
     * varianza).
     */
    abstract int cantidadValoresEmpiricos();

    /**
     *
     * @param intervalo
     * @return la frecuencia esperada del intervalo indicado.
     */
    private double frecuenciaEsperada(Intervalo intervalo, Muestra muestra) {
        return probabilidad(intervalo, muestra) * tamañoMuestra;
    }

    /**
     * Probabilidad del intervalo en la muestra.
     * En algunas distribuciones se pueden tomar parametros de la muestra, como
     * media, maximo y/o minimo.
     * @param Intervalo
     * @return la probabilidad de que un valor pertenezca al intervalo.
     */
    abstract double probabilidad(Intervalo intervalo, Muestra muestra);

}
