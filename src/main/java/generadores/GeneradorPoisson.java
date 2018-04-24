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
public class GeneradorPoisson {
    private  double p;
    private double x ;
    private double a;
    private double lambda;
    private double RND;
    
    public GeneradorPoisson(double lambda) {
        this.p = 1;
        this.x = -1;
        this.a = Math.exp(-lambda);
    }
    
    public double poisson(double RND){
        while(p >= a){
            p = p * RND ;
            x += 1;
        }
        return x;
    }
}
