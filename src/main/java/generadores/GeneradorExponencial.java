package generadores;

import java.util.Random;



/**
 * Genera numeros aleatorios con distribucion uniforme. Utiliza el metodo
 * congruencial lineal (multiplicativo mixto). Pero si C == 0, entonces esta
 * utilizando el metodo congruencial simple.
 *
 * @author Juani
 */
public class GeneradorExponencial implements IGenerador {

    private IGenerador random;
    private int u;  // Tiempo medio entre ocurrencias?

    public GeneradorExponencial() {
        this(new Random().nextInt(1024));
    }

    public GeneradorExponencial(int semilla) {
        this(semilla, 1);
    }
    
    public GeneradorExponencial(int semilla, int u) {
        this.random = new GeneradorUniforme(semilla);
        this.u = u;
    }
    
    @Override
    public double nextDouble() {
        double val = -1.0 * this.u * Math.log(1 - random.nextDouble());
        return val;
    }
}
