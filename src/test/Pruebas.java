package test;

import algoritmos.QuickSortSecuencial;
import algoritmos.QuickSortConcurrente;
import java.util.Random;
import java.util.Arrays;

public class Pruebas {
    public static void main(String[] args) {
        int n = 1_000_000; // Tamaño del arreglo
        int[] arrOriginal = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            arrOriginal[i] = random.nextInt(n);

        int[] arrSecuencial = Arrays.copyOf(arrOriginal, n);
        int[] arrConcurrente = Arrays.copyOf(arrOriginal, n);

        long inicioSec = System.currentTimeMillis();
        QuickSortSecuencial.sort(arrSecuencial);
        long finSec = System.currentTimeMillis();
        System.out.println("Tiempo de QuickSort secuencial: " + (finSec - inicioSec) + " ms");

        long inicioCon = System.currentTimeMillis();
        QuickSortConcurrente.sort(arrConcurrente);
        long finCon = System.currentTimeMillis();
        System.out.println("Tiempo de QuickSort concurrente: " + (finCon - inicioCon) + " ms");
    }

}
