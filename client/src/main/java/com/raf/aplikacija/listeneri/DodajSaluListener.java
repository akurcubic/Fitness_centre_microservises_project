package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.view.DodajSaluView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DodajSaluListener implements ActionListener {

    private DodajSaluView dodajSaluView;

    public DodajSaluListener(DodajSaluView dodajSaluView) {
        this.dodajSaluView = dodajSaluView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(dodajSaluView.getBrojTrenera().getText().isEmpty() ||
                dodajSaluView.getPocetakRadnogVremena().getText().isEmpty() ||
                dodajSaluView.getKrajRadnogVremena().getText().isEmpty() ||
                dodajSaluView.getOpis().getText().isEmpty() ||
                dodajSaluView.getPogodnost().getText().isEmpty() ||
                dodajSaluView.getNazivSale().getText().isEmpty()){

            JOptionPane.showMessageDialog(dodajSaluView,"Sva polja moraju biti popunjena","Obavestenje",1);
            return;
        }

        int brojTrenera = Integer.parseInt(dodajSaluView.getBrojTrenera().getText());
        String pocetakRadnogVremena = dodajSaluView.getPocetakRadnogVremena().getText();
        String krajRadnogVremena = dodajSaluView.getKrajRadnogVremena().getText();
        String opis = dodajSaluView.getOpis().getText();
        int pogodnost = Integer.parseInt(dodajSaluView.getPogodnost().getText());
        String nazivSale = dodajSaluView.getNazivSale().getText();

        try {
            dodajSaluView.getAdminView().getRezervacijaServiceRestClient().dodajSalu(brojTrenera,pocetakRadnogVremena,krajRadnogVremena,opis,pogodnost,nazivSale);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
