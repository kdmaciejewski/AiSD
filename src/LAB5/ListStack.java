import javax.sound.sampled.AudioFileFormat;
import java.util.EmptyStackException;

public class ListStack<E> {//implements IList<E>
    IList<E> _list;
    IList<E> pomoc;
    public ListStack(){
        _list = new OneWayLinkedListWithHead<E>();
        pomoc = new OneWayLinkedListWithHead<E>();}

    public boolean isEmpty() {
        return _list.isEmpty();}

    public boolean isFull() {
        return false;}

    public E pop() throws EmptyStackException {
        E value=_list.remove(0);
        if(value==null) throw new EmptyStackException();
        return value;}

    public void push(E elem) throws FullStackException {
        _list.add(0,elem);}

    public int size() {
        return _list.size();}

    public E top() throws EmptyStackException {
        E value=_list.get(0);
        if(value==null) throw new EmptyStackException();
        return value;}
    public void rozpocznij() throws FullStackException {
        push((E) "Ford");
        push((E) "Audi");
        push((E) "Maserati");
        push((E) "Skoda"); //ten jest najbliżej wyjścia
    }
    public void sprzedaj(String nazwa) throws FullStackException {
    ListStack<String> l = new ListStack<>();
        rozpocznij();
        System.out.println("Samochody, które muszą wyjechać i wjechać:");
        for (int i = 0; i < size(); i++) {
           if (top().equals(nazwa)){
               pop();
               for (int j = 0; j < l.size(); j++) {
                   push((E) l.pop());
               }
               return;
           }
            String g = (String) pop();
            l.push(g);
            System.out.println(g);
        }
    }
//    public void sprzedaj(String nazwa) throws FullStackException {
//
//        rozpocznij();
//        System.out.println("Samochody, które muszą wyjechać i wjechać:");
//        for (int i = 0; i < size(); i++) {
//            if (top().equals(nazwa)){
//                return;
//            }
//            System.out.println(pop().toString());
//        }
//    }
}
