package com.raf.aplikacija.view;

import com.raf.aplikacija.listeneri.PromeniLozniku;
import com.raf.aplikacija.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;

public class PromeniLozinkuView extends JDialog {


    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private ClientView clientView;
    private ManagerView managerView;
    private UserServiceRestClient userServiceRestClient;

    public PromeniLozinkuView(ClientView clientView) {

        this.clientView = clientView;
        setLocationRelativeTo(clientView);
        initUI();
    }

    public PromeniLozinkuView(ManagerView managerView){

        this.managerView = managerView;
        setLocationRelativeTo(managerView);
        initUI();

    }

    private void initUI() {
        setLayout(new GridLayout(4, 2, 10, 10));
        userServiceRestClient = new UserServiceRestClient();
        setTitle("Promeni Lozinku");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Old password:"));
        oldPasswordField = new JPasswordField();
        add(oldPasswordField);

        add(new JLabel("New password:"));
        newPasswordField = new JPasswordField();
        add(newPasswordField);

        JButton promeniButton = new JButton("Promeni");
        promeniButton.addActionListener(new PromeniLozniku(this));
        add(promeniButton);

        pack();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getOldPasswordField() {
        return oldPasswordField;
    }

    public void setOldPasswordField(JPasswordField oldPasswordField) {
        this.oldPasswordField = oldPasswordField;
    }

    public JPasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public void setNewPasswordField(JPasswordField newPasswordField) {
        this.newPasswordField = newPasswordField;
    }

    public ClientView getClientView() {
        return clientView;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public ManagerView getManagerView() {
        return managerView;
    }

    public void setManagerView(ManagerView managerView) {
        this.managerView = managerView;
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public void setUserServiceRestClient(UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }
}
