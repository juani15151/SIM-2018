package generadores;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial lineal (multiplicativo mixto).
 * 
 * @author eric
 */
public class GeneradorUniformeMixto {
    double semilla;
    int a, c, m;

    public GeneradorUniformeMixto(double semilla, int a, int c, int m) {
        this.semilla = semilla;
        this.a = a; // Se recomienda sea 1 + 4 * k
        this.c = c; // Se recomienda ser primo relativo a M (creo que M - 1 es siempre primo relativo)
        this.m = m; // Se recomienda ser 2**g
    }   
    
    public double nextDouble(){
        this.semilla = (this.a * this.semilla + this.c) % this.m;               
        return this.semilla / (double) this.m;
    }
}
