import java.util.ArrayList;
import java.util.Random;
// wersjaE jest po prostu dla h = n/2
// wersjaF jest dla tego 0,75
public class Test {
    int tab[];
    ArrayList<Integer> ciag;
    static int rozmiar = 10000; //rozmiar tablicy

    public static void main(String[] args) {
        ShellSort1 jeden = new ShellSort1();
        ShellSort2 dwa = new ShellSort2();
        ShellSort3 trzy = new ShellSort3();

        Test t = new Test();
        t.czas();

    }
    public void czas(){
        System.out.println("Liczba elementów: " + rozmiar + "\n");
        /////////////////////////////////////////////// wersjaA
        Test t1 = new Test();// wszędzie ciąg z t1
        t1.generuj();
        t1.wersjaA();
        long startTime = System.currentTimeMillis();
        ShellSort1 jeden = new ShellSort1();
        jeden.sort(t1.tab, t1.ciag);
        long finish = System.currentTimeMillis();
        long estimatedTime = finish - startTime;
        System.out.println("Czas wykonania Wersji 1 dla ciągu a: " + estimatedTime);

        Test t2 = new Test();
        t2.generuj();
        long s = System.currentTimeMillis();
        ShellSort2 dwa = new ShellSort2();
        dwa.sort(t2.tab, t1.ciag);
        long f = System.currentTimeMillis();
        long e = f - s;
        System.out.println("Czas wykonania Wersji 2 dla ciągu a: " + e);

        Test t3 = new Test();
        t3.generuj();
        long s1 = System.currentTimeMillis();
        ShellSort3 trzy = new ShellSort3();
        trzy.sort(t3.tab, t1.ciag);
        long f1 = System.currentTimeMillis();
        long e1 = f1 - s1;
        System.out.println("Czas wykonania Wersji 3 dla ciągu a: " + e1);
        System.out.println("----------------------------------------------------------------------");
        /////////////////////////////////////////////// /////////////////// wersjaB
        Test t4 = new Test();
        t4.generuj();
        t4.wersjaB();
        long s2 = System.currentTimeMillis();
        ShellSort1 jeden1 = new ShellSort1();
        jeden1.sort(t4.tab, t4.ciag);
        long f2 = System.currentTimeMillis();
        long e2 = f2 - s2;
        System.out.println("Czas wykonania Wersji 1 dla ciągu b: " + e2);

        Test t5 = new Test();
        t5.generuj();
        long s3 = System.currentTimeMillis();
        ShellSort2 dwa1 = new ShellSort2();
        dwa1.sort(t5.tab, t4.ciag);
        long f3 = System.currentTimeMillis();
        long e3 = f3 - s3;
        System.out.println("Czas wykonania Wersji 2 dla ciągu b: " + e3);

        Test t6 = new Test();
        t6.generuj();
        long s4 = System.currentTimeMillis();
        ShellSort3 trzy1 = new ShellSort3();
        trzy1.sort(t6.tab, t4.ciag);
        long f4 = System.currentTimeMillis();
        long e4 = f4 - s4;
        System.out.println("Czas wykonania Wersji 3 dla ciągu b: " + e4);
        System.out.println("----------------------------------------------------------------------");
        /////////////////////////////////////////////// /////////////////// wersjaC
        Test t7 = new Test();
        t7.generuj();
        t7.wersjaC();
        long s5 = System.currentTimeMillis();
        ShellSort1 jeden2 = new ShellSort1();
        jeden2.sort(t7.tab, t7.ciag);
        long f5 = System.currentTimeMillis();
        long e5 = f5 - s5;
        System.out.println("Czas wykonania Wersji 1 dla ciągu c: " + e5);

        Test t8 = new Test();
        t8.generuj();
        long s6 = System.currentTimeMillis();
        ShellSort2 dwa2 = new ShellSort2();
        dwa2.sort(t8.tab, t7.ciag);
        long f6 = System.currentTimeMillis();
        long e6 = f6 - s6;
        System.out.println("Czas wykonania Wersji 2 dla ciągu c: " + e6);

        Test t9 = new Test();
        t9.generuj();
        long s7 = System.currentTimeMillis();
        ShellSort3 trzy2 = new ShellSort3();
        trzy2.sort(t9.tab, t7.ciag);
        long f7 = System.currentTimeMillis();
        long e7 = f7 - s7;
        System.out.println("Czas wykonania Wersji 3 dla ciągu c: " + e7);
        System.out.println("----------------------------------------------------------------------");
        /////////////////////////////////////////////// /////////////////// wersjaD
        Test t10 = new Test();
        t10.generuj();
        t10.wersjaD();
        long s8 = System.currentTimeMillis();
        ShellSort1 jeden3 = new ShellSort1();
        jeden3.sort(t10.tab, t10.ciag);
        long f8 = System.currentTimeMillis();
        long e8 = f8 - s8;
        System.out.println("Czas wykonania Wersji 1 dla ciągu d: " + e8);

        Test t11 = new Test();
        t11.generuj();
        long s9 = System.currentTimeMillis();
        ShellSort2 dwa3 = new ShellSort2();
        dwa3.sort(t11.tab, t10.ciag);
        long f9 = System.currentTimeMillis();
        long e9 = f9 - s9;
        System.out.println("Czas wykonania Wersji 2 dla ciągu d: " + e9);

        Test t12 = new Test();
        t12.generuj();
        long s10 = System.currentTimeMillis();
        ShellSort3 trzy3 = new ShellSort3();
        trzy3.sort(t12.tab, t10.ciag);
        long f10 = System.currentTimeMillis();
        long e10 = f10 - s10;
        System.out.println("Czas wykonania Wersji 3 dla ciągu d: " + e10);
        System.out.println("----------------------------------------------------------------------");
        /////////////////////////////////////////////// /////////////////// wersja Dodatkowe zadanie
        Test t13 = new Test();
        t13.generuj();
        t13.wersjaF();
        long s11 = System.currentTimeMillis();
        ShellSort1 jeden4 = new ShellSort1();
        jeden4.sort(t13.tab, t13.ciag);
        long f11 = System.currentTimeMillis();
        long e11 = f11 - s11;
        System.out.println("Czas wykonania Wersji 1 dla ciągu dodatkowego: " + e11);

        Test t14 = new Test();
        t14.generuj();
        long s12 = System.currentTimeMillis();
        ShellSort2 dwa4 = new ShellSort2();
        dwa4.sort(t14.tab, t13.ciag);
        long f12 = System.currentTimeMillis();
        long e12 = f12 - s12;
        System.out.println("Czas wykonania Wersji 2 dla ciągu dodatkowego: " + e12);

        Test t15 = new Test();
        t15.generuj();
        long s13 = System.currentTimeMillis();
        ShellSort3 trzy4 = new ShellSort3();
        trzy4.sort(t15.tab, t13.ciag);
        long f13 = System.currentTimeMillis();
        long e13 = f13 - s13;
        System.out.println("Czas wykonania Wersji 3 dla ciągu dodatkowego: " + e13);
    }



    public void wersjaA(){
        Test test = new Test();
        test.generuj();

        int h = 1;
        ciag = new ArrayList<>();
        ciag.add(h);
        while(h <= rozmiar){
            h = 3*h +1;         // trzeba wziąć size-1
            ciag.add(h);
        }
        //System.out.println(ciag);
    }
    public void wersjaB(){
        int h = 1;
        ciag = new ArrayList<>();
        ciag.add(h);
        int k = 1;
        while(h <= rozmiar){
            h = (int) (Math.pow(2, k) - 1);
            ciag.add(h);
            k++;
        }
    }
    public void wersjaC(){
        int h = 1;
        ciag = new ArrayList<>();
        ciag.add(h);
        int k = 1;
        while(h <= rozmiar){
            h = (int) (Math.pow(2, k) + 1);
            ciag.add(h);
            k++;
        }

    }
    public void wersjaD(){
        int h = 0;
        ciag = new ArrayList<>();
        ciag.add(0);
        ciag.add(1);
        int hformer = 1;
        int hformer2 = 0;
        while(h <= rozmiar){
            h = hformer + hformer2;
            hformer2 = hformer;
            hformer = h;

            ciag.add(h);
        }
    }
    public void wersjaE(){
        int h = rozmiar/2;
        ArrayList<Integer> pomoc  = new ArrayList<>();
        ciag = new ArrayList<>();
        pomoc.add(h);
        while(h >= 1){
            h = h/2;
            pomoc.add(h);
        }
        for (int i = pomoc.size()-1; i >= 0 ; i--) { //zapisuje do ciągu odwrotnie
            ciag.add(pomoc.get(i));
        }
        ciag.add(rozmiar+2);
    }
    public void wersjaF(){
        ciag = new ArrayList<>();
        int h = rozmiar/2;  
        ArrayList<Integer> pomoc  = new ArrayList<>();
        ArrayList<Integer> arr  = new ArrayList<>();
        ciag = new ArrayList<>();
        pomoc.add(h);
        while(h >= 1){
            h = h/2;
            pomoc.add(h);
        }

        // pierwszy element ciagu to rozmiar/4, dlatego zaczynamy od drugiego elementu
        arr.clear();
        arr.add(pomoc.get(1));
        for (int i = 2; i < pomoc.size(); i++) { //zaczynamy od drugiego elementu
            arr.add((int) (pomoc.get(i-1)*0.75));
        }
        for (int i = arr.size()-1; i >= 0 ; i--) { //zapisuje do ciągu odwrotnie
            ciag.add(arr.get(i));
        }
        ciag.add(rozmiar+2);  //CZY NA PEWNO
    }

    public void generuj(){
        tab = new int[rozmiar];

        for (int i = 0; i < rozmiar; i++) {
            Random random = new Random();
            int l = random.nextInt(Integer.MAX_VALUE);
            tab[i] = l;
        }
    }
}
 