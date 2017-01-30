
package biblioteka;

import java.util.ArrayList;


public class Pracownik extends Osoba{
    int numerKartyPracownika;
    public Pracownik(String imie, String Nazwisko)
    {
        super(imie, Nazwisko);
        
    }
     public void dodajKsiazke(ArrayList<Ksiazka> lista, Ksiazka ksiazka)
    {
    lista.add(ksiazka);
    }
     public void usunKsiazke(ArrayList<Ksiazka> lista, Ksiazka ksiazka)
     {
     lista.remove(ksiazka);
     }
     
}
