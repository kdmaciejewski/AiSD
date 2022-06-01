import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

public class OneWayLinkedListWithHeadandTail<E> extends AbstractList<E>{
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
    Element tail = null;
    public OneWayLinkedListWithHeadandTail(){}

    public boolean isEmpty(){
        return head==null;}

    public void clear() {
        head=null;
        tail=null;
    }

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
            tail = newElem;

            return true;
        }

        tail.setNext(newElem);  //zamiana
        tail=newElem;
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
        if(index == size()-1){
            Element e = new Element(data);
            add((E) e);
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
        if(index == size()-1){
            Element e = getElement(size()-2);
            e.setNext(null);
            tail = e;
        }
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
        while(actElem.getNext()!=null && !actElem.getNext().getValue().equals(value)){
            actElem=actElem.getNext();
        }
        if (indexOf((E) actElem.getNext()) == size()-1) {
            actElem.setNext(null);
            tail = actElem;
        }
        if(actElem.getNext()==null)
            return false;
        actElem.setNext(actElem.getNext().getNext()); //to jest dobrze bo pętla wyrzuca gdy getnext to null
        return true;}

    public void wyswietl(){
        Element el = head;
        while (el != null){
            System.out.println(el.getValue().toString());
            el = el.getNext();
        }
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

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

}
