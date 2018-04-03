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
public class Nodo {
    private Comparable info;
    private Nodo next;

    public Nodo(Comparable info, Nodo next) {
        this.info = info;
        this.next = next;
    }
    public Nodo(){
    
    }
    /**
     * @return the info
     */
    public Comparable getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(Comparable info) {
        this.info = info;
    }

    /**
     * @return the next
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo next) {
        this.next = next;
    }
    
    
    
}
