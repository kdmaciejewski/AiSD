import java.util.ArrayList;

public class ShellSort3 {
    public void sort(int arrayToSort[], ArrayList<Integer> lista) {

        int n = arrayToSort.length;
        // bierzemy przedostatni element listy i od niego zaczynamy
        for (int k = lista.size()-2 ; k >= 0 ; k--) {
            int gap = lista.get(k);

            if (lista.get(k) == 1) {

                for (int i = gap; i < n; i++) {         // INSERTION SORT
                    int key = arrayToSort[i];
                    int j = i;
                    while (j >= gap && arrayToSort[j - gap] > key) {
                        arrayToSort[j] = arrayToSort[j - gap];
                        j = j - gap;
                    }
                    arrayToSort[j] = key;
                }
                break;
            }


            for (int i = 0; i < arrayToSort.length; i++) {
                ArrayList<Integer> arr = new ArrayList<>();
                int a = gap;
                int z = arr.size();
                while(a<=arrayToSort.length){      // szukam indeksów tablicy które w kroku mają być posortowane
                    arr.add(a);
                    a += gap;
                }
                for (int l = 0; l <= z - 1; l++){ // sortowanie bombelkowe
                    for (int j = 0; j < z - l - 1; j++) {

                        if (arrayToSort[j] > arrayToSort[j + 1]) {
                            // swap arr[j+1] and arr[j]
                            int temp = arrayToSort[j];
                            arrayToSort[j] = arrayToSort[j + 1];
                            arrayToSort[j + 1] = temp;
                        }
                    }
                }

                arr.clear();
            }
        }

    }
}
// int n = arrayToSort.length;
//        // bierzemy przedostatni element listy i od niego zaczynamy
//        for (int k = lista.size()-2 ; k >= 0 ; k--) {
//            int gap = lista.get(k);
//
//            if (lista.get(k) == 1) {
//
//                for (int i = gap; i < n; i++) {         // INSERTION SORT
//                    int key = arrayToSort[i];
//                    int j = i;
//                    while (j >= gap && arrayToSort[j - gap] > key) {
//                        arrayToSort[j] = arrayToSort[j - gap];
//                        j = j - gap;
//                    }
//                    arrayToSort[j] = key;
//                }
//                break;
//            }
//
//
//            for (int i = 0; i < arrayToSort.length; i++) {
//                //ArrayList<Integer> arr = new ArrayList<>();
//
//                int licznik = 0;
//                int a = gap;
//                //int z = arr.size();
//
//                while(a<=arrayToSort.length){      // szukam indeksów tablicy które w kroku mają być posortowane
//                    //arr.add(a);
//                    a += gap;
//                    licznik++;
//                }
//                int tablica [] = new int[licznik];
//                int x = 0;
//                a = gap;
//                while(a<=arrayToSort.length){      // szukam indeksów tablicy które w kroku mają być posortowane
//                    tablica[x] = a;
//                    a += gap;
//                    x++;
//                }
//                int z = tablica.length;
//
//                for (int l = 0; l <= z - 1; l++){ // sortowanie bombelkowe
//                    for (int j = 0; j < z - l - 1; j++) {
//
//                        if (arrayToSort[j] > arrayToSort[j + 1]) {
//                            // swap arr[j+1] and arr[j]
//                            int temp = arrayToSort[j];
//                            arrayToSort[j] = arrayToSort[j + 1];
//                            arrayToSort[j + 1] = temp;
//                        }
//                    }
//                }
//
//                //arr.clear();
//            }
//        }