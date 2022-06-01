import java.util.Objects;

public class Karta implements Comparable{
    int wartosc;
    int kolor;
    boolean znacznik;

    public Karta(int wartosc, int kolor, boolean znacznik) {
        this.wartosc = wartosc;
        this.kolor = kolor;
        this.znacznik = znacznik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karta karta = (Karta) o;
        return wartosc == karta.wartosc &&
                kolor == karta.kolor &&
                znacznik == karta.znacznik;
    }

    @Override
    public String toString() {
        if(wartosc==14){
            return "()";
        }
        else {
            return "Karta{" +
                    "wartosc=" + wartosc +
                    ", kolor=" + kolor +
                    '}';
        }
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getKolor() {
        return kolor;
    }

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }

    public boolean isZnacznik() {
        return znacznik;
    }

    public void setZnacznik(boolean znacznik) {
        this.znacznik = znacznik;
    }

    @Override
    public int compareTo(Object o) {
        Karta k = (Karta) o;
        if (this.getWartosc() > k.getWartosc()) return 1;
        else if (this.getWartosc() == k.getWartosc()){
            if (this.getKolor() > k.getKolor()) return 1;
            if (this.getKolor() < k.getKolor()) return -1;
            else return 0;
        }
        return -1;
    }
}
