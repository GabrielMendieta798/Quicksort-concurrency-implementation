package algoritmos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class QuickSortConcurrente {

    // Método público para iniciar el ordenamiento concurrente
     public static void sort(int[] arr) {
        ForkJoinPool.commonPool().invoke(new QuickSortTask(arr, 0, arr.length - 1));
    }
    

    static class QuickSortTask extends RecursiveAction {
        private final int[] arr; // Arreglo a ordenar
        private final int inicio, fin; // indice de inicio y fin del subarreglo
        private static final int UMBRAL = 10_000; // Umbral para usar ordenamiento secuencial


        // Constructor que inicializa la tarea con el subarreglo a ordenar
        public QuickSortTask(int[] arr, int inicio, int fin) {
            this.arr = arr;
            this.inicio = inicio;
            this.fin = fin;
        }

        // Método que define la lógica de la tarea cuando se ejecuta
        @Override
        protected void compute() {
            if (inicio < fin) {
                if (fin - inicio < UMBRAL) {
                    quickSortSecuencial(arr, inicio, fin);
                } else {
                    int pivote = particionar(arr, inicio, fin);
                    invokeAll(
                        new QuickSortTask(arr, inicio, pivote - 1),
                        new QuickSortTask(arr, pivote + 1, fin)
                    );
                }
            }
        }

        // Método que realiza la partición del arreglo y devuelve la posición del pivote
        private int particionar(int[] arr, int inicio, int fin) {
            int pivote = arr[fin];
            int i = inicio - 1;
            for (int j = inicio; j < fin; j++) {
                if (arr[j] <= pivote) {
                    i++;
                    intercambiar(arr, i, j);
                }
            }
            intercambiar(arr, i + 1, fin);
            return i + 1;
        }

        // Método auxiliar que implementa QuickSort de forma secuencial
        private void quickSortSecuencial(int[] arr, int inicio, int fin) {
            if (inicio < fin) {
                int pivote = particionar(arr, inicio, fin);
                quickSortSecuencial(arr, inicio, pivote - 1);
                quickSortSecuencial(arr, pivote + 1, fin);
            }
        }

        // Método auxiliar para intercambiar dos elementos dentro del arreglo
        private void intercambiar(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
