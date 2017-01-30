package biblioteka;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Biblioteka {

    String nazwa;
    Date dataZalozenia;
    int iloscKsiazek;
    public static final int BUFFOR=1024;
    byte[] tempData;

    Biblioteka(String nazwa, int rok, int miesiac, int dzien) {
        this.nazwa = nazwa;
        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiac - 1, dzien);
        this.dataZalozenia = kalendarz.getTime();
    }

    public static ArrayList<Ksiazka> listaWypozyczonych = new ArrayList();
    public static ArrayList<Ksiazka> ListaKsiazek = new ArrayList();
    public static HashMap<Integer, Czytelnik> listaCzytelnikow=new HashMap();
    
    public void dodajCzytelnika(Czytelnik czytelnik, int idCzytelnika)
    {
    listaCzytelnikow.put(idCzytelnika, czytelnik);
    }
    public void usunCzytelnika(int idCzytelnika)
    {
    listaCzytelnikow.remove(idCzytelnika);
    }
    public void wyswietlWszystkichCzytelnikow()
    {
    for(int s: listaCzytelnikow.keySet())
    {
    System.out.println("ID: "+ s+ " | "  +  listaCzytelnikow.get(s).toString());
    }
    }

    public void listaWypozyczonych() {
        Iterator it = listaWypozyczonych.iterator();
        while (it.hasNext()) {
            System.out.println(((Ksiazka) it.next()).toString());
        }
    }

   
    public int ileJestKsiazek() {
        this.iloscKsiazek = ListaKsiazek.size();
        return iloscKsiazek;
    }
    
    public void wypiszWszystkieKsiazkiDoPliku()
    {
    File listaKsiazek=new File("Lista_Ksiazek.txt");
       
    try {
            
            BufferedWriter pw= new BufferedWriter(new FileWriter(listaKsiazek));
            for(int i=0; i<ListaKsiazek.size(); i++)
            {
                pw.write(ListaKsiazek.get(i).getNazwa()+ "\n"+ ListaKsiazek.get(i).getImie()+ " " + ListaKsiazek.get(i).getNazwisko()+ "\n" + ListaKsiazek.get(i).getID()+ "\n"+ "\n");
            }
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

   

}
