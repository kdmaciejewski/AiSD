public class Klient {
    ListQueue<Element> lista;
    String nazwa;

    public Klient( String nazwa) { //ListQueue<Element> lista
        this.lista = new ListQueue<>();
        this.nazwa = nazwa;
    }
    public static class Element{ //czemu zmieniło na static
        private Zamowienie value;
        private Element next;
        public Zamowienie getValue() {
            return value;}
        public void setValue(Zamowienie value) {
            this.value = value;}
        public Element getNext() {
            return next;}
        public void setNext(Element next) {
            this.next = next;}
        Element(Zamowienie data){
            this.value=data;}
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public ListQueue<Element> getLista() {
        return lista;
    }

    public void setLista(ListQueue<Element> lista) {
        this.lista = lista;
    }
}
