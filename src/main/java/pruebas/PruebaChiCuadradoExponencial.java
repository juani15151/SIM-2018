package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoExponencial extends PruebaChiCuadrado {
    
    private int u;

    public PruebaChiCuadradoExponencial(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    double getFrecuenciaEsperada(int numeroIntervalo) {
        // Se asumen intervalos iguales.
        // TODO: En Chi seguramente hay que agrupar.
        double tamañoIntervalo = this.tamañoMuestra / this.cantidadIntervalos;
        double inicioIntervalo = tamañoIntervalo * numeroIntervalo;
        double finIntervalo = inicioIntervalo + tamañoIntervalo;
        return probabilidadIntervalo(inicioIntervalo, finIntervalo) * this.tamañoMuestra;
    }
    
    double probabilidadAcumulada(double x){
        return 1.0 - Math.pow(Math.E, -1.0/u * x);
    }
    
    double probabilidadIntervalo(double inicio, double fin){
        // Equivalente a probabilidadAcumulada(fin) - probabilidadAcumulada(inicio);
        return Math.pow(Math.E, -1.0/u * inicio) - Math.pow(Math.E, -1.0/u * fin);        
    }

    @Override
    int getCantidadValoresEmpiricos() {
        return 1;  // u es un valor empirico?
    }

}
