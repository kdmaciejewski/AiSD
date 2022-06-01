import java.io.Serializable;

public class Auto implements Comparable, Serializable {
    int nr_silnika;
    String marka;
    String typ;
    int data;
    int cena;
    String kolor;
    int skladowanie;
    int przecena;

    public Auto(int nr_silnika, String marka, String typ, int data, int cena, String kolor, int skladowanie) {
        this.nr_silnika = nr_silnika;
        this.marka = marka;
        this.typ = typ;
        this.data = data;
        this.cena = cena;
        this.kolor = kolor;
        this.skladowanie = skladowanie;
        if (6 <= skladowanie && skladowanie <15){
            this.przecena = 6;
            this.cena = (int) (cena*0.94);
        }
        else if(skladowanie>=15){
            this.przecena = 15;
            this.cena = (int) (cena*0.85);
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Auto auto = (Auto) o;
        return marka == auto.marka;                                //czy dawać tu marka?
    }
    @Override
    public int compareTo(Object o) {
        Auto k = (Auto) o;
        if (this.getNr_silnika() <= k.getNr_silnika()) return 1;

        return -1;
    }
    @Override
    public String toString() {
        return "Auto{" +
                "nr_silnika=" + nr_silnika +
                ", marka=" + marka +
                ", typ=" + typ  +
                ", data=" + data +
                ", cena=" + cena +
                ", kolor=" + kolor  +
                ", przecena=" + przecena +
                '}';
    }

    public int getNr_silnika() {
        return nr_silnika;
    }

    public void setNr_silnika(int nr_silnika) {
        this.nr_silnika = nr_silnika;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public int getPrzecena() {
        return przecena;
    }

    public void setPrzecena(int przecena) {
        this.przecena = przecena;
    }



}
