public class Firma {
    ListQueue<Element> lista = new ListQueue<>();
    private class Element{
        private Magazyn value;
        private Element next;
        public Magazyn getValue() {
            return value;}
        public void setValue(Magazyn value) {
            this.value = value;}
        public Element getNext() {
            return next;}
        public void setNext(Element next) {
            this.next = next;}
        Element(Magazyn data){
            this.value=data;}
    }
    public void start() throws FullQueueException {
        Magazyn m1 = new Magazyn();
        Klient k1 = new Klient("Ikea");
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Krzesło", 5, 100)));
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Biurko", 5, 200)));
        k1.getLista().enqueue(new Klient.Element(new Zamowienie("Łóżko", 1, 900)));
        m1.getLista().enqueue(new Magazyn.Element(k1));

        Klient k2 = new Klient("Jysk");
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Poduszka", 20, 50)));
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Firanka", 10, 40)));
        k2.getLista().enqueue(new Klient.Element(new Zamowienie("Drzwi", 5, 500)));
        m1.getLista().enqueue(new Magazyn.Element(k2));

        Klient k3 = new Klient("Obi");
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Kran", 12, 340)));
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Wanna", 10, 2000)));
        k3.getLista().enqueue(new Klient.Element(new Zamowienie("Kabina", 5, 1000)));
        m1.getLista().enqueue(new Magazyn.Element(k3));

        Magazyn m2 = new Magazyn();
        Klient z1 = new Klient("Ikea");
        z1.getLista().enqueue(new Klient.Element(new Zamowienie("Krzesło", 5, 1)));
        z1.getLista().enqueue(new Klient.Element(new Zamowienie("Biurko", 5, 2)));
        z1.getLista().enqueue(new Klient.Element(new Zamowienie("Łóżko", 1, 30)));
        m2.getLista().enqueue(new Magazyn.Element(z1));

        Klient z2 = new Klient("Jysk");
        z2.getLista().enqueue(new Klient.Element(new Zamowienie("Poduszka", 20, 5)));
        z2.getLista().enqueue(new Klient.Element(new Zamowienie("Firanka", 10, 4)));
        z2.getLista().enqueue(new Klient.Element(new Zamowienie("Drzwi", 5, 5)));
        m2.getLista().enqueue(new Magazyn.Element(z2));

        Klient z3 = new Klient("Obi");
        z3.getLista().enqueue(new Klient.Element(new Zamowienie("Kran", 12, 34)));
        z3.getLista().enqueue(new Klient.Element(new Zamowienie("Wanna", 10, 20)));
        z3.getLista().enqueue(new Klient.Element(new Zamowienie("Kabina", 5, 10)));
        m2.getLista().enqueue(new Magazyn.Element(z3));

        lista.enqueue(new Element(m1));
        lista.enqueue(new Element(m2));
    }
    int suma = 0;
    int licznik = 0;
    public void pracuj() throws EmptyQueueException {
        int p  = 0;
        while (!lista.isEmpty()){
            Magazyn m = lista.first().getValue();
            p = m.pracuj2();
            licznik++;
            suma += p;
            System.out.println("Zlecenie zrealizowane, kwota z " +  licznik + " magazynu = " + p);
            p = 0;
            lista.dequeue(); //usuwamy magazyn
        }
        System.out.println("Suma kwot wszystkich magazynów = " + suma);
    }
}
