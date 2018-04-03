/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a2
 */
public class Estadistica {

    private int lanzamientos;
    private Vector dado1;
    private Vector dado2;

    public Estadistica(int lanzamientos) {
        this.lanzamientos = lanzamientos;
        dado1 = new Vector(lanzamientos);
        dado2 = new Vector(lanzamientos);
    }

    @Override
    public String toString() {
        return "Estadistica{" + "\nlanzamientos=" + lanzamientos + ", \ndado1=" + dado1 + ", \ndado2=" + dado2 + '}';
    }

    public void generarLanzamientos() {
        dado1.generarDatos(1, 6);
        dado2.generarDatos(1, 6);
    }

    public int contarIguales() {
        int cont = 0;
        for (int i = 0; i < lanzamientos; i++) {
            if (dado1.getItem(i) == dado2.getItem(i)) {
                cont++;
            }
        }
        return cont;
    }

    public int buscarSumaImpar() {
        for (int i = 0; i < lanzamientos; i++) {
            if ((dado1.getItem(i) + dado2.getItem(i)) % 2 != 0) {
                return i;
            }
        }
        return -1;
    }

    public int buscarMayorValor(int dado) {
        switch (dado) {
            case 1:
                return dado1.getItem(dado1.buscarMayor());
            case 2:
                return dado2.getItem(dado2.buscarMayor());
        }
        return 0;
    }

    public Vector contarSumas() {
        Vector conteo = new Vector(13);
        for (int i = 0; i < lanzamientos; i++) {
            int suma = dado1.getItem(i) + dado2.getItem(i);
            conteo.sumarItem(suma, 1);
        }
    return conteo ;
    }
    
    public int buscarMasRepetido(int dado)
    {
        switch (dado) {
            case 1:
                return dado1.contarValores().buscarMayor(); 
            case 2:
                return dado2.contarValores().buscarMayor(); 
        }
        return 0;
    }
}

