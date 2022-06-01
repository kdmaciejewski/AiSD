public class Zamowienie {
    String nazwa;
    int liczba;
    int cena;

    public Zamowienie(String nazwa, int liczba, int cena) {
        this.nazwa = nazwa;
        this.liczba = liczba;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }


}
