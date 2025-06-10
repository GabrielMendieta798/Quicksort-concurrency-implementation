package algoritmos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class QuickSortConcurrente {

     public static void sort(int[] arr) {
        ForkJoinPool.commonPool().invoke(new QuickSortTask(arr, 0, arr.length - 1));
    }
    

    static class QuickSortTask extends RecursiveAction {
        private final int[] arr;
        private final int inicio, fin;
        private static final int UMBRAL = 10_000;


        public QuickSortTask(int[] arr, int inicio, int fin) {
            this.arr = arr;
            this.inicio = inicio;
            this.fin = fin;
        }

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

        private void quickSortSecuencial(int[] arr, int inicio, int fin) {
            if (inicio < fin) {
                int pivote = particionar(arr, inicio, fin);
                quickSortSecuencial(arr, inicio, pivote - 1);
                quickSortSecuencial(arr, pivote + 1, fin);
            }
        }

        private void intercambiar(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
