package com.raf.aplikacija.view;

import com.raf.aplikacija.listeneri.DodajTreningListener;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaDtoOut;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaListDto;
import com.raf.aplikacija.restclient.dto.TipTreningaDtoOut;
import com.raf.aplikacija.restclient.dto.TipTreningaListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreningView extends JDialog {
    private JTextField txtNazivTreninga;
    private JComboBox<String> cbTipTreninga;
    private JButton btnDodaj;

    private ManagerView managerView;

    public TreningView(ManagerView managerView) throws IOException {

        this.managerView = managerView;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));


        JLabel lblNazivTreninga = new JLabel("Naziv treninga:");
        txtNazivTreninga = new JTextField();
        panel.add(lblNazivTreninga);
        panel.add(txtNazivTreninga);


        JLabel lblTipTreninga = new JLabel("Tip treninga:");
        cbTipTreninga = new JComboBox<>(getTipOptions());
        panel.add(lblTipTreninga);
        panel.add(cbTipTreninga);

        btnDodaj = new JButton("Dodaj");
        btnDodaj.addActionListener(new DodajTreningListener(this));
        panel.add(btnDodaj);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private String[] getTipOptions() throws IOException {

        TipTreningaListDto tipTreningaListDto = managerView.getRezervacijaServiceRestClient().getTipTreningaAll();

        List<String> tipovi = new ArrayList<>();
        for(TipTreningaDtoOut tip : tipTreningaListDto.getContent()){

            tipovi.add(tip.getTip());
        }

        return tipovi.toArray(new String[0]);
    }

    public JTextField getTxtNazivTreninga() {
        return txtNazivTreninga;
    }

    public void setTxtNazivTreninga(JTextField txtNazivTreninga) {
        this.txtNazivTreninga = txtNazivTreninga;
    }

    public JComboBox<String> getCbTipTreninga() {
        return cbTipTreninga;
    }

    public void setCbTipTreninga(JComboBox<String> cbTipTreninga) {
        this.cbTipTreninga = cbTipTreninga;
    }

    public JButton getBtnDodaj() {
        return btnDodaj;
    }

    public void setBtnDodaj(JButton btnDodaj) {
        this.btnDodaj = btnDodaj;
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }
}
