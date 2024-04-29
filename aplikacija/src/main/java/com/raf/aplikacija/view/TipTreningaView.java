package com.raf.aplikacija.view;

import com.raf.aplikacija.listeneri.TipTreningaListener;

import javax.swing.*;
import java.awt.*;

public class TipTreningaView extends JDialog {
    private JTextField tipTreninga;
    private ManagerView managerView;

    public TipTreningaView(ManagerView managerView) {

        this.managerView = managerView;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel tipTr = new JLabel("Tip treninga:");
        tipTreninga = new JTextField(15);
        topPanel.add(tipTr);
        topPanel.add(tipTreninga);


        panel.add(topPanel, BorderLayout.NORTH);


        JButton changeButton = new JButton("Dodaj");
        changeButton.addActionListener(new TipTreningaListener(this));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(changeButton);


        panel.add(centerPanel, BorderLayout.CENTER);


        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(JTextField tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }
}


