package moje;

import java.util.*;

class Node{
    int miasto;
    int cel;
    int droga; // długość drogi

    public Node(int miasto, int cel, int droga) {
        this.miasto = miasto;
        this.cel = cel;
        this.droga = droga;
    }

    @Override
    public String toString() {
        return droga + "km -> " + "cel= " + cel + ",    ";
    }
}
class Miasto{
    int miasto;
    int droga;
    int poprzednik;

    public Miasto(int miasto, int droga, int poprzednik) {
        this.miasto = miasto;
        this.droga = droga;
        this.poprzednik = poprzednik;
    }

    @Override
    public String toString() {
        return "Miasto{" +
                "miasto=" + miasto +
                ", droga=" + droga +
                ", poprzednik=" + poprzednik +
                '}';
    }
    public String nietoString() {
        return "Miasto{" +
                "miasto=" + miasto +
                ", NIE MOŻNA DOTRZEĆ" +
                '}';
    }
}
class Graph {

    static void addEdge(ArrayList<ArrayList<Node> > lista, int u, int v, int dlugosc, boolean czyJedno)
    {
        // sprawdzamy jaka to droga
        if (czyJedno){
            lista.get(u).add(new Node(u,v,dlugosc));
        }
        else{
            lista.get(u).add(new Node(u,v,dlugosc));
            lista.get(v).add(new Node(v, u, dlugosc));
        }
    }

    static void printGraph(ArrayList<ArrayList<Node> > adj)
    {
        System.out.println("Lista sąsiedztwa:");
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nDla wierzchołka: " + i);

            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "+adj.get(i).get(j));
            }
            System.out.println();
        }
    }
    class NodeComparator implements Comparator<Node>{

        public int compare(Node s1, Node s2) {
            if (s1.droga <= s2.droga)
                return -1;
            else if (s1.droga > s2.droga)
                return 1;
            return 0;
        }
    }
    class MiastoComparator implements Comparator<Miasto>{

        public int compare(Miasto s1,Miasto s2) {
            if (s1.droga < s2.droga)
                return -1;
            else if (s1.droga > s2.droga)
                return 1;
            return 0;
        }
    }
    static int V = 5;
    public void dijkstra(ArrayList<ArrayList<Node>> lista, int miastoPoczatkowe){
        int dtab[] = new int[V]; // koszty dojścia
        int ptab[] = new int[V]; //poprzednik wierzchołka (żebyśmy mogli odtworzyć drogę)
        ArrayList<Miasto> obliczone = new ArrayList<>();

        dtab[miastoPoczatkowe] = 0;

        for (int i = 0; i < dtab.length; i++) {
            if (i != miastoPoczatkowe){
                dtab[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < ptab.length; i++) {
            ptab[i] = -1;
        }

        ArrayList<Miasto> queue = new ArrayList<Miasto>();

        for (int i = 0; i < dtab.length; i++) {

            queue.add(new Miasto(i, dtab[i], ptab[i]));
        }

        queue.sort(new MiastoComparator());
        obliczone.add(queue.remove(0)); // odpinamy miasto początkowe bo ma droge = 0
        int poprzednie = obliczone.get(0).miasto;

        while(!queue.isEmpty()){
            ArrayList<Node> osiagalne = lista.get(poprzednie); // sąsiedzi poprzedniego miasta

            for (int i = 0; i <lista.get(poprzednie).size() ; i++) {


                if (dtab[osiagalne.get(i).cel] > dtab[poprzednie] +osiagalne.get(i).droga){

                    dtab[osiagalne.get(i).cel] = dtab[poprzednie] +osiagalne.get(i).droga;
                    ptab[osiagalne.get(i).cel] = poprzednie;

                    int indeks = 0;
                    for (int j = 0; j < queue.size(); j++) {
                        if (queue.get(j).miasto == osiagalne.get(i).cel){
                            indeks = j;
                            break;
                        }
                    }// uswuamy elementy z queue i musimy znaleźć indeksy pozostałych miast
                    // ustawiamy składowe dobrych obiektów na nowe wartości

                    queue.get(indeks).droga =  dtab[osiagalne.get(i).cel];
                    queue.get(indeks).poprzednik = poprzednie;
                }
            }

            queue.sort(new MiastoComparator());

            Miasto m = queue.remove(0);
            poprzednie = m.miasto;
            obliczone.add(m);
        }

        obliczone.sort(new MiastoComparator());
        System.out.println("Wyświetlanie odleglości od miasta: " + miastoPoczatkowe);
        for (int i = 0; i < obliczone.size() ; i++) {
            if (obliczone.get(i).droga >= 2147000000 || obliczone.get(i).droga <= -2147400000 ){
                System.out.println(obliczone.get(i).nietoString());
            }
            else
            System.out.println(obliczone.get(i).toString());
        }
    }

    boolean [] odwiedzone = new boolean[V];

    public void DFS( ArrayList<ArrayList<Node>> lista, int m){ // m to numer miasta sąsiadującego
        ArrayList<Node> s = lista.get(m); // sąsiedzi
        for (int i = 0; i < s.size(); i++) {
            if (odwiedzone[s.get(i).cel] == false){
                System.out.println(s.get(i).cel);
                odwiedzone[s.get(i).cel] = true;
                DFS(lista, s.get(i).cel);
            }
        }

    }
    int licz = 1;
    public void DFS2( ArrayList<ArrayList<Node>> lista, int m){ // m to numer miasta sąsiadującego
        ArrayList<Node> s = lista.get(m); // sąsiedzi

        for (int i = 0; i < s.size(); i++) {

            if (odwiedzone[s.get(i).cel] == false){
                licz++;
                System.out.println(s.get(i).cel);
                odwiedzone[s.get(i).cel] = true;
                DFS2(lista, s.get(i).cel);
            }
        }
        if (licz == V){
            return;
        }
        else{
            DFS2Helper(lista);
        }

    }
    public void DFS2Starter(ArrayList<ArrayList<Node>> lista){
        for (int i = 0; i < odwiedzone.length; i++) {
            odwiedzone[i] = false;
        }
        System.out.println("\nOdwiedzone wierzchołki DFS:\n" );
        System.out.println(lista.get(0).get(0).miasto);
        DFS2Helper(lista);
    }
    public void DFS2Helper(ArrayList<ArrayList<Node>> lista){

        int l = 0;
        for (int i = 0; i < odwiedzone.length; i++) {
            if (odwiedzone[i] == false){
                l=i;
                break;
            }
        }
        DFS2(lista, l);
    }


    public void DFSHelper(ArrayList<ArrayList<Node>> lista, int m){
        for (int i = 0; i < odwiedzone.length; i++) {
            odwiedzone[i] = false;
        }
        odwiedzone[m] = true; // bo od niego zaczynamy przechodzenie
        System.out.println("\nOdwiedzone wierzchołki DFS:\n"+ m );
        DFS(lista, m);
    }

    public static void main(String[] args)
    {

        ArrayList<ArrayList<Node>> lista = new ArrayList<ArrayList<Node> >(V);

        for (int i = 0; i < V; i++)
            lista.add(new ArrayList<Node>());


        addEdge(lista, 0, 1, 10, true);
        addEdge(lista, 0, 3, 30, true);
        addEdge(lista, 0, 4, 100, true);

        addEdge(lista, 1, 2,50, true);
        addEdge(lista, 2, 4,10, true);

        addEdge(lista, 3, 2,20, true);
        addEdge(lista, 3, 4,60, true);


        printGraph(lista);
        Graph g = new Graph();
        g.dijkstra(lista, 0);
        //g.dijkstra(lista, 1);
        //g.DFSHelper(lista,3);
        g.DFS2Starter(lista);
    }
}