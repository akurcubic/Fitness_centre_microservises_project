package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.view.ClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ZakaziListener implements ActionListener {

    private ClientView clientView;

    public ZakaziListener(ClientView clientView) {
        this.clientView = clientView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedRowIndex = clientView.getTable().getSelectedRow();

        JTable table = clientView.getTable();

        if (selectedRowIndex != -1) { // Provera da li je neki red selektovan
            // Dobijanje vrednosti iz selektiranog reda za svaku kolonu
            Long selectedID = (Long) table.getValueAt(selectedRowIndex, 0);
            boolean dostupan = (boolean) table.getValueAt(selectedRowIndex, 10);
            int kapacitet = (int) table.getValueAt(selectedRowIndex, 9);

            if(!dostupan){

                JOptionPane.showMessageDialog(clientView,"Termin nije dostupan","Nije dostupan",1);
            }

            if(kapacitet <= 0){

                JOptionPane.showMessageDialog(clientView,"Termin je zauzet","Nije dostupan",1);

            }

            System.out.println(selectedID);

            try {
                clientView.getRezervacijaServiceRestClient().zakaziTermin(selectedID, ClientApplication.getInstance().getClient().getId());
                clientView.init();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(clientView,"Morate da selektujete termin!","Zakazivanje",1);
        }
    }
}
