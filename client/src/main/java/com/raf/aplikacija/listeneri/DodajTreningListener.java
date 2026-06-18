package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.view.TreningView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DodajTreningListener implements ActionListener {

    private TreningView treningView;

    public DodajTreningListener(TreningView treningView) {
        this.treningView = treningView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(treningView.getTxtNazivTreninga().getText().isEmpty()){

            JOptionPane.showMessageDialog(treningView,"Popunite naziv treninga","Obavestenje",1);
            return;
        }

        String trening = treningView.getTxtNazivTreninga().getText();
        String tip = (String) treningView.getCbTipTreninga().getSelectedItem();

        try {
            treningView.getManagerView().getRezervacijaServiceRestClient().dodajTrening(trening,tip);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
