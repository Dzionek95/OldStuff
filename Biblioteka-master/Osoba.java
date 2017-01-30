package biblioteka;

import java.util.ArrayList;

public abstract class Osoba
{
    private String imie;
    private String nazwisko;
    Thread t;
    Osoba(String imie, String nazwisko)
    {
    this.imie=imie;
    this.nazwisko=nazwisko;
    }
    
    public void setImie(String imie)
    {
    this.imie=imie;
    }
    public String getImie()
    {
    return imie;
    }
    public void setNazwisko(String nazwisko)
    {
    this.nazwisko=nazwisko;
    }
    public String getNazwisko()
    {
    return nazwisko;
    }
    
        public void ZwrocKsiazke(ArrayList<Ksiazka> lista,Ksiazka ksiazka) {
        synchronized(ksiazka){
        lista.add(ksiazka);
        ksiazka.setDostepnosc(true);
              
        ksiazka.notify();
        Biblioteka.listaWypozyczonych.remove(ksiazka);
        
        System.out.println("Zwrocono ksiazke pomyslne");
        }
    }

    private void WypozyczKsiazke(ArrayList<Ksiazka> lista, Ksiazka ksiazka) {
        synchronized (ksiazka) {
            if (!lista.isEmpty()) {
                while (!ksiazka.getDostepnosc()) {
                    try {
                        System.out.println("Nazwa watku przy czekaniu: " +t.getName());
                        ksiazka.wait();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                    lista.remove(ksiazka);
                    ksiazka.setDostepnosc(false);
                    Biblioteka.listaWypozyczonych.add(ksiazka);
                    System.out.println("Nazwa watku przy wypozyczaniu: " +t.getName());
                    System.out.println("Wypozyczono ksiazke");
                    Ksiazka.naliczKolejneWypozyczenie();
                

            }
        }
    }
public void ObslugaWielowatkowaOsobyWypozyczajacej(ArrayList<Ksiazka> lista, Ksiazka ksiazka) {

        t=new Thread(new Runnable() {

            @Override
            public void run() {
                WypozyczKsiazke(lista, ksiazka);
            }

        });
        
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
