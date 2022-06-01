public class Magazyn {
    ListQueue<Element> lista = new ListQueue<>();
    public static class Element{
        private Klient value;
        private Element next;
        public Klient getValue() {
            return value;}
        public void setValue(Klient value) {
            this.value = value;}
        public Element getNext() {
            return next;}
        public void setNext(Element next) {
            this.next = next;}
        Element(Klient data){
            this.value=data;}
    }
    public void start() throws FullQueueException {
        Klient k1 = new Klient("Ikea");
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Krzesło", 5, 100)));
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Biurko", 5, 200)));
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Łóżko", 1, 900)));
        lista.enqueue(new Element(k1));

        Klient k2 = new Klient("Jysk");
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Poduszka", 20, 50)));
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Firanka", 10, 40)));
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Drzwi", 5, 500)));
        lista.enqueue(new Element(k2));

        Klient k3 = new Klient("Obi");
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Kran", 12, 340)));
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Wanna", 10, 2000)));
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Kabina", 5, 1000)));
        lista.enqueue(new Element(k3));
    }
    int suma = 0;
    public int pracuj() throws EmptyQueueException {
        while (!lista.isEmpty()){

            Klient k = lista.first().getValue();
            int kwota = 0;
            while (!k.getLista().isEmpty()){
                Zamowienie z = k.getLista().first().getValue();
                int cena = z.getCena();
                int n = z.getLiczba();
                kwota += cena*n;
                k.getLista().dequeue(); // usuwamy produkt
            }
            System.out.println("Zlecenie zrealizowane: " + lista.first().getValue().getNazwa()  + ", kwota do zapłaty = " + kwota);
            suma += kwota;
            kwota = 0;//czyścimy kwotę dla następnego klienta
            lista.dequeue(); //usuwamy klienta
        }
        System.out.println("Suma kwot wszystkich klientów = " + suma);
        return suma;
    }
    public int pracuj2() throws EmptyQueueException { //metoda dla firmy żeby nie wyświetlało zbędnych komunikatów
        while (!lista.isEmpty()){

            Klient k = lista.first().getValue();
            int kwota = 0;
            while (!k.getLista().isEmpty()){
                Zamowienie z = k.getLista().first().getValue();
                int cena = z.getCena();
                int n = z.getLiczba();
                kwota += cena*n;
                k.getLista().dequeue(); // usuwamy produkt
            }
            suma += kwota;
            kwota = 0;//czyścimy kwotę dla następnego klienta
            lista.dequeue(); //usuwamy klienta
        }
        return suma;
    }
    public ListQueue<Element> getLista() {
        return lista;
    }
}
