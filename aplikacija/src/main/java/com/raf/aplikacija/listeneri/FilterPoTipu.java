package com.raf.aplikacija.listeneri;


import com.raf.aplikacija.view.FilterTipView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FilterPoTipu implements ActionListener {

    private FilterTipView filterTipView;

    public FilterPoTipu(FilterTipView filterTipView) {

        this.filterTipView = filterTipView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if(!filterTipView.getTextField().getText().isEmpty()) {
                if (filterTipView.getClientView() != null)
                    filterTipView.getClientView().filterPoTipu(filterTipView.getTextField().getText());
                else
                    filterTipView.getManagerView().filterPoTipu(filterTipView.getTextField().getText());
            }
            else{
                JOptionPane.showMessageDialog(filterTipView,"Unesite tip treninga!","Unesite podatke",1);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
