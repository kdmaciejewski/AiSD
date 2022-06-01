public class Test {
    public static void main(String[] args) {


        Tree t = new Tree();

        String onp = t.convertONP("((4+3)-(2+1)*2+3)/2");
        t.buduj();
        System.out.println("Postać ONP (postorder): " + onp);
        t.kalkulator();

        System.out.println(t.ileLisci());
        System.out.println(t.ileWezlow());
        System.out.println(t.jakaWysokosc());
        System.out.println("Przechodzenie wszerz: ");
        t.goAcross();


    }
}
