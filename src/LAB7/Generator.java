
public class Generator {

    public void insertSort(int array[]){

        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];  //kolejny element
            int i = j-1;

            // przesuwamy elementy array[0...j-1], które są większe od key jedną pozycję do przodu od obecnej
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key; // wstawiamy key na odpowiednie miejsce w posortowanej tablicy
        }
    }

    public void bubbleSort(int arr[]) {

        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++) //zmniejszamy tablice bo największego już nie sortujemy
                if (arr[j] > arr[j+1]) // jeżeli następny jest mniejszy to zamień
                {
                    //zamieniamy elementy arr[j+1] i arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

    }
    public void selectSort(int arr[]){
        int n = arr.length;

        //krok po kroku zmieniamy rozmiary tablicy
        for (int i = 0; i < n-1; i++)
        {
            // szukamy najmniejszego elementu w nieposortowanej tablicy i wstawiamy go na koniec pososrtowanej części
            int min_idx = i; //inicjalizujemy indeks najmniejszego na i
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j; //zamieniamy indeks!

            // wymieniamy najmniejszy element z tym na którym staliśmy
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }

    }

}
