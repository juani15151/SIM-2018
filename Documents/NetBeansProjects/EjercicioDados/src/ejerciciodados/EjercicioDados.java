/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciodados;

/**
 *
 * @author Octavio
 */
public class EjercicioDados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int d1[];
        int d2[];
        d1 = new int[2];
        d2 = new int[2];
        Funciones f = new Funciones();
        for (int i = 0; i < d1.length ; i++) {
            d1[i] = (int) Math.floor(Math.random()*6+1);
            d2[i] = (int) Math.floor(Math.random()*6+1);
            System.out.println("Ronda: " + i );
            System.out.println("Dado 1: " + d1[i]);
            System.out.println("Dado 2:" + d2[i]);
        }
        System.out.println("Promedio lanzamientos: "+f.Promedio(d1, d2)/2);
        System.out.println("Primera suma impar: " + f.sumaImpar(d1, d2));
        System.out.println("Mayor numero dado 1: " + d1[f.Mayor(d1)]);
        System.out.println("Mayor numero dado 1: " + d2[f.Mayor(d2)]);
        System.out.println("La suma en cada ronda es: "+f.cadenaVector(f.sumarDado(d1,d2)));
        System.out.println("los repetidos en cada ronda son: "+f.cadenaVector(f.contarValor(d1,d2)));
        System.out.println("El que mas se repite es....: " + f.Mayor(f.contarValor(d1, d2)));
        
        
        
    
}
}
