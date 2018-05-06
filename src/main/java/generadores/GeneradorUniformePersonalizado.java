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
public class GeneradorUniformePersonalizado implements IGenerador {
    private int limA;
    private int limB;
    GeneradorUniforme g;

    public GeneradorUniformePersonalizado(int limA, int limB) {
        this.limA = limA;
        this.limB = limB;
        this.g = new GeneradorUniforme();
    }
    
    

    @Override
    public double nextDouble() {
        return g.nextDouble() * (this.limB - this.limA)+ limA;
    }
    
}
