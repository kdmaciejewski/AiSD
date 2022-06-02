import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Algorytm {
    ArrayList<Litera> lista;   // ile razy wystąpiła dana litera
    ArrayList<ZnakSzyfr> kody; // kody zaszyfrowanych liter
    String zaszyfrowane;       // zaszyfrowane wyrażenie
    char [] tab;               // tablica char z podanego wyrażenia (ala i ola.)

    class Litera {     // wystąpienie znaków
        int count;
        char znak;
        public void wyswietlanie(){
            System.out.println("Litera: " + znak + ", licznik: "+ count);
        }
    }

    class ZnakSzyfr{    // zaszyfrowane znaki
        ArrayList<Integer> szyfr;
        char znak;
    }

    class Node {
        int data;
        char c;

        Node left;
        Node right;
    }

    class MyComparator implements Comparator<Node> {
        public int compare(Node x, Node y)
        {
            if (x.data == y.data){ // przyłączamy do tego z mniejszą ilością dzieci
                return liczDzieci(x) - liczDzieci(y);
            }
            return x.data - y.data;
        }
    }
    public int liczDzieci(Node n){  // który węzeł ma więcej dzieci
        int l = 0;
        int r = 0;
        if (n.left != null){
            l = liczDzieci(n.left) + 1;
        }
        if (n.left != null){
            r =  liczDzieci(n.right) + 1;
        }
        return l+r;
    }
    public void printCode(Node root, String s)
    {
        //jeżeli liść to drukujemy
        if (root.left == null && root.right == null) {

            System.out.println(root.c + ": " + s);
            ZnakSzyfr z = new ZnakSzyfr();
            z.znak = root.c;
            z.szyfr = new ArrayList<>();

            char [] tabch = s.toCharArray();  // tablica char z kodu znaków
            for (int i = 0; i < tabch.length; i++) {
                z.szyfr.add(tabch[i]- '0');             // do inta
            }

            kody.add(z);     // dodajemy do tablic zakodowanych znaków
            return;
        }
        // jeżeli na lewo to dodajemy 0
        // na prawo 1

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
    Node r = null;

    public void start(){  // tworzenie drzewa

        int s = lista.size();
        PriorityQueue<Node> q                           // min priorytet
                = new PriorityQueue<Node>(s, new MyComparator());

        for (int i = 0; i < s; i++) {
            Node n = new Node();

            n.c = lista.get(i).znak;        //  TWORZENIE
            n.data = lista.get(i).count;    // ilość wystąpień

            n.left = null;
            n.right = null;

            q.add(n);
        }

        Node root = null;
        //wyciągamy min wartości z drzewa aż size kolejki = 1

        while (q.size() > 1) {
            //pobieram wartości 2 minimalnych
            Node x = q.peek();
            q.poll();

            Node y = q.peek();
            q.poll();

            Node nowy = new Node();

            nowy.data = x.data + y.data ;     // + 1
            nowy.c = '-';

            nowy.left = x;
            nowy.right = y;  // drugi pobrany to prawe dziecko

            root = nowy;
            r = nowy;
            q.add(nowy);  // dodaje do kolejki
        }
        System.out.println("Kody znaków: ");
        kody = new ArrayList<>();
        printCode(root, "");
    }
    public void zapis(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("plik.txt"));
            writer.write(zaszyfrowane);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wyswietlSzyfr(){
        zaszyfrowane = "";
        for (int i = 0; i < tab.length; i++) {
            ArrayList<Integer> arr = szukajKodu(tab[i]); // szukam kodu danej litery
            for (int j = 0; j < arr.size(); j++) {
                zaszyfrowane += arr.get(j);
                System.out.print(arr.get(j));
            }
        }
    }
    public ArrayList<Integer> szukajKodu(char ch){

        for (int i = 0; i < kody.size(); i++) {
            if (kody.get(i).znak == ch){
                return kody.get(i).szyfr;
            }
        }
        return null;
    }
    public void odszyfrowywanie(){                          // odszyfrowywanie za pomocą drzewa
        System.out.println("\nOdszyfrowane wyrażenie: ");
        String s = zaszyfrowane;
        char[] array = s.toCharArray();
        Node root = r;

        for (int i = 0; i <= array.length; i++) {
            if (i==array.length){ // przypadek końcowy
                System.out.println(root.c);
                break;
            }

            char ch = array[i];
            if (root.left == null && root.right == null){ // jeżeli to liść to wyświetlamy
                System.out.print(root.c);
                root=r;                                 // czyścimy root
            }
            if (ch == '0'){
                root = root.left;
            }
            if (ch == '1'){
                root = root.right;
            }
        }
    }

    public void oczytajPlik(String nazwa){
        lista = new ArrayList<>();     // dodajemy znaki i ilość wystąpień
        try {
            BufferedReader br = new BufferedReader(new FileReader(nazwa));
            String s = br.readLine();
            tab = s.toCharArray();
            for (int i = 0; i < tab.length; i++) {
                boolean dalej = true;
                if (i>=1){
                    for (int j = 0; j < lista.size(); j++) { // jeżeli powtórzony
                        if (lista.get(j).znak == tab[i]){
                            dalej = false;
                            lista.get(j).count+=1;
                            break;
                        }
                    }
                }
                if (dalej){
                    Litera l = new Litera();
                    l.count+=1;
                    l.znak = tab[i];
                    lista.add(l);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wyswietl(){
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).wyswietlanie();
        }
    }

}
