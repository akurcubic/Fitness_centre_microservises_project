package com.raf.aplikacija.listeneri;


import com.raf.aplikacija.view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ZabraniKoriscenjeAplListener implements ActionListener {


    private AdminView adminView;

    public ZabraniKoriscenjeAplListener(AdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedRowIndex = adminView.getTable().getSelectedRow();

        JTable table = adminView.getTable();

        if (selectedRowIndex != -1) {

            String username = (String) table.getValueAt(selectedRowIndex, 4);


            try {

                adminView.getUserServiceRestClient().zabraniKoriscenjeAplikacije(username);
                adminView.init();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Morate da selektujete korisnika!","Obavestenje",1);
        }
    }
}
