package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.view.TipTreningaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TipTreningaListener implements ActionListener {

    private TipTreningaView tipTreningaView;

    public TipTreningaListener(TipTreningaView tipTreningaView) {
        this.tipTreningaView = tipTreningaView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(tipTreningaView.getTipTreninga().getText().isEmpty()){

            JOptionPane.showMessageDialog(tipTreningaView,"Popunite polje","Obavestenje",1);
            return;
        }
        String tipTreninga = tipTreningaView.getTipTreninga().getText();
        try {
            tipTreningaView.getManagerView().getRezervacijaServiceRestClient().dodajTipTreninga(tipTreninga);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
