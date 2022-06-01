import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;

// minimalne drzewo rozpinające

class KruskalAlgorithm {

    class Edge implements Comparable<Edge> {
        int source, destination, weight;

        public int compareTo(Edge edgeToCompare) {
            return this.weight - edgeToCompare.weight;
        }
    };

    // dla scalania
    class Subset {
        int parent, value;
    };

    int vertices, edges;
    Edge edgeArray[];


    KruskalAlgorithm(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edgeArray = new Edge[this.edges];
        for (int i = 0; i < edges; ++i)
            edgeArray[i] = new Edge();
    }


    void applyKruskal() {

        Edge finalResult[] = new Edge[vertices];    // tutaj przechowywane minimalne drzewo
        int newEdge = 0;
        int index = 0;
        for (index = 0; index < vertices; ++index)
            finalResult[index] = new Edge();

        Arrays.sort(edgeArray);                     // sortuje


        // tablica wierzchołków typu Subset dla podgrafów wierzchołków
        Subset subsetArray[] = new Subset[vertices];

        // by móc stworzyć podbiory wierzchołków
        for (index = 0; index < vertices; ++index)
            subsetArray[index] = new Subset();


        // tworze podzbiór z pojedynczym elementem
        for (int vertex = 0; vertex < vertices; ++vertex) {
            subsetArray[vertex].parent = vertex;        // rodzic jest indeksem
            subsetArray[vertex].value = 0;
        }

        // pobieramy najkrótszą krawędź

        index = 0;
        while (newEdge < vertices - 1) {

            Edge nextEdge = new Edge();
            nextEdge = edgeArray[index++];  // pobieramy krawędź

            // czy wierzchołki były już dołączane
            int nextSource = findSetOfElement(subsetArray, nextEdge.source);
            int nextDestination = findSetOfElement(subsetArray, nextEdge.destination);


            // jeżeli krawędź nie utworzy cyklu to dodajemy ją do finalResult i inktementujemy newEdge
            if (nextSource != nextDestination) {
                finalResult[newEdge++] = nextEdge;
                performUnion(subsetArray, nextSource, nextDestination); // scalamy
            }
        }
        // wyświetlanie
       // for (index = 0; index < newEdge; ++index)
        //    System.out.println(finalResult[index].source + " - " + finalResult[index].destination + ": " + finalResult[index].weight);
        int suma = 0;
        for (index = 0; index < newEdge; ++index){
            suma += finalResult[index].weight;
        }
        System.out.println("Wynik: " + suma);
    }

// dostajemy podzbiór danego elementu

    int findSetOfElement(Subset subsetArray[], int i) {
        if (i == vertices){
            return 0;                                        //
        }
        if (subsetArray[i].parent != i) // przechodzimy rekurencyjnie
            subsetArray[i].parent = findSetOfElement(subsetArray, subsetArray[i].parent);
        return subsetArray[i].parent;
    }

    // metoda scalająca dwa podzbiory
    void performUnion(Subset subsetArray[], int sourceRoot, int destinationRoot) {

        int nextSourceRoot = findSetOfElement(subsetArray, sourceRoot);
        int nextDestinationRoot = findSetOfElement(subsetArray, destinationRoot);

        if (subsetArray[nextSourceRoot].value < subsetArray[nextDestinationRoot].value)
            subsetArray[nextSourceRoot].parent = nextDestinationRoot;
        else if (subsetArray[nextSourceRoot].value > subsetArray[nextDestinationRoot].value)
            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
        else {
            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
            subsetArray[nextSourceRoot].value++;
        }
    }


    public static void odczytPliku(String nazwa){
        try {
            BufferedReader br = new BufferedReader(new FileReader(nazwa));
            String[] tab;

            String s = br.readLine();
            tab = s.split(" ");
            int wartosci [] = new int[tab.length];

            for (int i = 0; i < wartosci.length; i++) {
                wartosci[i] = Integer.parseInt(tab[i]);
            }
            int v = wartosci[0];
            int e = wartosci[1];
            KruskalAlgorithm graph = new KruskalAlgorithm(v, e);

            for (int i = 0; i < e; i++) {
                String st = br.readLine();
                if (st == null){
                    break;
                }
                String t[] = st.split(" ");
                int wart [] = new int[t.length];

                for (int k = 0; k < wart.length; k++) {
                    wart[k] = Integer.parseInt(t[k]);
                }

                graph.edgeArray[i].source = wart[0];

                graph.edgeArray[i].destination = wart[1];

                graph.edgeArray[i].weight = wart[2];
            }
            graph.applyKruskal();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        //odczytZKonsoli();
        odczytPliku("da1" + ".txt");
        //odczytPliku("da2" + ".txt");
        odczytPliku("da3" + ".txt");
        odczytPliku("da4" + ".txt");
        odczytPliku("da5" + ".txt");
        odczytPliku("da6" + ".txt");
        odczytPliku("da7" + ".txt");
    }

    public static void odczytZKonsoli(){
        int v, e;
        Scanner sc = new Scanner(System.in);

        System.out.println("Wprowadź listę wierzchołków: ");

        v = sc.nextInt();

        System.out.println("Wprowadź listę krawędzi");

        e = sc.nextInt();
        // tworzymy graf
        KruskalAlgorithm graph = new KruskalAlgorithm(v, e);

        for(int i = 0; i < e; i++){
            System.out.println("Wprowadź początek krawędzi["+ i +"]");
            graph.edgeArray[i].source = sc.nextInt();

            System.out.println("Wprowadź koniec krawędzi["+ i +"]");
            graph.edgeArray[i].destination = sc.nextInt();

            System.out.println("Wprowadź wagę krawędzi["+i+"]");
            graph.edgeArray[i].weight = sc.nextInt();
        }

        graph.applyKruskal();
    }
}