package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.DodajTreningZaSaluListener;
import com.raf.aplikacija.restclient.dto.TreningDtoOut;
import com.raf.aplikacija.restclient.dto.TreningListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreningSalaView extends JDialog {
    private JLabel salaLabel;
    private JLabel treningLabel;
    private JComboBox<String> treningComboBox;
    private JLabel cenaLabel;
    private JTextField cenaTextField;
    private JLabel kapacitetLabel;
    private JTextField kapacitetTextField;
    private JLabel trajanjeLabel;
    private JTextField trajanjeTextField;
    private JButton dodajButton;
    private ManagerView managerView;


    public TreningSalaView(ManagerView managerView) throws IOException {


        this.managerView = managerView;
        setLayout(new GridLayout(6, 2,5,5));


        salaLabel = new JLabel("Sala:");
        treningLabel = new JLabel("Trening:");
        treningComboBox = new JComboBox<>(getTreningOptions());
        cenaLabel = new JLabel("Cena:");
        cenaTextField = new JTextField();
        kapacitetLabel = new JLabel("Kapacitet:");
        kapacitetTextField = new JTextField();
        trajanjeLabel = new JLabel("Trajanje:");
        trajanjeTextField = new JTextField();
        dodajButton = new JButton("Dodaj");



        JLabel salaImeLabel = new JLabel(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());


        add(salaLabel);
        add(salaImeLabel);
        add(treningLabel);
        add(treningComboBox);
        add(cenaLabel);
        add(cenaTextField);
        add(kapacitetLabel);
        add(kapacitetTextField);
        add(trajanjeLabel);
        add(trajanjeTextField);
        add(new JLabel());
        add(dodajButton);

        dodajButton.addActionListener(new DodajTreningZaSaluListener(this));

        setSize(400, 300);
        setLocationRelativeTo(managerView);
        setVisible(true);
    }

    private String[] getTreningOptions() throws IOException {

        TreningListDto treningListDto = managerView.getRezervacijaServiceRestClient().getSviTreninzi();

        List<String> treninzi = new ArrayList<>();
        for(TreningDtoOut treningDtoOut : treningListDto.getContent()){

            treninzi.add(treningDtoOut.getNaziv());
        }

        return treninzi.toArray(new String[0]);
    }

    public JLabel getSalaLabel() {
        return salaLabel;
    }

    public void setSalaLabel(JLabel salaLabel) {
        this.salaLabel = salaLabel;
    }

    public JLabel getTreningLabel() {
        return treningLabel;
    }

    public void setTreningLabel(JLabel treningLabel) {
        this.treningLabel = treningLabel;
    }

    public JComboBox<String> getTreningComboBox() {
        return treningComboBox;
    }

    public void setTreningComboBox(JComboBox<String> treningComboBox) {
        this.treningComboBox = treningComboBox;
    }

    public JLabel getCenaLabel() {
        return cenaLabel;
    }

    public void setCenaLabel(JLabel cenaLabel) {
        this.cenaLabel = cenaLabel;
    }

    public JTextField getCenaTextField() {
        return cenaTextField;
    }

    public void setCenaTextField(JTextField cenaTextField) {
        this.cenaTextField = cenaTextField;
    }

    public JLabel getKapacitetLabel() {
        return kapacitetLabel;
    }

    public void setKapacitetLabel(JLabel kapacitetLabel) {
        this.kapacitetLabel = kapacitetLabel;
    }

    public JTextField getKapacitetTextField() {
        return kapacitetTextField;
    }

    public void setKapacitetTextField(JTextField kapacitetTextField) {
        this.kapacitetTextField = kapacitetTextField;
    }

    public JLabel getTrajanjeLabel() {
        return trajanjeLabel;
    }

    public void setTrajanjeLabel(JLabel trajanjeLabel) {
        this.trajanjeLabel = trajanjeLabel;
    }

    public JTextField getTrajanjeTextField() {
        return trajanjeTextField;
    }

    public void setTrajanjeTextField(JTextField trajanjeTextField) {
        this.trajanjeTextField = trajanjeTextField;
    }

    public JButton getDodajButton() {
        return dodajButton;
    }

    public void setDodajButton(JButton dodajButton) {
        this.dodajButton = dodajButton;
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }
}
