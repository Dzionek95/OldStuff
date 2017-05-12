package ziper;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import static javafx.application.Platform.exit;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Ziper extends JFrame implements ActionListener {

    private String wybor;
    private static final int BUFFOR = 1024;
    private byte[] tempData = new byte[BUFFOR];
    private JList listaPliki;
    private JScrollPane skrol;
    private JButton dodajPlik, usunPlik, zipuj, unzipuj, gzipuj, ungzipuj;
    private JMenuBar pasekMenu;
    private JMenu menuPlik, menuZmianaWygladu, menuPomoc;
    private JMenuItem itemDodaj, itemUsun, itemWyjdz, itemOProgramie, itemDomyslny, itemWindows, itemNimbus;
    private DefaultListModel modelListy;
    private ImageIcon informacja = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\kkk.png");

    Ziper() {
        setTitle("Ziper");
        setSize(450, 350);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Menu
        pasekMenu = new JMenuBar();
        setJMenuBar(pasekMenu);

        menuPlik = new JMenu("Plik");
        menuZmianaWygladu = new JMenu("Zmień wygląd");
        menuPomoc = new JMenu("Pomoc");

        pasekMenu.add(menuPlik);
        pasekMenu.add(menuZmianaWygladu);
        pasekMenu.add(Box.createHorizontalGlue());
        pasekMenu.add(menuPomoc);
        //menu
        itemDodaj = new JMenuItem("Dodaj", 'D');
        itemDodaj.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        itemDodaj.addActionListener(this);
        itemUsun = new JMenuItem("Usuń", 'U');
        itemUsun.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
        itemUsun.addActionListener(this);
        itemWyjdz = new JMenuItem("Wyjście", 'W');
        itemWyjdz.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
        itemWyjdz.addActionListener(this);
        itemOProgramie = new JMenuItem("O programie", 'O');
        itemDomyslny = new JMenuItem("Domyślny", 'D');
        itemWindows = new JMenuItem("Windows", 'W');
        itemNimbus = new JMenuItem("Nimbus", 'N');

        menuPlik.add(itemDodaj);
        menuPlik.addSeparator();
        menuPlik.add(itemUsun);
        menuPlik.addSeparator();
        menuPlik.add(itemWyjdz);

        menuZmianaWygladu.add(itemDomyslny);
        itemDomyslny.addActionListener(this);
        menuZmianaWygladu.addSeparator();
        menuZmianaWygladu.add(itemWindows);
        itemWindows.addActionListener(this);
        menuZmianaWygladu.addSeparator();
        menuZmianaWygladu.add(itemNimbus);
        itemNimbus.addActionListener(this);

        menuPomoc.add(itemOProgramie);
        itemOProgramie.addActionListener(this);

        //Przyciski
        ImageIcon dodaj = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\1457839250_save.png");
        ImageIcon usun = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\1457839288_free-27.png");
        ImageIcon zip = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\aaa.png");
        ImageIcon gzip = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\gzip.png");
        ImageIcon ungzip = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\ungzip.png");
        ImageIcon unzip = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\unzip.png");
        dodajPlik = new JButton(" DODAJ", dodaj);
        //dodajPlik.setBounds(250, 30, 110, 24);
        dodajPlik.addActionListener(this);
        usunPlik = new JButton("   USUŃ ", usun);
        //usunPlik.setBounds(250, 80, 110, 24);
        usunPlik.addActionListener(this);
        zipuj = new JButton("  ZIP ", zip);
        //zipuj.setBounds(250, 185, 110, 24);
        zipuj.addActionListener(this);
        gzipuj = new JButton("GZIP", gzip);
        ungzipuj = new JButton("UnGZIP", ungzip);
        ungzipuj.addActionListener(this);
        gzipuj.addActionListener(this);

        unzipuj = new JButton("  UnZIP ", unzip);
        unzipuj.addActionListener(this);
        add(dodajPlik);
        add(usunPlik);
        add(zipuj);
        add(gzipuj);

        add(unzipuj);
        add(ungzipuj);
        //Lista
        modelListy = new DefaultListModel();
        listaPliki = new JList(modelListy);
        listaPliki.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listaPliki.setLayoutOrientation(JList.VERTICAL);
        listaPliki.setToolTipText("Wybrane pliki");
        skrol = new JScrollPane(listaPliki);
        //skrol.setBounds(10, 10, 215 , 200);
        add(skrol);
        //ustawienie layoutu
        GroupLayout layout = new GroupLayout(this.getContentPane());

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(skrol, 100, 150, Short.MAX_VALUE).
                addGap(40).
                addContainerGap(0, Short.MAX_VALUE).
                addGroup(
                        layout.createParallelGroup()
                        .addGap(40)
                        .addComponent(unzipuj)
                        .addComponent(ungzipuj)).
                addGroup(
                        layout.createParallelGroup().
                        addComponent(dodajPlik).
                        addComponent(usunPlik).
                        addComponent(zipuj).
                        addComponent(gzipuj)
                ));
        layout.setVerticalGroup(
                layout.createParallelGroup().
                addComponent(skrol, 100, 150, Short.MAX_VALUE).
                addGroup(
                        layout.createSequentialGroup().
                        addGap(190).
                        addComponent(unzipuj).
                        addComponent(ungzipuj)).
                addGroup(
                        layout.createSequentialGroup().
                        addComponent(dodajPlik).
                        addComponent(usunPlik).
                        addGap(40, 80, Short.MAX_VALUE).
                        addComponent(zipuj).
                        addComponent(gzipuj)
                ));
        setLayout(layout);
    }

    //sluchacz przyciskow

    @Override
    public void actionPerformed(ActionEvent e) {

        Object zrodlo = e.getSource();
        //obsluga buttonow strumieni
        if (zrodlo == ungzipuj) {
            unGzipuj();
        } else if (zrodlo == gzipuj) {
            GZIPOutputStream gOS = null;
            try {
                gOS = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("wyjsciowyGZIP.tar.gz")));
                for (int a = 0; a < modelListy.size(); a++) {
                    if (!((File) modelListy.get(a)).isDirectory()) {
                        gzipuj(gOS, (File) modelListy.get(a), tempData);
                    } else {
                        gzipujKatalogi(gOS, (File) modelListy.get(a));
                    }
                }
            } catch (IOException ex) {
              JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    gOS.close();
                } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (zrodlo == zipuj) {

            try {

                ZipOutputStream zOS = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("wyjscie.zip")));
                for (int i = 0; i < modelListy.size(); i++) {
                    if (!((File) modelListy.get(i)).isDirectory()) {
                        zipuj(zOS, (File) modelListy.get(i), tempData);
                    } else {
                        zipujKatalog(zOS, (File) modelListy.get(i));
                    }


                }
                modelListy.removeAllElements();
                JOptionPane.showMessageDialog(null, "Kompresja ZIP zakończona Pomyślnie", "Zakończono", JOptionPane.OK_OPTION, informacja);
                zOS.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        } else if (zrodlo == unzipuj) {
            File katalog = new File(System.getProperty("") + File.separator + "UnZip");

            if (!katalog.exists()) {
                katalog.mkdir();
            }

            try {
                for (int i = 0; i < modelListy.size(); i++) {
                    if (!katalog.exists()) {
                        katalog.mkdir();
                    }

                    ZipInputStream zInS = new ZipInputStream(new BufferedInputStream(new FileInputStream(((File) modelListy.get(i)).getPath()), BUFFOR));
                    ZipEntry ze = null;

                    while ((ze = zInS.getNextEntry()) != null) {
                        BufferedOutputStream fOutS = new BufferedOutputStream(new FileOutputStream(katalog + File.separator + ze.getName()), BUFFOR);

                        int counter;

                        while ((counter = zInS.read(tempData, 0, BUFFOR)) != -1) {
                            fOutS.write(tempData, 0, counter);
                        }

                        fOutS.close();
                        zInS.closeEntry();
                    }

                    zInS.close();
                    modelListy.removeAllElements();
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } //obsluga buttonow uzytkowych tj. dodawanie etc
        else if (zrodlo == dodajPlik || zrodlo == itemDodaj) {

            JFileChooser jfc = new JFileChooser(System.getProperty("user.home"));
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.setApproveButtonText("Wybierz");
            jfc.setMultiSelectionEnabled(true);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                File[] pliki;
                pliki = jfc.getSelectedFiles();

                for (int i = 0; i < pliki.length; i++) {

                    modelListy.addElement(pliki[i]);

                }
            }
        } else if (zrodlo == usunPlik || zrodlo == itemUsun) {
            int pozycja = listaPliki.getSelectedIndex();
            modelListy.remove(pozycja);

        } else if (zrodlo == itemWyjdz) {
            dispose();
        } else if (zrodlo == itemOProgramie) {
            JOptionPane.showMessageDialog(null, "Autor: Bartłomiej Janik\n Wersja: 1.0\n Program pakujący pliki do formatu ZIP", "O Programie", JOptionPane.OK_OPTION, informacja);
        } else if (zrodlo == itemDomyslny) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            SwingUtilities.updateComponentTreeUI(this);

        } else if (zrodlo == itemWindows) {

            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            SwingUtilities.updateComponentTreeUI(this);
        } else if (zrodlo == itemNimbus) {

            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            SwingUtilities.updateComponentTreeUI(this);
        }

    }
    //koniec sluchacza przyciskow

    public static void main(String[] args) {
        Ziper app = new Ziper();

        if (app.wyborSystemu() == false) {
            app.dispose();
        } else {
            app.setVisible(true);
        }
    }

// metody zwiazane ze strumieniami~ metody zwiazane z GZIP/UNGZIP wstepnie napisane, lecz nie wiem czy do końca poprawnie działają

    public void zipuj(ZipOutputStream zOS, File sciezkaPliku, byte[] tempData) throws IOException {
        BufferedInputStream fIS = new BufferedInputStream(new FileInputStream(sciezkaPliku), BUFFOR);

        zOS.putNextEntry(new ZipEntry(sciezkaPliku.getName()));
        int counter;
        while ((counter = fIS.read(tempData, 0, BUFFOR)) != -1) {
            zOS.write(tempData, 0, counter);
        }

        zOS.closeEntry();
        fIS.close();
    }

    public void zipujKatalog(ZipOutputStream zOS, File p) throws IOException {
        String[] listaSciezek = p.list();
        for (int a = 0; a < listaSciezek.length; a++) {
            File plik = new File(p.getPath(), listaSciezek[a]);
            if (!plik.isDirectory()) {
                zipuj(zOS, plik, tempData);
            } else {
                zipujKatalog(zOS, plik);
            }
        }
    }

    public void gzipuj(GZIPOutputStream gOS, File plik, byte[] tempData) throws FileNotFoundException, IOException {
        BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(plik.getPath()), BUFFOR);
        ZipEntry ze = new ZipEntry(plik.getName());
        int licznik = 0;
        while ((licznik = bIS.read(tempData, 0, BUFFOR)) != -1) {
            gOS.write(tempData, 0, licznik);
        }

        bIS.close();
    }

    public void gzipujKatalogi(GZIPOutputStream gOS, File plik) throws IOException {
        String[] sciezki = plik.list();
        for (int i = 0; i < sciezki.length; i++) {
            File p = new File(plik.getPath(), sciezki[i]);
            if (!p.isDirectory()) {
                gzipuj(gOS, p, tempData);
            } else {
                gzipujKatalogi(gOS, p);
            }
        }
    }

    public void unGzipuj() {
        for (int a = 0; a < modelListy.size(); a++) {
            try {
                File katalog = new File("wyjsciowy");
                if (!katalog.exists()) {
                    katalog.mkdir();
                }
                GZIPInputStream gIS = new GZIPInputStream(new FileInputStream(((File) modelListy.get(a))), BUFFOR);
                InputStreamReader czytacz = new InputStreamReader(gIS);
                BufferedReader buforowany = new BufferedReader(czytacz);
                BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(katalog + File.separator), BUFFOR);
                String ze;
                while ((ze = buforowany.readLine()) != null) {
                    bOS.write(tempData, 0, BUFFOR);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Napotkano błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
//metoda dotycznaca wyboru i odpowiedniego dobrania przyciskow do wybranej opcji

    public boolean wyborSystemu() {

        ImageIcon zapytanie = new ImageIcon("C:\\Users\\Bartek\\Documents\\NetBeansProjects\\Ziper\\pytanie.png");
        String[] rozszerzenia = {"Zip/UnZip", "Gzip/UnGzip"};
        String wybor = (String) JOptionPane.showInputDialog(null, "Chczesz pracować z rozszerzeniem .zip czy .gzip?", "Wybór rozszerzenia", JOptionPane.OK_OPTION, zapytanie, rozszerzenia, rozszerzenia[0]);
        if (wybor == null) {
            JOptionPane.showMessageDialog(null, "Nie wybrano zadnego typu program zostanie wyłączony", "Brak wyboru", JOptionPane.OK_OPTION, informacja);
            return false;
        } else;
        if (wybor.equals("Zip/UnZip")) {
            gzipuj.setEnabled(false);
            ungzipuj.setEnabled(false);
            return true;
        } else if (wybor.equals("Gzip/UnGzip")) {
            zipuj.setEnabled(false);
            unzipuj.setEnabled(false);
            return true;
        }
        return false;
    }

}
