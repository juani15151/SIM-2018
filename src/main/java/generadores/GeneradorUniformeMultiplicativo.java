package generadores;
import java.util.*;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial multiplicativo.
 * 
 * @author eric
 */
public class GeneradorUniformeMultiplicativo implements GeneradorUniforme {
    int semilla, A, M;
        
    public GeneradorUniformeMultiplicativo(int semilla, int a, int m) {
        this.semilla = semilla;
        this.A = a;
        this.M = m;
    }

    @Override
    public double nextDouble() {
        this.semilla = (this.A * this.semilla) % this.M;               
        return (double) this.semilla / this.M;
    }
}
