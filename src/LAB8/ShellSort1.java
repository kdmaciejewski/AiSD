import java.util.ArrayList;

public class ShellSort1 {  //wersja 1
    public void sort(int arrayToSort[], ArrayList<Integer> lista) {


        int n = arrayToSort.length;
        // bierzemy przedostatni element listy i od niego zaczynamy
        for (int k = lista.size()-2 ; k >= 0 ; k--) {
            int gap = lista.get(k);     //pobieramy element ciągu

            for (int i = gap; i < n; i++) { //insertion sort
                int key = arrayToSort[i];   // przechodzę po odpowiednich elementach
                int j = i;
                while (j >= gap && arrayToSort[j - gap] > key) {
                    arrayToSort[j] = arrayToSort[j - gap];
                    j = j - gap;
                }
                arrayToSort[j] = key;
            }
        }

    }


}
