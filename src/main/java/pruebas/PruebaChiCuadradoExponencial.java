package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoExponencial extends PruebaChiCuadrado {   

    public PruebaChiCuadradoExponencial(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    double getFrecuenciaEsperada(Intervalo intervalo) {
        // Se asumen intervalos iguales.
        return probabilidadIntervalo(intervalo.getInicio(), intervalo.getFin()) * this.tamañoMuestra;
    }
    
    private double probabilidadAcumulada(double x){
        return 1.0 - Math.pow(Math.E, -1.0/media * x);
    }
    
    private double probabilidadIntervalo(double inicio, double fin){
        // Equivalente a probabilidadAcumulada(fin) - probabilidadAcumulada(inicio);
        return Math.pow(Math.E, -1.0/media * inicio) - Math.pow(Math.E, -1.0/media * fin);        
    }

    @Override
    int getCantidadValoresEmpiricos() {
        return 1;
    }

}
