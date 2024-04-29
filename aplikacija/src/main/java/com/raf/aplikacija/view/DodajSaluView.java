package com.raf.aplikacija.view;


import com.raf.aplikacija.listeneri.DodajSaluListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DodajSaluView extends JDialog {

    private JTextField brojTrenera;
    private JTextField pocetakRadnogVremena;
    private JTextField krajRadnogVremena;
    private JTextField opis;
    private JTextField pogodnost;
    private JTextField nazivSale;
    private AdminView adminView;


    public DodajSaluView(AdminView adminView) throws IOException {


        this.adminView = adminView;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(adminView);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));

        JLabel nazivSaleLabel = new JLabel("Naziv sale:");
        nazivSale = new JTextField();

        JLabel brTrenera = new JLabel("Broj trenera:");
        brojTrenera = new JTextField();

        JLabel pocetakRV = new JLabel("Pocetak radnog vremena:");
        pocetakRadnogVremena = new JTextField();

        JLabel krajRV = new JLabel("Kraj radnog vremena:");
        krajRadnogVremena = new JTextField();

        JLabel opiss = new JLabel("Opis:");
        opis = new JTextField();

        JLabel pogodnostt = new JLabel("Pogodnost za verne klijente:");
        pogodnost = new JTextField();


        panel.add(nazivSaleLabel);
        panel.add(nazivSale);
        panel.add(brTrenera);
        panel.add(brojTrenera);
        panel.add(pocetakRV);
        panel.add(pocetakRadnogVremena);
        panel.add(krajRV);
        panel.add(krajRadnogVremena);
        panel.add(opiss);
        panel.add(opis);
        panel.add(pogodnostt);
        panel.add(pogodnost);


        JButton dodaj = new JButton("Dodaj");
        dodaj.addActionListener(new DodajSaluListener(this));

        panel.add(dodaj);

        add(panel);
        setVisible(true);
    }

    public JTextField getBrojTrenera() {
        return brojTrenera;
    }

    public void setBrojTrenera(JTextField brojTrenera) {
        this.brojTrenera = brojTrenera;
    }

    public JTextField getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(JTextField pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public JTextField getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(JTextField krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }

    public JTextField getOpis() {
        return opis;
    }

    public void setOpis(JTextField opis) {
        this.opis = opis;
    }

    public JTextField getPogodnost() {
        return pogodnost;
    }

    public void setPogodnost(JTextField pogodnost) {
        this.pogodnost = pogodnost;
    }

    public JTextField getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(JTextField nazivSale) {
        this.nazivSale = nazivSale;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }
}
