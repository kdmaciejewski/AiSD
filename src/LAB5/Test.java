import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, FullStackException {
        Test test = new Test();
        ListStack<String> s = new ListStack<>();
        //s.sprzedaj("Audi");
        TwoWayCycledListWithSentinel two = new TwoWayCycledListWithSentinel();
        test.menu();

    }
    public void menu(){
        TwoWayCycledListWithSentinel z = new TwoWayCycledListWithSentinel();
        int numer = 20;
        while(numer!=0){

            System.out.println("0. Zakończ\n1. Utworzenie nowej bazy danych\n2. Odczyt bazy z pliku\n3. Wyświetlenie wszystkich rekordów" +
                    "\n4. Wyświetlenie danych jednego samochodu\n5. Dopisanie nowego samochodu\n6. Usunięcie samochodu z bazy\n7. Aktualizowanie danych samochodu\n" +
                    "8. Wyświetlanie danych samochodów o podanej marce\n" +
                    "9. Wyświetlenie danych samochodów, wyprodukowanych w roku m \n" +
                    "10. Wyświetlenie danych samochodów o cenach poniżej x \n" +
                    "11. Zapis bazy do pliku");
            Scanner scan = new Scanner(System.in);
            numer = scan.nextInt();
            if (numer == 0){
                break;
            }
            if (numer==1){
                z.nowaBaza();
            }
            if (numer==2){
                z.odczytBazy();
            }
            if (numer==3){
                z.wyswietl();
            }
            if (numer==4){
                z.daneJednego();
            }
            if (numer==5){
                z.dodaj();
            }
            if (numer==6){
               z.usun();
            }
            if (numer==7){
                z.aktualizacja();
            }
            if (numer==8){
                z.podanaMarka();
            }
            if (numer==9){
                z.wRoku();
            }
            if (numer==10){
                z.cenaPonizej();
            }
            if (numer==11){
                z.zapisBazy();
            }


    }
}
}
