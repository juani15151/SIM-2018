/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

/**
 *
 * @author Camila
 */
public class GeneradorPoisson implements IGenerador {
    private  double p;
    private double x ;
    private double a;
    private double lambda; // equivale a la media en esta distribucion.
    private double RND;
    GeneradorUniforme g;
    
    public GeneradorPoisson(double lambda) {
        this.reset();
        this.a = Math.exp(-lambda);        
        this.g = new GeneradorUniforme();
    }
    
    private void reset(){
        this.p = 1;
        this.x = -1;
    }
    
    @Override
    public double nextDouble(){
        this.reset();
        while(p >= a){
            RND = g.nextDouble();
            p = p * RND ;
            x += 1;
        }
        return x;
    }
}
