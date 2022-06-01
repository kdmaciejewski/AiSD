public class Test {
    public static void main(String[] args) throws FullQueueException, EmptyQueueException {
        Magazyn m = new Magazyn();
       m.start();
        m.pracuj();
        Firma f = new Firma();
//        f.start();
//        f.pracuj();

    }
}
