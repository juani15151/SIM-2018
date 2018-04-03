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
public class Funciones {
    public float Promedio(int v[], int d[]) {
        int c = 0;
        for (int i = 0; i < v.length; i++) {
            if(v[i] == d[i]){
            c++;
            }
               
        }
    return c;
    }
    
    public int sumaImpar(int v[], int d[]) {
        for (int i = 0; i < v.length ; i++) {
            if((v[i] + d[i]) % 2 != 0){
            return i+1;
            }
           
            }
            return -1;
        }
        
    public int Mayor(int v[]) {
        int may = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] > v[may]){
            may = i;
            }
            
        }
        return may;
        
    }
    public int[] sumarDado(int v[], int d[]){
        int c[];
        c = new int[13];
        for (int i = 0; i < v.length ; i++) {
            c[d[i] + v[i]] += 1;
        }
        
        return c;
    }
    
    public String cadenaVector(int v[]) {
        String str = "|";
        for (int i = 0; i < v.length; i++) {
            str += v[i] + "|";
        }
        return str;
    }
    
    public int[] contarValor(int v[], int d[]){
    int c[];
    c = new int[7];
        for (int i = 0; i < v.length ; i++) {
            c[v[i]] += 1;
            c[d[i]] += 1;
            
        }
        return c;
    }
    
   
    
    }
    

