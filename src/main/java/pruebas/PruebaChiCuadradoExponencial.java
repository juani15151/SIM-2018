package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoExponencial extends PruebaChiCuadrado {

    public PruebaChiCuadradoExponencial(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    public PruebaChiCuadradoExponencial(IGenerador generador, int cantidadIntervalos, int tamañoMuestra) {
        super(generador, cantidadIntervalos, tamañoMuestra);
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        return probabilidadIntervalo(intervalo.getInicio(), intervalo.getFin(), muestra.media());
    }

    private double probabilidadAcumulada(double x, double media) {
        if (x < 0) {
            x = 0;
        }
        return 1.0 - Math.exp((-1.0 / media) * x);
    }

    private double probabilidadIntervalo(double inicio, double fin, double media) {
        return probabilidadAcumulada(fin, media) - probabilidadAcumulada(inicio, media);
    }

    @Override
    int cantidadValoresEmpiricos() {
        // El valor empirico es la media o lambda.
        return 1;
    }

}
