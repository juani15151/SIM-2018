/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.simulacion;
import java.util.*;
/**
 *
 * @author eric
 */
public class Random {
    int raiz, a, c, m;

    public Random() {
    }

    public Random(int raiz, int a, int c, int m) {
        this.raiz = raiz;
        this.a = a;
        this.c = c;
        this.m = m;
    }
    
    public Random(int raiz, int a, int m) {
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
