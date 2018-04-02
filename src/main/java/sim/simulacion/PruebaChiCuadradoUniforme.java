package sim.simulacion;

import generadores.GeneradorUniforme;
import java.util.ArrayList;
import java.util.List;


public class PruebaChiCuadradoUniforme {
    private final int TAMAÑOMUESTRA = 1000;
    
    //private double serie[];
    private List<Double> serie;
    private final int[] frecuenciaIntervalos; // Cada item almacena la frecuencia del intervalo.
    private double frecEsperada;

    public PruebaChiCuadradoUniforme(ArrayList<Double> serie, int cantidadIntervalos) {
        this.serie = serie;
        this.frecuenciaIntervalos = new int[cantidadIntervalos]; 
        this.frecEsperada = (double) serie.size() / cantidadIntervalos;
    }   
    
    public PruebaChiCuadradoUniforme(GeneradorUniforme generador, int cantidadIntervalos){
        this.serie = new ArrayList<>(this.TAMAÑOMUESTRA);
        for(int i=0; i < this.TAMAÑOMUESTRA; i++){
            this.serie.add(generador.nextDouble());
        }
        this.frecuenciaIntervalos = new int[cantidadIntervalos];
        this.frecEsperada = (double) serie.size() / cantidadIntervalos;
    }
        
    /**
     * @return Si paso el test de ChiCuadrado o no.
     */
    public boolean runTest(){
        // calcular frecuencias de cada intervalo
        this.observarFrecuenciasPorIntervalo();
        return this.calcularChi() <= this.chiAceptado();
    }    
    
    private int cantIntervalos(){
        return this.frecuenciaIntervalos.length;
    }
    
    private void observarFrecuenciasPorIntervalo() {
        // Usamos intervalos uniformes, pero no necesariamente deben serlo.
        double tamañoInter = (double) 1.0 / this.cantIntervalos();

        for(double valorObservado : this.serie){ 
            for(int i = 0; i < this.cantIntervalos(); i++){
                if(valorObservado > tamañoInter * i && valorObservado <= tamañoInter * (i + 1)){
                    this.frecuenciaIntervalos[i]++;
                }
            }
        }        
    }  

    public double calcularChi() {
        double chiCalculado = 0;
        double sumatoriaDesviaciones = 0.0;
        for (int i = 0; i < this.cantIntervalos(); i++) {
            int frecObservada = this.frecuenciaIntervalos[i];
            sumatoriaDesviaciones += Math.pow(frecObservada - this.frecEsperada, 2);
        }
        // Se simplifica la formula de la sumatoria, dividiendo todo junto al final.
        return sumatoriaDesviaciones / this.frecEsperada;
    }

    public double chiAceptado() {
        // Tabla tabulada de ChiCuadrado con nivel de confianza = 0.95
        double[] tablaChi95 = {3.8, 6, 7.8, 9.5, 11.1, 12.6, 14.1, 15.5, 16.9, 
            18.3, 19.7, 21, 22.4, 23.7, 25, 26.3, 27.6,28.9,30.1,31.4,32.7,33.9,
            35.2,36.4,37.7,38.9,41.1,41.3,42.6,43.8};

        int gradosLibertad = this.cantIntervalos() - 1; // No tiene valores fijos.
        assert gradosLibertad <= tablaChi95.length; // Solo tabulamos hasta 30 grados libertad.
        return tablaChi95[gradosLibertad];
    }
}
