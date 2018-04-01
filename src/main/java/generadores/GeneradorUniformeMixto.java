package generadores;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial lineal (multiplicativo mixto).
 * 
 * @author eric
 */
public class GeneradorUniformeMixto implements GeneradorUniforme {
    private int semilla;
    private int A; // Se recomienda sea 1 + 4 * k
    private int M; // Se recomienda ser primo relativo a M (creo que M - 1 es siempre primo relativo)
    private int C; // Se recomienda ser 2**g

    public GeneradorUniformeMixto(int semilla, int A, int C, int M) {
        if(semilla < 0 || A < 0 || C < 0 || M < 0){
            throw new IllegalArgumentException("Las constances A, C y M, y la semilla, deben ser enteros positivos.");
        }
        this.semilla = semilla;        
        this.A = A; 
        this.C = C; 
        this.M = M; 
    }   
    
    public GeneradorUniformeMixto(int semilla){
        this(semilla, 1 + 4 * 10, (int) Math.pow(2, 10) - 1, (int) Math.pow(2, 10));
    }
    
    @Override
    public double nextDouble(){
        this.semilla = (this.A * this.semilla + this.C) % this.M;               
        return (double) this.semilla / this.M;
    }
}
