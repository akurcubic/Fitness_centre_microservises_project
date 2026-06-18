package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.view.DodajTerminView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DodajTerminListener implements ActionListener {


    private DodajTerminView dodajTerminView;

    public DodajTerminListener(DodajTerminView dodajTerminView) {
        this.dodajTerminView = dodajTerminView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(dodajTerminView.getDanTextField().getText().isEmpty() ||
        dodajTerminView.getDatumTextField().getText().isEmpty() ||
        dodajTerminView.getVremeOdTextField().getText().isEmpty() ||
        dodajTerminView.getVremeDoTextField().getText().isEmpty()){

            JOptionPane.showMessageDialog(dodajTerminView,"Popunite sva polja!","Obavestenje",1);
            return;
        }

        String nazivSale = ClientApplication.getInstance().getManager().getNazivFiskulturneSale();
        String trening = (String) dodajTerminView.getTreningComboBox().getSelectedItem();
        String dan = dodajTerminView.getDanTextField().getText();
        String datum = dodajTerminView.getDatumTextField().getText();
        String vremeOd = dodajTerminView.getVremeOdTextField().getText();
        String vremeDo = dodajTerminView.getVremeDoTextField().getText();

        try {
            dodajTerminView.getManagerView().getRezervacijaServiceRestClient().dodajTermin(nazivSale,trening,dan,datum,vremeOd,vremeDo);
            dodajTerminView.getManagerView().init();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
