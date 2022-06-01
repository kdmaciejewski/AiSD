import java.util.ArrayList;

public class ShellSort2 {
    public void sort(int arrayToSort[], ArrayList<Integer> lista) {

        int n = arrayToSort.length;
        // bierzemy przedostatni element listy i od niego zaczynamy
        for (int k = lista.size()-2 ; k >= 0 ; k--) {
            int gap = lista.get(k);

            if (lista.get(k) == 1) {  //sortowanie bąbelkowe

                int z = arrayToSort.length;
                for (int i = 0; i < z - 1; i++){
                    for (int j = 0; j < z - i - 1; j++) {
                        if (arrayToSort[j] > arrayToSort[j + 1]) {
                            // swap arr[j+1] and arr[j]
                            int temp = arrayToSort[j];
                            arrayToSort[j] = arrayToSort[j + 1];
                            arrayToSort[j + 1] = temp;
                        }
                    }
                }
                break;
            }
            for (int i = gap; i < n; i++) {
                int key = arrayToSort[i];
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
