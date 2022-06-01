import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class TwoWayCycledListWithSentinel<E> extends AbstractList<E> {
    private class Element{
        private E value;
        private Element next;
        private Element prev;
        public E getValue() { return value; }
        public void setValue(E value) { this.value = value; }
        public Element getNext() {return next;}
        public void setNext(Element next) {this.next = next;}
        public Element getPrev() {return prev;}
        public void setPrev(Element prev) {this.prev = prev;}
        Element(E data){this.value=data;}
        /** elem będzie stawiony <b> za this </b>*/
        public void insertAfter(Element elem){
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);}
        /** elem będzie stawiany <b> przed this </b>*/
        public void insertBefore(Element elem){
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);}
        /** elem będzie usuwany z listy w której jest <p>
         * <b>Założenie:</b> element jest już umieszczony w liście i nie jest to sentinel */
        public void remove(){
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());}}
    Element sentinel=null;
    public TwoWayCycledListWithSentinel() {
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);}
    private Element getElement(int index){
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && counter<index){
            counter++;
            elem=elem.getNext();}
        if(elem==sentinel)
            throw new IndexOutOfBoundsException();
        return elem;}
    private Element getElement(E value){
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && !value.equals(elem.getValue())){
            counter++;
            elem=elem.getNext();}
        if(elem==sentinel)
            return null;
        return elem;}
    public boolean isEmpty() {
        return sentinel.getNext()==sentinel;}
    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);}
    public boolean contains(E value) {
        return indexOf(value)!=-1;}
    public E get(int index) {
        Element elem=getElement(index);
        return elem.getValue();}
    public E set(int index, E value) {
        Element elem=getElement(index);
        E retValue=elem.getValue();
        elem.setValue(value);
        return retValue;}
    public boolean add(E value) {
        Element newElem=new Element(value);
        sentinel.insertBefore(newElem);
        return true;}
    public boolean add(int index, E value) {
        Element newElem=new Element(value);
        if(index==0) sentinel.insertAfter(newElem);
        else{
            Element elem=getElement(index-1);
            elem.insertAfter(newElem);}
        return true;}
    public int indexOf(E value) {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && !elem.getValue().equals(value)){
            counter++;
            elem=elem.getNext();}
        if(elem==sentinel)
            return -1;
        return counter;}
    public E remove(int index) {
        Element elem=getElement(index);
        elem.remove();
        return elem.getValue();}
    public boolean remove(E value) {
        Element elem=getElement(value);
        if(elem==null) return false;
        elem.remove();
        return true;}
    public int size() {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel){
            counter++;
            elem=elem.getNext();}
        return counter;}
    public Iterator<E> iterator() {
        return new TWCIterator();}
    private class TWCIterator implements Iterator<E>{
        Element _current=sentinel;
        public boolean hasNext() {
            return _current.getNext()!=sentinel;}
        public E next() {
            _current=_current.getNext();
            return _current.getValue();}
    }
    public ListIterator<E> listIterator() {
        return new TWCListIterator();}
    private class TWCListIterator implements ListIterator<E>{
        boolean wasNext=false;
        boolean wasPrevious=false;
        /** strażnik */
        Element _current=sentinel;
        public boolean hasNext() {
            return _current.getNext()!=sentinel;}
        public boolean hasPrevious() {
            return _current!=sentinel;}
        public int nextIndex() {
            throw new UnsupportedOperationException();}
        public int previousIndex() {
            throw new UnsupportedOperationException();}
        public E next() {
            wasNext=true;
            wasPrevious=false;
            _current=_current.getNext();
            return _current.getValue();}
        public E previous() {
            wasNext=false;
            wasPrevious=true;
            E retValue=_current.getValue();
            _current=_current.getPrev();
            return retValue;}
        public void remove() {
            if(wasNext){
                Element curr=_current.getPrev();
                _current.remove();
                _current=curr;
                wasNext=false;}
            if(wasPrevious){
                _current.getNext().remove();
                wasPrevious=false;}}
        public void add(E data) {
            Element newElem=new Element(data);
            _current.insertAfter(newElem);
            _current=_current.getNext();}
        public void set(E data) {
            if(wasNext){
                _current.setValue(data);
                wasNext=false;}
            if(wasPrevious){
                _current.getNext().setValue(data);
                wasNext=false;}}}

    TwoWayCycledListWithSentinel<Auto> t;
    public static int ilosc = 4;
    public void rozpocznij() {
        System.out.println("Zapis do pliku...");

        Auto a = new Auto(11, "Opel", "Sedan", 2012, 9000, "czerwony", 16);
        Auto a12 = new Auto(12, "Fiat", "Coupe", 2004, 2000, "żółty", 14);
        Auto a13 = new Auto(13, "Mazda", "Kabriolet", 2016, 15000, "purpurowy", 7);
        Auto a14 = new Auto(14, "A3", "Hatchback", 2021, 40000, "czarny", 3);

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Autka.ser"));
            os.writeObject(Integer.valueOf(4));
            os.writeObject(a);
            os.writeObject(a14);
            os.writeObject(a12);
            os.writeObject(a13);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void oczytPliku() throws IOException {
       System.out.println("Odczyt z pliku...");
//        t = new TwoWayCycledListWithSentinel<>();
//        try {
//            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Autka.ser"));
//            Auto at = null;
//            while ((at = (Auto) is.readObject()) != null){
//                t.sortowanie(at);
//            }
////            is.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
       // t = new TwoWayCycledListWithSentinel<>();
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("Autka.ser"))){
            int ile = is.readInt();
            for (int i = 0; i < ile; i++) {
                t.sortowanie((Auto) is.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void nowaBaza(){
       // t = new TwoWayCycledListWithSentinel<>();
        System.out.println("Utworzenie bazy danych...");
        Auto a = new Auto(11, "Opel", "Sedan", 2012, 9000, "czerwony", 16);
        Auto a12 = new Auto(12, "Fiat", "Coupe", 2004, 2000, "żółty", 14);
        Auto a13 = new Auto(13, "Mazda", "Kabriolet", 2016, 15000, "purpurowy", 7);
        Auto a14 = new Auto(14, "Audi", "Hatchback", 2021, 40000, "czarny", 3);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Autka.ser"));
            os.writeObject(4);
            os.writeObject(a);
            os.writeObject(a12);
            os.writeObject(a14);
            os.writeObject(a13);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void zapisBazy(){
        System.out.println("Zapis bazy danych...");
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Autka.ser"));
            int x = size();
            os.writeObject(x);                  //pierwszą wartościa jest ilość obiektów w tej bazie danych
            for (int i = 0; i < size(); i++) {
                os.writeObject(get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void odczytBazy(){
        System.out.println("Odczyt bazy danych...");
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Autka.ser"));
            int x = (Integer) is.readObject();
            clear();
            for (int i = 0; i < x; i++) {
                Auto a = (Auto) is.readObject();
                sortowanie(a);
            }
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void dodaj(){
        System.out.println("Dodawanie samochodu");
        System.out.println("Podaj numer silnika");
        Scanner s1 = new Scanner(System.in);
        int numer = s1.nextInt();

        System.out.println("Podaj markę");
        Scanner s2 = new Scanner(System.in);
        String marka = s2.nextLine();

        System.out.println("Podaj typ");
        Scanner s3 = new Scanner(System.in);
        String typ = s3.nextLine();

        System.out.println("Podaj datę");
        Scanner s4 = new Scanner(System.in);
        int data = s4.nextInt();

        System.out.println("Podaj cenę");
        Scanner s5 = new Scanner(System.in);
        int cena = s5.nextInt();

        System.out.println("Podaj kolor");
        Scanner s6 = new Scanner(System.in);
        String kolor = s6.nextLine();

        System.out.println("Podaj ile miesięcy jest składowane");
        Scanner s7 = new Scanner(System.in);
        int skladowanie = s7.nextInt();

        Auto a = new Auto(numer, marka, typ, data,cena, kolor, skladowanie);
        sortowanie(a);
    }
    public void odczyt(){
        System.out.println("Odczyt z pliku...");
        try {
            BufferedReader br = new BufferedReader(new FileReader("Lista.ser"));
            String wiersz = null;
            while ((wiersz = br.readLine()) != null){
                String[] wartosci = wiersz.split(", ");
                Auto a = new Auto(Integer.parseInt(wartosci[0]), wartosci[1], wartosci[2],Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]), wartosci[5], Integer.parseInt(wartosci[6]));
                sortowanie(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sortowanie(Auto a){
        int x = 0;
        if (isEmpty()){
            add(0,(E)a);
            return;
        }
        if (size()==1){
            Auto z = (Auto) get(0);
            if (z.getNr_silnika() >= a.getNr_silnika()){
                add(2,(E) a);
                return;
            }
            else {
                add(1,(E) a);
                return;
            }
        }
        for (int i = 0; i < size(); i++) {
            Object auto = (Object) get(i);
            Auto at = (Auto) get(i);
            x = a.compareTo(auto);
            if (x==1){
                add(i, (E) a);
                break;             //zmienione z break
            }
        }
        if (x==-1){
            add((E) a);                 //tutaj zmienione z index = 0
        }
    }
    public void daneJednego(){
        System.out.println("Podaj indeks samochodu, których chcesz wyświetlić");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        System.out.println(get(x).toString());
    }
    public void usun(){
        System.out.println("Podaj indeks samochodu, który chcesz usunąć");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        remove(x);
        System.out.println("Usunięto samochód o nr: " + x);
    }
    public void wyswietl(){
        for (int i = 0; i < size(); i++) {
            System.out.println(get(i).toString());
        }
    }
    public void aktualizacja(){
        System.out.println("Podaj indeks samochodu, który chcesz zaktualizować");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        System.out.println("Podaj numer silnika");
        Scanner s1 = new Scanner(System.in);
        int numer = s1.nextInt();

        System.out.println("Podaj markę");
        Scanner s2 = new Scanner(System.in);
        String marka = s2.nextLine();

        System.out.println("Podaj typ");
        Scanner s3 = new Scanner(System.in);
        String typ = s3.nextLine();

        System.out.println("Podaj datę");
        Scanner s4 = new Scanner(System.in);
        int data = s4.nextInt();

        System.out.println("Podaj cenę");
        Scanner s5 = new Scanner(System.in);
        int cena = s5.nextInt();

        System.out.println("Podaj kolor");
        Scanner s6 = new Scanner(System.in);
        String kolor = s6.nextLine();

        System.out.println("Podaj ile miesięcy jest składowane");
        Scanner s7 = new Scanner(System.in);
        int skladowanie = s7.nextInt();

        Auto a = new Auto(numer, marka, typ, data,cena, kolor, skladowanie);
        remove(x);
        sortowanie(a);

    }
    public void podanaMarka(){
        System.out.println("Podaj markę samochodów, którą chcesz wyświetlić");
        Scanner s = new Scanner(System.in);
        String m = s.nextLine();
        int x = 0;
        for (int i = 0; i < size(); i++) {
            Auto a = (Auto) get(i);

            if (m.equals((Object) a.getMarka())){
                System.out.println(get(i).toString());
                x++;
            }
        }
        System.out.println();
        if (x==0){
            System.out.println("Brak samochodów o podanej marce");
        }

    }
    public void wRoku(){
        System.out.println("Podaj rocznik samochodów, który chcesz wyświetlić");
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int x = 0;
        for (int i = 0; i < size(); i++) {
            Auto a = (Auto) get(i);

            if (m == a.getData()){
                System.out.println(get(i).toString());
                x++;
            }
        }
        System.out.println();
        if (x==0){
            System.out.println("Brak samochodów o podanym roczniku");
        }
    }
    public void cenaPonizej(){
        System.out.println("Podaj cenę poniżej, której wyświetlić");
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int x = 0;
        for (int i = 0; i < size(); i++) {
            Auto a = (Auto) get(i);

            if (m >= a.getCena()){
                System.out.println(get(i).toString());
                x++;
            }
        }
        System.out.println();
        if (x==0){
            System.out.println("Brak samochodów o podanej cenie");
        }
    }
}