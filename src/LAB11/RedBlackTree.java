import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Node implements Comparable<Node>
{
    String data; //wyraz
    Node parent;
    Node left;
    Node right;
    int color; // 1 Red, 0 Black
    ArrayList<Integer> wystapienia = new ArrayList<>();

    public void wyswietlanie(){
        System.out.printf("%-13s", data + ":");
        System.out.print( wystapienia + "\n");
    }

    @Override
    public int compareTo(Node node) {
        return this.data.compareTo(node.data);
    }
}

class Wyraz
{
    String wyraz;
    int jakiWiersz;

        @Override
        public String toString() {
            return "Wyraz{" +
                    "wyraz='" + wyraz + '\'' +
                    ", jakiWiersz=" + jakiWiersz +
                    '}';
        }
}

    class MyComparator implements Comparator<Node> {
            public int compare(Node f1, Node f2) {
                return f1.data.compareTo(f2.data);
        }
    }


public class RedBlackTree {
    private Node root;
    private Node TNULL;
    ArrayList<String> lista;
    ArrayList<Wyraz> slowa;

    public void odczytajPlik(String nazwa){
        lista = new ArrayList<>();
        slowa = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nazwa));
            String linia = null;
            int licznik = 1;
            while((linia = br.readLine()) != null){
                String [] tab = linia.split(" "); // split dzieli na słowa SEDNO
                for (int i = 0; i < tab.length; i++) {
                    char[] chars = tab[i].toCharArray();
                    ArrayList<Character> arr = new ArrayList<>();

                    for (int j = 0; j < chars.length; j++) { //przenoszę wyrażenie w charach do array
                        arr.add(chars[j]);
                    }
                    for (int j = 0; j < arr.size(); j++) { // usuwanie przecinków i kropek
                        if (arr.get(j) == ',' || arr.get(j) =='.'){
                            arr.remove(j);
                        }
                        else if (arr.get(j) =='-'){
                            arr.remove(j);

                        }


                    }
                    if (arr.size() == 0) break;
                    String str = "";  // tworzenie słów
                    for (int j = 0; j < arr.size(); j++) {
                        str += arr.get(j);
                    }
                    lista.add(str);

                    Wyraz w = new Wyraz(); // tworzenie wyrazu z wystąpieniem
                    w.jakiWiersz=licznik;
                    w.wyraz = str;
                    boolean czyDodac = true;
                    slowa.add(w);
                }
                licznik++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void utworzDrzewo(){
        for (int i = 0; i < slowa.size(); i++) {
            insert(slowa.get(i));
        }
    }

    public void goAcross(){  // przechodzenie wszerz
        goAcross(root);
    }
    public void goAcross(Node n){
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);
        System.out.println("\nPrzechodzenie wszerz");
        Node node = null;
        while(!queue.isEmpty()) {
            node = queue.remove();
            node.wyswietlanie();

            if(node.left != TNULL)      // dodajemy do kolejki dzieci odnóg
                queue.add(node.left);  //wykonujemy dopóki zabraknie węzłów
            if(node.right != TNULL)
                queue.add(node.right);
        }
    }
    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.data + ": " + node.wystapienia + "\n");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
           // System.out.print(node.data + ":" + node.wystapienia + "\n");
            System.out.printf("%-13s", node.data + ":");
            System.out.print( node.wystapienia + "\n");

            inOrderHelper(node.right);
        }
    }

    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    private Node searchTreeHelper(Node node, String key) {

        if (node == TNULL || key.equals(node.data)) {
            return node;
        }

        if ( key.compareTo(node.data) < 0) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    // naprawianie po delete
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) { // jeżeli to lewe dziecko

                s = x.parent.right; // rodzeństwo
                if (s.color == 1) {

                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {

                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {

                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {

                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {

                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {

                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }


    private void rbTransplant(Node u, Node v){ // wycinanie węzła
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left){ // jeżeli to lewe dziecko
            u.parent.left = v;
        } else {    // jeżeli prawe
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(Node node, String key) {

        Node z = TNULL;
        Node x, y;

        z = searchTree(key); //szukamy węzła zawierającego key

        if (z == TNULL) { // jeśli nie znajdziemy
            System.out.println("Nie znaleziono klucza");
            return;
        }

        y = z;
        int yOriginalColor = y.color; // kolor znalezionego

        if (z.left == TNULL) { // jeżeli nie ma już lewych
            x = z.right;
            rbTransplant(z, z.right);
        }
        else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        }
        else {
            y = minimum(z.right);  //szukamy następnika
            yOriginalColor = y.color;
            x = y.right; //prawnuk z

            if (y.parent == z) {
                x.parent = y;
            }
            else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0){ // jeśli pozostał czarny to naprawiamy
            fixDelete(x);
        }
    }

    // naprawianie wstawiania
    private void fixInsert(Node k){
        Node u;

        while (k.parent.color == 1) { // dopóki kolor wstawionego to czerwony

            if (k.parent == k.parent.parent.right) { // jeżeli rodzic jest prawym dzieckiem dziadka
                u = k.parent.parent.left; // wujek lewy

                if (u.color == 1) { // jeżeli wujek czerwony
                    // zamieniamy kolory
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;    // idziemy wyżej
                }

                else {
                    if (k == k.parent.left) { // jeżeli to lewe dziecko

                        k = k.parent;
                        rightRotate(k);  // to rotacja w prawo
                    }

                    k.parent.color = 0;     // jeżeli to prawe dziecko i rodzic czarny
                    k.parent.parent.color = 1; // a dziadek czerwony
                    leftRotate(k.parent.parent);    // to rotacja w lewo
                }
            }
            // lustrzane odbicie
            else {      // jeżeli rodzic jest lewym dzieckiem dziadka
                u = k.parent.parent.right; // wujek

                if (u.color == 1) {

                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else
                    {
                    if (k == k.parent.right) {

                        k = k.parent;
                        leftRotate(k);
                    }

                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {  // gdy dojdziemy do korzenia to koniec
                break;
            }
        }
        root.color = 0;  // korzeń musi być czarny tym niczego nie zepsujemy
    }

    public RedBlackTree() { // ustawiamy TNULL na liścia
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    public void preorder() {
        preOrderHelper(this.root);
    }
    public void inorder() {
        inOrderHelper(this.root);
    }
    public void postorder() {
        postOrderHelper(this.root);
    }

    public Node searchTree(String k) {
        return searchTreeHelper(this.root, k);
    }

    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public Node maximum(Node node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public Node successor(Node x) {
        // następnik jest w najbardziej lewym węźle prawego poddrzewa
        if (x.right != TNULL) {
            return minimum(x.right);
        }

        Node y = x.parent;
        while (y != TNULL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node predecessor(Node x) {
        //najbardziej prawy w lewym poddrzewie
        if (x.left != TNULL) {
            return maximum(x.left);
        }

        Node y = x.parent;
        while (y != TNULL && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }


    public void leftRotate(Node x) {
        Node y = x.right;   //zamiana
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }


    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(Wyraz key) {                                  //   INSERT
        Node node = new Node(); // tworzymy nowy węzeł
        node.parent = null;
        node.data = key.wyraz;
        node.left = TNULL;
        node.right = TNULL;
        node.wystapienia.add(key.jakiWiersz);
        node.color = 1; // nowy musi być czerwony

        Node y = null;
        Node x = this.root;


        while (x != TNULL) {    //przechodzimy alfabetycznie rekurencyjnie i szukamy miejsca
            y = x;
            if (node.data.compareTo(x.data) < 0) {
                x = x.left;
            } else if (node.data.compareTo(x.data) > 0){
                x = x.right;
            }
            else {      // jeżeli to słowo już było to dodajemy do wystąpień
                x.wystapienia.add(key.jakiWiersz);
                break;
            }
        }

        // y jest rodzicem x
        node.parent = y;

        if (y == null) {  // wstawiamy
            root = node;
        } else if (node.data.compareTo(y.data) < 0) {
            y.left = node;
        } else if (node.data.compareTo(y.data) > 0){
            y.right = node;
        }

        // jeżeli wstawiliśmy korzeń, kończymy
        if (node.parent == null){
            node.color = 0;
            return;
        }

        // jeżeli dziadek jest null, kończymy
        if (node.parent.parent == null) {
            return;
        }

        // naprawiamy
        fixInsert(node);
    }

    public Node getRoot(){
        return this.root;
    }

    public void deleteNode(String data) {
        deleteNodeHelper(this.root, data);
    }


}