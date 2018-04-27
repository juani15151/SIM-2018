package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoUniformeAB extends PruebaChiCuadrado {

    public PruebaChiCuadradoUniformeAB(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }
   
    @Override
    int cantidadValoresEmpiricos() {
        // No estoy seguro si A (minimo) y B (maximo) serian valores empiricos...
        return 0;
    }

    private double probabilidadAcumulada(double x, double a, double b){
        return (x - a) / (b - a);
    }
    
    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        // Si el intervalo esta fuera de A-B, no debe tener valores.
        if (intervalo.getFin() <= muestra.minimo()) return 0;
        if (intervalo.getInicio() >= muestra.maximo()) return 0;
        
        double acumFin = probabilidadAcumulada(intervalo.getFin(), muestra.minimo(), muestra.maximo());
        double acumInicio = probabilidadAcumulada(intervalo.getFin(), muestra.minimo(), muestra.maximo());
        
        return acumFin - acumInicio;
    }

}
