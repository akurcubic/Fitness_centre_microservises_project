package com.raf.aplikacija.listeneri;


import com.raf.aplikacija.view.PromeniLozinkuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PromeniLozniku implements ActionListener {

    private PromeniLozinkuView promeniLozinkuView;

    public PromeniLozniku(PromeniLozinkuView promeniLozinkuView) {
        this.promeniLozinkuView = promeniLozinkuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = promeniLozinkuView.getUsernameField().getText();
        String oldPassword = promeniLozinkuView.getOldPasswordField().getText();
        String newPassword = promeniLozinkuView.getNewPasswordField().getText();

        try {
            promeniLozinkuView.getUserServiceRestClient().promeniLozinku(username,oldPassword,newPassword);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
