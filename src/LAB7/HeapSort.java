public class HeapSort {
    public void sort(int arr[])
    {
        int n = arr.length;

        // Budujemy stóg zmieniając tablicę
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // pojedynczo usuwamy elementy ze stogu
        for (int i = n - 1; i > 0; i--) {
            // przesuwamy obecny korzeń na koniec
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // wywołujemy heapify na zredukowanym stogu bo mogło się zepsuć
            heapify(arr, i, 0);
        }
    }

    // operacja na poddrzewie zakorzenionym w węźle i (indeks w tablicy arr)
    // n jest rozmiarem tablicy

    void heapify(int arr[], int n, int i)
    {
        int largest = i; // inicjalizujemy największy jako korzeń poddrzewa
        //Węzeł z indeksem i posiada dwójkę potomków z indeksami 2*i+1 oraz 2*i+2

        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // Jeżeli lewe dziecko jest większe od korzenia
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Jeżeli prawe dziecko jest większe od jak na razie największego
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Jeżeli największy nie jest korzeniem to zamieniamy
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // rekursywanie zmieniamy dotknięte poddrzewo
            // bo na dole dalej może być źle
            heapify(arr, n, largest);
        }
    }
}
