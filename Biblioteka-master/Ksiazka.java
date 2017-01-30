package biblioteka;

import java.util.ArrayList;

public class Ksiazka {

    private String imie;
    private String nazwisko;
    private int id;
    private static int licznik = 1;
    private String nazwa;
    private boolean dostepna = true;
    private static int liczbaWypozyczen;

    Ksiazka(String nazwa, String imie, String nazwisko) {
        this.nazwa = nazwa;
        this.imie = imie;
        this.nazwisko = nazwisko;

        id = licznik;
        licznik++;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getInicjaly() {
        return imie.charAt(0) + "." + nazwisko.charAt(0);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getID() {
        return id;
    }

    public boolean getDostepnosc() {
        return dostepna;
    }

    public void setDostepnosc(Boolean dostepnosc) {
        this.dostepna = dostepnosc;
    }
    public String czyDostepna()
    {
    if(getDostepnosc()==true)
        return "tak";
    else 
        return "nie";
    }
    public static boolean czySaJeszczeKsiazkiTegoTytułu(ArrayList<Ksiazka> lista) {
        if (!lista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public String skroconeInformacjeOKsiazce()
    {
    return "Inicjały: "+ getInicjaly()+" | " + " Tytuł" + getNazwa()+" | " + "ID: " + getID()+" | "+" Czy można ją teraz wypożyczyć? : " + czyDostepna();
    }
    public static void naliczKolejneWypozyczenie() {
        Ksiazka.liczbaWypozyczen++;
    }

    public int iloscWypozyczen() {
        return liczbaWypozyczen;
    }

    public String toString() {
        return "Imie autora: " + getImie() + " | " + " Nazwisko autora: " + getNazwisko() + " | " + " Tytuł: " + getNazwa() + " | " + " ID: " + getID();
    }

}
