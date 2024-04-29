package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.view.TreningSalaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DodajTreningZaSaluListener implements ActionListener {

    private TreningSalaView treningSalaView;

    public DodajTreningZaSaluListener(TreningSalaView treningSalaView) {
        this.treningSalaView = treningSalaView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(treningSalaView.getCenaTextField().getText().isEmpty() ||
        treningSalaView.getKapacitetTextField().getText().isEmpty() ||
        treningSalaView.getTrajanjeTextField().getText().isEmpty()){

            JOptionPane.showMessageDialog(treningSalaView,"Popunite sva polja!","Obavestenje",1);
            return;
        }

        String nazivSale = ClientApplication.getInstance().getManager().getNazivFiskulturneSale();
        String trening = (String) treningSalaView.getTreningComboBox().getSelectedItem();
        int cena = Integer.parseInt(treningSalaView.getCenaTextField().getText());
        int kapacitet = Integer.parseInt(treningSalaView.getKapacitetTextField().getText());
        int trajanje = Integer.parseInt(treningSalaView.getTrajanjeTextField().getText());

        try {
            treningSalaView.getManagerView().getRezervacijaServiceRestClient().dodajTreningZaSalu(nazivSale,trening,cena,trajanje,kapacitet);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
