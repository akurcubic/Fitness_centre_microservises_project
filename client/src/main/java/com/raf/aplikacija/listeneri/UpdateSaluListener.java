package com.raf.aplikacija.listeneri;


import com.raf.aplikacija.view.SalaUpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateSaluListener implements ActionListener {

    private SalaUpdateView salaUpdateView;

    public UpdateSaluListener(SalaUpdateView salaUpdateView) {
        this.salaUpdateView = salaUpdateView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(salaUpdateView.getBrojTrenera().getText().isEmpty() ||
        salaUpdateView.getPocetakRadnogVremena().getText().isEmpty() ||
        salaUpdateView.getKrajRadnogVremena().getText().isEmpty() ||
        salaUpdateView.getOpis().getText().isEmpty() ||
        salaUpdateView.getPogodnost().getText().isEmpty()){

            JOptionPane.showMessageDialog(salaUpdateView,"Sva polja moraju biti popunjena","Obavestenje",1);
            return;
        }

        int brojTrenera = Integer.parseInt(salaUpdateView.getBrojTrenera().getText());
        String pocetakRadnogVremena = salaUpdateView.getPocetakRadnogVremena().getText();
        String krajRadnogVremena = salaUpdateView.getKrajRadnogVremena().getText();
        String opis = salaUpdateView.getOpis().getText();
        int pogodnost = Integer.parseInt(salaUpdateView.getPogodnost().getText());

        try {
            salaUpdateView.getManagerView().getRezervacijaServiceRestClient().updateSala(brojTrenera,pocetakRadnogVremena,krajRadnogVremena,opis,pogodnost);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
