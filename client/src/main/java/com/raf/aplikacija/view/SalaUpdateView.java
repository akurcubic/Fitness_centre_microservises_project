package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.UpdateSaluListener;
import com.raf.aplikacija.listeneri.UpdateUser;
import com.raf.aplikacija.restclient.UserServiceRestClient;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaDtoOut;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class SalaUpdateView extends JDialog {
    private JTextField brojTrenera;
    private JTextField pocetakRadnogVremena;
    private JTextField krajRadnogVremena;
    private JTextField opis;
    private JTextField pogodnost;
    private ManagerView managerView;


    public SalaUpdateView(ManagerView managerView) throws IOException {


        this.managerView = managerView;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(managerView);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

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


        popuniPolja();

        JButton changeButton = new JButton("Promeni");
        changeButton.addActionListener(new UpdateSaluListener(this));
        panel.add(changeButton);

        add(panel);
        setVisible(true);
    }

    private void popuniPolja() throws IOException {

        FiskulturnaSalaDtoOut fiskulturnaSalaDtoOut =  managerView.getRezervacijaServiceRestClient().pronadjiSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
        this.brojTrenera.setText(String.valueOf(fiskulturnaSalaDtoOut.getBrojTrenera()));
        this.pocetakRadnogVremena.setText(fiskulturnaSalaDtoOut.getPocetakRadnogVremena());
        this.krajRadnogVremena.setText(fiskulturnaSalaDtoOut.getKrajRadnogVremena());
        this.opis.setText(fiskulturnaSalaDtoOut.getOpis());
        this.pogodnost.setText(String.valueOf(fiskulturnaSalaDtoOut.getPogodnostZaVereneKlijente()));

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


    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }
}
