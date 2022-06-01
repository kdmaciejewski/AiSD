public class QuickSort2 {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // sprawdzam czy tablica nie jest nullem lub nie jest pusta
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    public void quicksort(int low, int high) {
        int i = low, j = high;
        // bierzemy pivota ze środka tablicy
        int pivot = numbers[low + (high-low)/2];

        // rozdzielam na dwie listy
        while (i <= j) { //dopóki się nie miną indeksy low i high

            // szukam wartości większej od pivota
            while (numbers[i] < pivot) {
                i++;
            }

            // gdy ją znajdę przechodze do prawej listy
            // szukam wartości mniejszej od pivota
            while (numbers[j] > pivot) {
                j--;
            }
            // Jeśli znajdę wartość z lewej, która jest większa od pivota i
            // jeśli znajdę wartość z prawej, która jest mnijesza od pivota
            // to zamieniam te wartości

            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Rekurencja, dzielę na kolejne tablice
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    public void exchange(int i, int j) { // zamiana wartości
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}