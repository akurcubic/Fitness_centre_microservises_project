package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.UpdateUser;
import com.raf.aplikacija.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;

public class UpdateUserView extends JDialog{

    private JTextField oldUsernameField;
    private JTextField newUsernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private UserServiceRestClient userServiceRestClient;
    private ClientView clientView;
    private ManagerView managerView;

    public UpdateUserView(ClientView clientView) {

        this.clientView = clientView;
        instanciraj();
        setLocationRelativeTo(clientView);

    }

    public UpdateUserView(ManagerView managerView){

        this.managerView = managerView;
        instanciraj();
        setLocationRelativeTo(managerView);

    }

    private void instanciraj(){

        userServiceRestClient = new UserServiceRestClient();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        JLabel oldUsernameLabel = new JLabel("Old username:");
        oldUsernameField = new JTextField();

        JLabel newUsernameLabel = new JLabel("New username:");
        newUsernameField = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        if(clientView != null){
            oldUsernameField.setText(ClientApplication.getInstance().getClient().getUsername());
            newUsernameField.setText(ClientApplication.getInstance().getClient().getUsername());
            firstNameField.setText(ClientApplication.getInstance().getClient().getFirstName());
            lastNameField.setText(ClientApplication.getInstance().getClient().getLastName());
            emailField.setText(ClientApplication.getInstance().getClient().getEmail());
        }
        else{
            oldUsernameField.setText(ClientApplication.getInstance().getManager().getUsername());
            newUsernameField.setText(ClientApplication.getInstance().getManager().getUsername());
            firstNameField.setText(ClientApplication.getInstance().getManager().getFirstName());
            lastNameField.setText(ClientApplication.getInstance().getManager().getLastName());
            emailField.setText(ClientApplication.getInstance().getManager().getEmail());
        }

        panel.add(oldUsernameLabel);
        panel.add(oldUsernameField);
        panel.add(newUsernameLabel);
        panel.add(newUsernameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);

        JButton changeButton = new JButton("Promeni");
        changeButton.addActionListener(new UpdateUser(this));
        changeButton.addActionListener(new UpdateUser(this));
        panel.add(changeButton);

        add(panel);
        setVisible(true);
    }

    public JTextField getOldUsernameField() {
        return oldUsernameField;
    }

    public void setOldUsernameField(JTextField oldUsernameField) {
        this.oldUsernameField = oldUsernameField;
    }

    public JTextField getNewUsernameField() {
        return newUsernameField;
    }

    public void setNewUsernameField(JTextField newUsernameField) {
        this.newUsernameField = newUsernameField;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(JTextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(JTextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public void setUserServiceRestClient(UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }
}
