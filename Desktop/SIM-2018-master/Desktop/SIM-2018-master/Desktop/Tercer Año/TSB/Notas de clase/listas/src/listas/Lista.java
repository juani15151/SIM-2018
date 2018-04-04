/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas;

/**
 *
 * @author a2
 */
public class Lista {
    private Nodo frente;
    
    public void agregar(Comparable x){
    Nodo nuevo = new Nodo(x, frente);
    frente = nuevo;
    
    }
    
    public void agregarFinal(Comparable x) {
    Nodo nuevo = new Nodo(x, null);
    Nodo p = frente, q = null; // q siempre va a estar en el nodo anterior a p
    //Recorrido con persecucion 
    while (p != null) {
        q = p; 
        p = p.getNext();
    }
    // Al terminar el ciclo q apunta al ultimo
    if (q != null)
        q.setNext(nuevo);
    else
        frente = nuevo; // evita la nullpointer exception, agregando el nodo nuevo si o si
    }
    
    @Override
    public String toString(){
    Nodo p = frente;
    String cad = "";
    while(p != null){
    cad += p.getInfo() + "-";
    
    p =  p.getNext();
    }
    return cad;
    }
    
    //USO COMPARTE TO PARA HACERLA GENERICA Y PODER COMPARAR TO PIOLA
    public Comparable mayor(){
        if (frente == null)
            return null;
        Comparable may = frente.getInfo();
        Nodo p = frente;
        while (p != null) {
        if (p.getInfo().compareTo(may) > 0) {
            may = p.getInfo();
        }
        p = p.getNext();
        }
        return may;
    }
}
