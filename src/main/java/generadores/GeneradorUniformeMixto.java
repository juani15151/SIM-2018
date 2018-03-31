package generadores;
import java.util.ArrayList;
import java.util.List;
/**
 * Genera numeros aleatorios con distribucion uniforme.
 * Utiliza el metodo congruencial lineal (multiplicativo mixto).
 * 
 * @author eric
 */
public class GeneradorUniformeMixto {
    int raiz, a, c, m;

    public GeneradorUniformeMixto(int raiz, int a, int c, int m) {
        this.raiz = raiz;
        this.a = a;
        this.c = c;
        this.m = m;
    }   

    public List generar(){

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
