package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoUniformeAB extends PruebaChiCuadrado {

    public PruebaChiCuadradoUniformeAB(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    public PruebaChiCuadradoUniformeAB(IGenerador generador, int cantidadIntervalos, int tamañoMuestra) {
        super(generador, cantidadIntervalos, tamañoMuestra);
    }

    @Override
    int cantidadValoresEmpiricos() {
        // No estoy seguro si A (minimo) y B (maximo) serian valores empiricos...
        return 0;
    }

    private double probabilidadAcumulada(double x, double a, double b) {
        return (x - a) / (b - a);
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        // Si el intervalo esta fuera de A-B, no debe tener valores.
        double inicio = intervalo.getInicio();
        double fin = intervalo.getFin();

        if (fin <= muestra.minimo()) {
            return 0;
        }
        if (inicio >= muestra.maximo()) {
            return 0;
        }

        // Truncar los infinitos.
        if (inicio == Double.NEGATIVE_INFINITY) {
            inicio = muestra.minimo();
        }
        if (fin == Double.POSITIVE_INFINITY) {
            fin = muestra.maximo();
        }

        double acumFin = probabilidadAcumulada(fin, muestra.minimo(), muestra.maximo());
        double acumInicio = probabilidadAcumulada(inicio, muestra.minimo(), muestra.maximo());

        return acumFin - acumInicio;
    }

}
