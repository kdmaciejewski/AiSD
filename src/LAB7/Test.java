import com.sun.tools.javac.Main;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Test {
    int tab[];
    int rtab[]; //rosnąco
    int mtab[]; //malejąco
    int ltab[]; //losowo
    static int rozmiar = 40000; //rozmiar tablicy

    public static void main(String[] args) {

        Test t = new Test();
        t.is();
        t.bb();
        t.ss();

        t.qs();
        t.hs();
        t.ms();
    }
    public void is(){
        Test t = new Test();
        t.start();
        Generator g = new Generator();
        long startTime = System.currentTimeMillis();

        g.insertSort(t.ltab);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania InsertionSort (tablica losowa): " + estimatedTime);

        long start = System.currentTimeMillis();

        g.insertSort(t.rtab);

        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania InsertionSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

        g.insertSort(t.mtab);

        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania InsertionSort (tablica malejaca): " + est);
    }
    public void ss(){
        Test t = new Test();
        t.start();
        Generator g = new Generator();
        long startTime = System.currentTimeMillis();

        g.selectSort(t.ltab);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania SelectSort (tablica losowa): " + estimatedTime);
        long start = System.currentTimeMillis();

        g.selectSort(t.rtab);

        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania SelectSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

        g.selectSort(t.mtab);

        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania SelectSort (tablica malejaca): " + est);
    }
    public void bb(){
        Test t = new Test();
        t.start();
        Generator g = new Generator();
        long startTime = System.currentTimeMillis();

        g.bubbleSort(t.ltab);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania BubbleSort (tablica losowa): " + estimatedTime);

        long start = System.currentTimeMillis();

        g.bubbleSort(t.rtab);

        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania BubbleSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

        g.bubbleSort(t.mtab);

        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania BubbleSort (tablica malejaca): " + est);
    }
    public void hs(){
        Test t = new Test();
        t.start();
        HeapSort h = new HeapSort();
        long startTime = System.currentTimeMillis();

        h.sort(t.ltab);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania HeapSort (tablica losowa): " + estimatedTime);

        long start = System.currentTimeMillis();

        h.sort(t.rtab);

        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania HeapSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

        h.sort(t.mtab);

        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania HeapSort (tablica malejaca): " + est);
    }
    public void qs(){
        Test t = new Test();
        t.start();
        QuickSort q = new QuickSort();
        QuickSort2 dwa = new QuickSort2();
        long startTime = System.currentTimeMillis();
        dwa.sort(t.ltab);
       // QuickSort.quickSort(t.ltab,0, rozmiar-1);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania QuickSort (tablica losowa): " + estimatedTime);

        long start = System.currentTimeMillis();

        //QuickSort.quickSort(t.rtab,0, rozmiar-1);
        dwa.sort(t.rtab);
        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania QuickSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

       // QuickSort.quickSort(t.mtab,0, rozmiar-1);
        dwa.sort(t.mtab);
        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania QuickSort (tablica malejaca): " + est);
    }
    public void ms(){
        Test t = new Test();
        t.start();
        MergeSort ob = new MergeSort();
        long startTime = System.currentTimeMillis();

        ob.sort(t.ltab, 0, rozmiar - 1);

        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;

        System.out.println("Czas wykonania MergeSort (tablica losowa): " + estimatedTime);
        long start = System.currentTimeMillis();

        ob.sort(t.rtab, 0, rozmiar - 1);

        long f = System.currentTimeMillis();
        long estimated = f - start;

        System.out.println("Czas wykonania MergeSort (tablica rosnąca): " + estimated);

        long s = System.currentTimeMillis();

        ob.sort(t.mtab, 0, rozmiar - 1);

        long end = System.currentTimeMillis();
        long est = end - s;

        System.out.println("Czas wykonania MergeSort (tablica malejaca): " + est);
    }
    public void start(){
        ltab = new int[rozmiar];
        for (int i = 0; i < rozmiar; i++) {
            Random random = new Random();
            int l = random.nextInt(Integer.MAX_VALUE);
            ltab[i] = l;
        }
        rtab = new int[rozmiar];
        for (int i = 0; i < rozmiar; i++) {
            Random random = new Random();
            int l = random.nextInt(Integer.MAX_VALUE);
            rtab[i] = l;
        }
        Arrays.sort(rtab);

        int [] pomoc = new int[rozmiar];
        int [] mt = new int[rozmiar];

        for (int i = 0; i < rozmiar; i++) {
            Random random = new Random();
            int l = random.nextInt(Integer.MAX_VALUE);
            pomoc[i] = l;
        }
        Arrays.sort(pomoc);

        int j = 0;
        for (int i = rozmiar-1; i >= 0; i--) {
            mt[j] = pomoc[i];
            j++;
        }
        mtab = mt;
    }
    public int[] rosnaco(){
        Arrays.sort(tab);
        return tab;
    }
    public int[] malejaco(){
        Arrays.sort(tab);
        mtab = new int[rozmiar];
        int j = 0;
        for (int i = rozmiar-1; i >= 0; i--) {
            mtab[j] = tab[i];
            j++;
        }
        tab = mtab;
        return tab;
    }
    public int[] sprawdz(int n){
        int tablica[] = new int[rozmiar];
        if (n==1){
            tablica = ltab;
        }
        if (n==2){
            tablica = rtab;
        }
        if (n==3){
            tablica = mtab;
        }

        return tablica;
    }
}
