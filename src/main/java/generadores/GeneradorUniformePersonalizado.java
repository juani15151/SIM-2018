/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

/**
 *
 * @author Octavio
 */
//le cambie el int a double para poder usarlo,no se si caga algo
public class GeneradorUniformePersonalizado implements IGenerador {
    private double limA;
    private double limB;
    GeneradorUniforme g;

    public GeneradorUniformePersonalizado(double limA, double limB) {
        this.limA = limA;
        this.limB = limB;
        this.g = new GeneradorUniforme();
    }
    
    

    @Override
    public double nextDouble() {
        return g.nextDouble() * (this.limB - this.limA)+ limA;
    }
    
}
