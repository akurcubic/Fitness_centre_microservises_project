package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.view.FilterDanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FilterPoDanu implements ActionListener {


    private FilterDanView filterDanView;

    public FilterPoDanu(FilterDanView filterDanView) {
        this.filterDanView = filterDanView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if(!filterDanView.getTextField().getText().isEmpty()) {
                if(filterDanView.getClientView() != null)
                    filterDanView.getClientView().filterPoDanu(filterDanView.getTextField().getText());
                else
                    filterDanView.getManagerView().filterPoDanu(filterDanView.getTextField().getText());
            }
            else{
                JOptionPane.showMessageDialog(filterDanView,"Unesite dan treninga!","Unesite podatke",1);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
