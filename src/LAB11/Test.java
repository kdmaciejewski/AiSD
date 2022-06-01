public class Test {
    public static void main(String [] args){
        RedBlackTree r = new RedBlackTree();

        r.odczytajPlik("plik.txt");
        r.utworzDrzewo();

        r.inorder();
        r.deleteNode("pogodzie");
        System.out.println("\nPo usunięciu: \n");
        r.inorder();
        System.out.println();
        r.goAcross();


        //System.out.println(r.lista);
        //System.out.println(r.slowa);



    }
}
