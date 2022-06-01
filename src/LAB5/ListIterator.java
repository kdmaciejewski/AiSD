import java.util.Iterator;

public class ListIterator<E> implements Iterator<E> {
    public void add(E e) // dodanie e w bieżącej pozycji, ZA kursor
    {

    }
    public boolean hasNext() {
        return false;
    }

    boolean hasPrevious()// jak hasNext, ale w przeciwnym kierunku
    {
        return false;
    }

    public E next() {
        return null;
    }

    public int nextIndex() // indeks elementu, który byłby zwrócony przez next()
    {
        return 0;
    }

    public E previous() //jak next(), ale w przeciwnym kierunku
    {
        return null;
    }

    public int previousIndex() //jak nextIndex(), ale w przeciwnym kierunku
    {
        return 0;
    }

    public void remove() // usuwa ostatnio zwrócony element przez next() lub previous()
    {
    }
    public void set(E e)// wstawia wartość e do kolekcji pod ostatnio zwrócony element
    {

    }
}
