package generadores;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial lineal (multiplicativo mixto).
 * Pero si C == 0, entonces esta utilizando el metodo congruencial simple.
 * 
 * @author eric
 */
public class GeneradorUniforme implements IGenerador {
    private int semilla;
    private int A; // Se recomienda sea 1 + 4 * k
    private int M; // Se recomienda ser 2**g
    private int C; // Se recomienda ser primo relativo a M (creo que M - 1 es siempre primo relativo)    

    public GeneradorUniforme(int semilla, int A, int C, int M) {
        if(semilla < 0 || A < 0 || C < 0 || M < 0){
            throw new IllegalArgumentException("Las constances A, C y M, y la semilla, deben ser enteros positivos.");
        }
        this.semilla = semilla;        
        this.A = A; 
        this.C = C; 
        this.M = M; 
    }   
    
    public GeneradorUniforme(int semilla){
        this(semilla, 1 + 4 * 10, (int) Math.pow(2, 10) - 1, (int) Math.pow(2, 10));
    }
    
    @Override
    public double nextDouble(){
        this.semilla = (this.A * this.semilla + this.C) % this.M;               
        return (double) this.semilla / this.M;
    }
}
