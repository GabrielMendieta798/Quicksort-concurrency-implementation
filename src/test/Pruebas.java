package test;

import algoritmos.QuickSortSecuencial;
import algoritmos.QuickSortConcurrente;
import java.util.Random;
import java.util.Arrays;

public class Pruebas {
    public static void main(String[] args) {
        int n = 1_000_000; // Tamaño del arreglo
        int[] arrOriginal = new int[n]; // Arreglo original con números aleatorios
        Random random = new Random(); // Generador de números aleatorios

        // Llenamos el arreglo con valores aleatorios entre 0 y n
        for (int i = 0; i < n; i++)
            arrOriginal[i] = random.nextInt(n);

        // Creamos copias del arreglo original para realizar ambas pruebas de forma justa            
        int[] arrSecuencial = Arrays.copyOf(arrOriginal, n);
        int[] arrConcurrente = Arrays.copyOf(arrOriginal, n);

        // Medimos el tiempo que tarda en ordenarse con QuickSort secuencial
        long inicioSec = System.currentTimeMillis();
        QuickSortSecuencial.sort(arrSecuencial);
        long finSec = System.currentTimeMillis();
        System.out.println("Tiempo de QuickSort secuencial: " + (finSec - inicioSec) + " ms");

        
        // Medimos el tiempo que tarda en ordenarse con QuickSort concurrente
        long inicioCon = System.currentTimeMillis();
        QuickSortConcurrente.sort(arrConcurrente);
        long finCon = System.currentTimeMillis();
        System.out.println("Tiempo de QuickSort concurrente: " + (finCon - inicioCon) + " ms");
    }

}
