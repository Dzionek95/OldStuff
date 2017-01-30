package biblioteka;

import java.util.ArrayList;


public class Czytelnik extends Osoba {

    Thread t;
    Ksiazka ksiazka;
    

    public Czytelnik(String imie, String nazwisko) {
        super(imie, nazwisko);
     
    }
   

  public String toString()
 {
 return "Imie: " + getImie() + " | "+ " Nazwisko: " + getNazwisko(); 
 }
}
