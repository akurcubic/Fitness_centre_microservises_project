package com.raf.aplikacija.view;

import com.raf.aplikacija.listeneri.FilterPoDanu;
import com.raf.aplikacija.listeneri.FilterPoTipu;

import javax.swing.*;
import java.awt.*;

public class FilterDanView extends JDialog{

    private JLabel label;
    private JTextField textField;
    private JButton filtrirajButton;

    private ClientView clientView;
    private ManagerView managerView;

    public FilterDanView(ClientView clientView) {

        this.clientView = clientView;
        setLocationRelativeTo(clientView);
        instanciraj();
    }

    public FilterDanView(ManagerView managerView){

        this.managerView = managerView;
        setLocationRelativeTo(managerView);
        instanciraj();
    }

    private void instanciraj(){

        setSize(300, 150);

        JPanel panel = new JPanel(new BorderLayout());

        label = new JLabel("Unesite dan:");
        panel.add(label, BorderLayout.NORTH);

        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        filtrirajButton = new JButton("Filtriraj");
        filtrirajButton.addActionListener(new FilterPoDanu(this));
        panel.add(filtrirajButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JButton getFiltrirajButton() {
        return filtrirajButton;
    }

    public void setFiltrirajButton(JButton filtrirajButton) {
        this.filtrirajButton = filtrirajButton;
    }

    public ClientView getClientView() {
        return clientView;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }
}
