/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

/**
 *
 * @author eric
 */
public class GeneradorNormalConvolucion implements IGenerador {

    private double desv;
    private double media;
    private IGenerador gen;

    public GeneradorNormalConvolucion(double desv, double media) {
        this.desv = desv;
        this.media = media;
        this.gen = new GeneradorUniforme();
    }

    public double getDesv() {
        return desv;
    }

    public double getMedia() {
        return media;
    }  

    @Override
    public double nextDouble() {
        double sumatoria = 0.0;
        for(int i = 0; i < 12; i++){
            sumatoria += gen.nextDouble() - 6;
        }
        return sumatoria * desv + media;
    }

}
