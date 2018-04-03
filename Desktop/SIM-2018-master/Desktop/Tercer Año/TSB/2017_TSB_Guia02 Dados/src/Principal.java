/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a2
 */
import java.util.Scanner;

public class Principal {

    public static float calcularPorcentaje(int cantidad, int total) {
        if (total == 0) {
            return 0;
        } else {
            return (float) cantidad * 100 / total;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese cantidad de lanzamientos: ");
        int n = scanner.nextInt();
        Estadistica estadistica = new Estadistica(n);
        estadistica.generarLanzamientos();
        System.out.println(estadistica);
        int iguales = estadistica.contarIguales();
        System.out.println("Los dados fueron iguales " + iguales + " veces y representan el " +  calcularPorcentaje(iguales, n) + "% del total");
        int lanzamiento = estadistica.buscarSumaImpar();
        if(lanzamiento==-1)
            System.out.println("No hubo sumas impares");
        else
        System.out.println("La primer suma impar apareció en el lanzamiento " + (lanzamiento + 1));
        System.out.println("El mayor valor para el dado 1 fue " + estadistica.buscarMayorValor(1));
        System.out.println("El mayor valor para el dado 2 fue " + estadistica.buscarMayorValor(2));
        System.out.println("La cantidad de repeticiones de cada suma es " +  estadistica.contarSumas());
        System.out.println("El valor más repetido para el dado 1 fue " + estadistica.buscarMasRepetido(1));
        System.out.println("El valor más repetido para el dado 2 fue " + estadistica.buscarMasRepetido(2));
    }
}
