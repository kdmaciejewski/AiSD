import java.util.Scanner;

public class Main {
    //Zdefiniuj klasę Main zawierającą menu, którego opcje wykonują następujące operacje:
    //1. Utworzenie listy
    //2. Wyświetlanie listy (karty zakryte drukuj jako parę nawiasów „( )”
    //3. Wyświetlanie liczby elementów listy oraz ile jest kart zakrytych i ile odkrytych
    //4. Wyświetlanie kart o podanej wartości
    //5. Wyświetlanie kart o podanym kolorze
    //6. Usuwanie kart zakrytych
    public static void main(String[] args) {
        Main m = new Main();
        m.menu();

//        OneWayLinkedListWithHead<Karta> o = new OneWayLinkedListWithHead<>();
//        o.dodawanie();
//        o.wyswietl();
//        o.ileKart();
//        o.podanaWartosc(12);
//        o.podanyKolor(1);
//        o.usuwanieZakrytych();
//        o.wyswietl();
    }
    public void menu(){
        OneWayLinkedListWithHead<Karta> o = new OneWayLinkedListWithHead<>();


        int numer = 20;
        while(numer!=0){

        System.out.println("\n0. Zakończ\n1. Utworzenie listy\n2. Wyświetlanie listy\n3. Wyświetlanie liczby elementów listy oraz ile jest kart zakrytych i ile odkrytych" +
                "\n4. Wyświetlanie kart o podanej wartości\n5. Wyświetlanie kart o podanym kolorze\n6. Usuwanie kart zakrytych");
        Scanner scan = new Scanner(System.in);
        numer = scan.nextInt();
            if (numer == 0){
                break;
            }
            if (numer==1){
                o.dodawanie();
            }
            if (numer==2){
                o.wyswietl();
            }
            if (numer==3){
                o.ileKart();
            }
            if (numer==4){
                o.podanaWartosc(12);
            }
            if (numer==5){
                o.podanyKolor(1);
            }
            if (numer==6){
                o.usuwanieZakrytych();
            }

        }
    }
}
