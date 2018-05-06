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
public class GeneradorNormal implements IGenerador {

    private double desv;
    private double media;
    private IGenerador gen1;
    private double rnd1;
    private IGenerador gen2;
    private double rnd2;
    private boolean generarAleatorios;

    public GeneradorNormal(double desv, double media) {
        this.desv = desv;
        this.media = media;
        this.gen1 = new GeneradorUniforme();
        this.gen2 = new GeneradorUniforme();
        this.generarAleatorios = true;
    }

    public double getDesv() {
        return desv;
    }

    public double getMedia() {
        return media;
    }

    private void generarValoresRandom() {
        this.rnd1 = gen1.nextDouble();
        this.rnd2 = gen2.nextDouble();
    }

    private double calcularN1() {
        double n1 = ((Math.sqrt(-2.0 * Math.log(rnd1)))
                * (Math.cos(2.0 * Math.PI * rnd2))) * desv + media;
        return n1;
    }

    private double calcularN2() {
        double n2 = ((Math.sqrt(-2.0 * Math.log(rnd1)))
                * (Math.sin(2.0 * Math.PI * rnd2))) * desv + media;
        return n2;
    }

    @Override
    public double nextDouble() {
        double rnd;
        if (generarAleatorios) {
            generarValoresRandom();
            rnd = calcularN1();
        } else {
            rnd = calcularN2();
        }
        generarAleatorios = !generarAleatorios; // Lo invierte.
        return rnd;
    }

}
