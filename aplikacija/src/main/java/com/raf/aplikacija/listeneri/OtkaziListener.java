package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.view.ClientView;
import com.raf.aplikacija.view.ManagerView;
import com.raf.aplikacija.view.RezervacijeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OtkaziListener implements ActionListener {

    private RezervacijeView rezervacijeView;
    private ClientView clientView;
    private ManagerView managerView;

    public OtkaziListener(RezervacijeView rezervacijeView, ClientView clientView) {
        this.rezervacijeView = rezervacijeView;
        this.clientView = clientView;
    }

    public OtkaziListener(RezervacijeView rezervacijeView, ManagerView managerView){

        this.rezervacijeView = rezervacijeView;
        this.managerView = managerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedRowIndex = rezervacijeView.getTable().getSelectedRow();

        JTable table = rezervacijeView.getTable();

        if (selectedRowIndex != -1) {

            Long rezervacijaId = (Long) table.getValueAt(selectedRowIndex, 0);


            try {
                if(clientView != null)
                    rezervacijeView.getRezervacijaServiceRestClient().otkaziRezervaciju(rezervacijaId, ClientApplication.getInstance().getClient().getId());
                else
                    rezervacijeView.getRezervacijaServiceRestClient().otkaziRezervaciju(rezervacijaId, ClientApplication.getInstance().getManager().getId());

                if(clientView != null) {
                    clientView.init();
                    rezervacijeView.initKlijent();
                }
                else {
                    managerView.init();
                    rezervacijeView.initManager();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Morate da selektujete rezervaciju!","Otkazivanje",1);
        }
    }
}
