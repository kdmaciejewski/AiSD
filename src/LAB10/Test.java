public class Test {
    public static void main(String[] args) {
        Algorytm a = new Algorytm();

        a.oczytajPlik("tekst.txt");
        //a.oczytajPlik("tekst2.txt");
        a.wyswietl();
        a.start();
        System.out.println("Wyświetlanie zaszyfrowanej wiadomości: ");
        a.wyswietlSzyfr();
        a.zapis();
        a.odszyfrowywanie();




    }
}
