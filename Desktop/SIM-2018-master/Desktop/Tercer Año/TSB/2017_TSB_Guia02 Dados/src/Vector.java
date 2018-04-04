/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a2
 */
public class Vector {

    private int v[];

    public Vector(int n) {
        if (n <= 0) {
            n = 100;
        }
        v = new int[n];
    }

    @Override
    public String toString() {
        String str = "|";
        for (int i = 0; i < v.length; i++) {
            str += v[i] + "|";
        }
        return str;
    }

    public void generarDatos(int desde, int hasta) {
        for (int i = 0; i < v.length; i++) {
            v[i] = desde + (int) Math.round(Math.random() * (hasta - desde));

        }
    }

    public int getItem(int i) {
        return v[i];
    }

    public int buscarMayor() {
        int mayor = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i] > v[mayor]) {
                mayor = i;
            }
        }
        return mayor;
    }

    public void sumarItem(int i, int valor) {
        v[i] += valor;
    }

    public Vector contarValores() {
        Vector conteo = new Vector(7);
        for (int i = 0; i < v.length; i++) {
            conteo.sumarItem(v[i], 1);
        }
        return conteo;
    }
}
