import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

//E to Karta
public class OneWayLinkedListWithHead<E> extends AbstractList<E>{
    private class Element{
        private E value;
        private Element next;
        public E getValue() {
            return value;}
        public void setValue(E value) {
            this.value = value;}
        public Element getNext() {
            return next;}
        public void setNext(Element next) {
            this.next = next;}
        Element(E data){
            this.value=data;}
    }
    Element head=null;
    public OneWayLinkedListWithHead(){}

    public boolean isEmpty(){
        return head==null;}

    public void clear() {
        head=null;}

    @Override
    public int size() {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;}
    /** zwraca referencję na Element, wewnętrzną klasę */
    private Element getElement(int index){
        Element actElem=head;
        while(index>0 && actElem!=null){
            index--;
            actElem=actElem.getNext();
        }
        return actElem;
    }
    @Override
    public boolean add(E e) {
        Element newElem=new Element(e);
        if(head==null){
            head=newElem;
            return true;
        }
        Element tail=head;
        while(tail.getNext()!=null)
            tail=tail.getNext();
        tail.setNext(newElem);
        return true;
    }
    @Override
    public boolean add(int index, E data) {
        if(index<0) return false;
        Element newElem=new Element(data);
        if(index==0)
        {
            newElem.setNext(head);
            head=newElem;
            return true;
        }
        Element actElem=getElement(index-1);
        if(actElem==null)
            return false;
        newElem.setNext(actElem.getNext());
        actElem.setNext(newElem);
        return true;}
    @Override
    public int indexOf(E data) {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            if(actElem.getValue().equals(data))
                return pos;
            pos++;
            actElem=actElem.getNext();
        }
        return -1;}
    @Override
    public boolean contains(E data) {
        return indexOf(data)>=0;}
    @Override
    public E get(int index) {
        Element actElem=getElement(index);

        return actElem.getValue();} //return actElem==null?null:actElem.getValue();
    @Override
    public E set(int index, E data) {
        Element actElem=getElement(index);
        if(actElem==null)
            return null;
        E elemData=actElem.getValue();
        actElem.setValue(data);
        return elemData;}
    @Override
    public E remove(int index) {
        if(head==null)
            return null;
        if(index==0){
            E retValue=head.getValue();
            head=head.getNext();
            return retValue;}
        Element actElem=getElement(index-1);
        if(actElem==null || actElem.getNext()==null)
            return null;
        E retValue=actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        return retValue;}
    @Override
    public boolean remove(E value) {
        if(head==null)
            return false;
        if(head.getValue().equals(value)){
            head=head.getNext();
            return true;}
        Element actElem=head;
        while(actElem.getNext()!=null && !actElem.getNext().getValue().equals(value))
            actElem=actElem.getNext();
        if(actElem.getNext()==null)
            return false;
        actElem.setNext(actElem.getNext().getNext());
        return true;}

    public int tab[] = new int[52];
    public boolean czyWolne(int w, int k){
        if (w<=13 && w>0){
            int n =tab[(k+1)*w -1]; //wzór przyprządkowujący miejsce
            if (n==0){
                n = 1;
                return true;
            }
            else return false;
        }
        return false;
    }
    int licznik =0;
    int zakrytych=0;

public void dodawanie(){
    int wartosc = 1;

    while(wartosc !=0){
        Random random = new Random();
        wartosc = random.nextInt(15);
        Random random2 = new Random();
        int kolor = random2.nextInt(4);
        boolean zakryta = false;
        if (wartosc==14){
            zakryta = true;

        }
        Karta nowa = new Karta(wartosc, kolor, zakryta); //nowa karta
        if (isEmpty()){
            head=new Element((E) nowa);
            head.setNext(null);
            if (wartosc==14){
                zakrytych +=1;
            }
            licznik +=1;
        }
        else{

            for (int i = 0; i < size(); i++) {
                Karta ka = (Karta) get(i);
                Object o = (Object) get(i);

                int x = nowa.compareTo(o); //jeśli x jest 0 i czyWolne jest false
                if (x == 0 || !czyWolne(ka.getWartosc(), ka.getKolor())){
                    break;
                }
                if (x==1 && wartosc == 14){

                    add(i,(E)nowa);
                    licznik +=1;
                    zakrytych +=1;
                    break;
                }
                if (x==1 && czyWolne(ka.getWartosc(), ka.getKolor())){
                    add(i, (E) nowa);
                    licznik +=1;
                    if (wartosc==14){
                        zakrytych +=1;
                    }
                    break;
                }

            }
        }
    }
}
    public void wyswietl(){
        Element el = head;
        while (el != null){
            System.out.println(el.getValue().toString());
            el = el.getNext();
        }

    }
    public void ileKart(){
        System.out.println("Liczba: kart = " + licznik +", zakrytych = " + zakrytych + ", odkrytych = " + (licznik-zakrytych));
    }
    private class InnerIterator implements Iterator<E>{
        Element actElem;
        public InnerIterator() {
            actElem=head;
        }
        @Override
        public boolean hasNext() {
            return actElem!=null;
        }
        @Override
        public E next() {
            E value=actElem.getValue();
            actElem=actElem.getNext();
            return value;
        }
    }
    public void podanaWartosc(int w){
        Element el = head;
        int czy = 0;
        System.out.println("\nSzukanie kart o podanej wartości " + w + "...");
        while (el != null){
            Karta k = (Karta) el.getValue();
            if (k.getWartosc() == w){
                System.out.println(k.toString());
                czy +=1;
            }
            el = el.getNext();
        }
        if (czy == 0) System.out.println("Brak kart o podanej wartości");
    }
    public void podanyKolor(int k){
        Element el = head;
        int czy = 0;
        System.out.println("\nSzukanie kart o podanym kolorze " + k + "...");
        while(el != null){
            Karta ka = (Karta) el.getValue();
            if (ka.getKolor() == k){
                System.out.println(ka.toString());
                czy +=1;
            }
            el = el.getNext();
        }
        if (czy == 0) System.out.println("Brak kart o podanym kolorze");
    }
    public void usuwanieZakrytych(){
        System.out.println("Usuwanie zakrytych...");
        Element el = head;
        int czy = 0;
        while(el!= null){
            Karta ka = (Karta) el.getValue();
            if (ka.getWartosc() == 14){
                remove(el.getValue());
                czy +=1;
            }
            el = el.getNext();
        }
        if (czy == 0) System.out.println("Brak zakrytych kart");
    }
    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

}
