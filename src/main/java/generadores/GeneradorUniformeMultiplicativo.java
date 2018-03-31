package generadores;
import java.util.*;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial multiplicativo.
 * 
 * @author eric
 */
public class GeneradorUniformeMultiplicativo {
    int raiz, a, c, m;

    public GeneradorUniformeMultiplicativo(int raiz, int a, int c, int m) {
        this.raiz = raiz;
        this.a = a;
        this.c = c;
        this.m = m;
    }
    
    public GeneradorUniformeMultiplicativo(int raiz, int a, int m) {
        this.raiz = raiz;
        this.a = a;
        this.m = m;
    }
   
    public List generarMult(){

        List<Integer> x = new ArrayList<>();
        List<Float> r = new ArrayList<>();

        x.add((a * raiz) % m);
        r.add(x.get(0) / (float)(m));
        for (int i = 1; i < 20; i++) {
            x.add((a * x.get(i-1) ) % m);
            r.add(x.get(i) / (float) (m) );  
        }
        return r;  
    }
    
    public List generarMixto(){

        List<Integer> x = new ArrayList<>();
        List<Float> r = new ArrayList<>();

        x.add((a * raiz + c) % m);
        r.add(x.get(0) / (float)(m));
        for (int i = 1; i < 20; i++) {
            x.add((a * x.get(i-1) + c) % m);
            r.add(x.get(i) / (float) (m) );  
        }
        return r;  
    }
}
