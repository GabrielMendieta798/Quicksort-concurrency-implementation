package algoritmos;

public class QuickSortSecuencial {


    // Método para iniciar el ordenamiento utilizando QuickSort
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }


    // Método recursivo que implementa el algoritmo QuickSort
    private static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int pivote = particionar(arr, inicio, fin);
            
            // Llamada recursiva para ordenar la parte izquierda del pivote
            quickSort(arr, inicio, pivote - 1);

             // Llamada recursiva para ordenar la parte derecha del pivote
            quickSort(arr, pivote + 1, fin);
        }
    }


    // Método que reordena los elementos y devuelve la posición final del pivote
    private static int particionar(int[] arr, int inicio, int fin) {
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


    // Método auxiliar que intercambia dos elementos dentro del arreglo
    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
}
