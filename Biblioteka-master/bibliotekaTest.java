
package biblioteka;






public class bibliotekaTest {
    public static void main(String[] args)
    {
    Ksiazka k1=new Ksiazka("W pustyni i w puszczy", "Henryk", "Sienkiewicz");
    Ksiazka a1=new Ksiazka("Pan Samochodzik", " Zbigniew", "Nienacki");
    Ksiazka b1=new Ksiazka("Krzyżacy", "Henryk", "sienkiewicz");
    Ksiazka k2=new Ksiazka("Akademia pana Kleksa", "Jan", "Brzechwa");
    
    Biblioteka.ListaKsiazek.add(k1);
    Biblioteka.ListaKsiazek.add(a1);
    Biblioteka.ListaKsiazek.add(b1);
    Biblioteka.ListaKsiazek.add(k2);
    
        
    
   
    
    Biblioteka BJ=new Biblioteka("Biblioteka Jagiellońska", 2016,12,31);
    Czytelnik bartek=new Czytelnik("Bartłomiej", "Janik");
    Czytelnik ola=new Czytelnik("Ola", "Maka");
   bartek.ObslugaWielowatkowaOsobyWypozyczajacej(Biblioteka.ListaKsiazek, a1);
   bartek.ZwrocKsiazke(Biblioteka.ListaKsiazek, a1);
   ola.ObslugaWielowatkowaOsobyWypozyczajacej(Biblioteka.ListaKsiazek, a1);
   
  
    
    
    
    }
}
