package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.DodajTerminListener;
import com.raf.aplikacija.listeneri.DodajTreningListener;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaDtoOut;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaListDto;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaTreningDtoOut;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaTreningListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DodajTerminView extends JDialog {

    private ManagerView managerView;

    private JComboBox<String> treningComboBox;
    private JTextField danTextField;
    private JTextField datumTextField;
    private JTextField vremeOdTextField;
    private JTextField vremeDoTextField;


    public DodajTerminView(ManagerView managerView) throws IOException {

        this.managerView = managerView;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(managerView);
        setTitle("Dodaj termin");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2,5,5));


        JLabel treningLabel = new JLabel("Trening u sali:");

        treningComboBox = new JComboBox<>(getTreninziOptions());


        JLabel danLabel = new JLabel("Dan:");
        danTextField = new JTextField();


        JLabel datumLabel = new JLabel("Datum:");
        datumTextField = new JTextField();


        JLabel vremeOdLabel = new JLabel("Vreme Od:");
        vremeOdTextField = new JTextField();


        JLabel vremeDoLabel = new JLabel("Vreme Do:");
        vremeDoTextField = new JTextField();

        JButton dodajButton = new JButton("Dodaj");
        dodajButton.addActionListener(new DodajTerminListener(this));

        panel.add(treningLabel);
        panel.add(treningComboBox);
        panel.add(danLabel);
        panel.add(danTextField);
        panel.add(datumLabel);
        panel.add(datumTextField);
        panel.add(vremeOdLabel);
        panel.add(vremeOdTextField);
        panel.add(vremeDoLabel);
        panel.add(vremeDoTextField);

        add(panel, BorderLayout.CENTER);
        add(dodajButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String[] getTreninziOptions() throws IOException {

        FiskulturnaSalaTreningListDto fiskulturnaSalaTreningListDto = managerView.getRezervacijaServiceRestClient().sviTreninziZaSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());

        List<String> treninzi = new ArrayList<>();
        for(FiskulturnaSalaTreningDtoOut fist : fiskulturnaSalaTreningListDto.getContent()){

            treninzi.add(fist.getTrening());
        }

        return treninzi.toArray(new String[0]);
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }

    public JComboBox<String> getTreningComboBox() {
        return treningComboBox;
    }

    public void setTreningComboBox(JComboBox<String> treningComboBox) {
        this.treningComboBox = treningComboBox;
    }

    public JTextField getDanTextField() {
        return danTextField;
    }

    public void setDanTextField(JTextField danTextField) {
        this.danTextField = danTextField;
    }

    public JTextField getDatumTextField() {
        return datumTextField;
    }

    public void setDatumTextField(JTextField datumTextField) {
        this.datumTextField = datumTextField;
    }

    public JTextField getVremeOdTextField() {
        return vremeOdTextField;
    }

    public void setVremeOdTextField(JTextField vremeOdTextField) {
        this.vremeOdTextField = vremeOdTextField;
    }

    public JTextField getVremeDoTextField() {
        return vremeDoTextField;
    }

    public void setVremeDoTextField(JTextField vremeDoTextField) {
        this.vremeDoTextField = vremeDoTextField;
    }
}
