package pruebas;

import generadores.IGenerador;

public class PruebaChiCuadradoUniforme extends PruebaChiCuadrado {

    public PruebaChiCuadradoUniforme(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }
   
    @Override
    double getFrecuenciaEsperada(Intervalo intervalo) {
        return intervalo.getSize() / (this.maxValue - this.minValue);
    }

    @Override
    int getCantidadValoresEmpiricos() {
        return 0;
    }   

}
