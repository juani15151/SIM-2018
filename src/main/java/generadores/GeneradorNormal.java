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
public class GeneradorNormal {
    
    private double desv;
    private double media;
    private double rnd1;
    private double rnd2;

    public GeneradorNormal(double desv, double media) {
        this.desv = desv;
        this.media = media;
        this.rnd1 = Math.random();
        this.rnd2 = Math.random();
    }
    
    public double getN1(){
        double n1 = ((Math.sqrt(-2* Math.log(rnd1)))
                * (Math.cos(2*Math.PI*rnd2))) * desv + media;
        return n1;
    }
    public double getN2(){
        double n2 = ((Math.sqrt(-2* Math.log(rnd1)))
                * (Math.sin(2*Math.PI*rnd2))) * desv + media;
        return n2;
    }

    
    
}
