public class MergeSort {
    // łączy dwie podtablice tablicy arr
    // pierwsza podtablica to arr[l...m]
    // druga podtablica to arr[m+1...r]

    void merge(int arr[], int l, int m, int r)
    {
        // obliczam rozmiary podtablic
        int n1 = m - l + 1;
        int n2 = r - m;

        // tworzę tymczasowe tablice
        int L[] = new int[n1];
        int R[] = new int[n2];

        // kopiuje wartości z tymczasowych tablic
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // łączenie tymczasowych tablic //

        // indeksy 1 i 2 podtablicy
        int i = 0, j = 0;

        //początkowy indeks łączonej poddtablicy
        int k = l;
        // mniejszy element idzie na przód, dopóki jesteśmy w dobrym miejscu
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // kopiuję pozostałe elementy L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // kopiuję pozostałe elementy R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // metoda sortująca tablice arr[l..r] przy pomocy merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // szukanie środka
            int m =l+ (r-l)/2;

            // dalsze rekursywne rozdzielanie połówek
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // scalanie podtablic
            merge(arr, l, m, r);
        }
    }

}
