package com.raf.aplikacija.listeneri;

import com.raf.aplikacija.view.UpdateUserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateUser implements ActionListener {

    private UpdateUserView updateUserView;

    public UpdateUser(UpdateUserView updateUserView) {
        this.updateUserView = updateUserView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String oldUsername = updateUserView.getOldUsernameField().getText();

        String newUsername = updateUserView.getNewUsernameField().getText();

        String firstName = updateUserView.getFirstNameField().getText();

        String lastName = updateUserView.getLastNameField().getText();

        String email = updateUserView.getEmailField().getText();


        if(oldUsername.isEmpty() || newUsername.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){

            JOptionPane.showMessageDialog(updateUserView,"Sva polja moraju biti popunjena!","Obavestenje",1);
            return;
        }

        try {
            updateUserView.getUserServiceRestClient().updateUser(email,firstName,lastName,oldUsername,newUsername);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
