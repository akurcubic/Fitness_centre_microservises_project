package com.raf.aplikacija.view;

import com.raf.aplikacija.restclient.RezervacijaServiceRestClient;
import com.raf.aplikacija.restclient.UserServiceRestClient;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaDtoOut;
import com.raf.aplikacija.restclient.dto.FiskulturnaSalaListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterManagerView extends JFrame {

    private RezervacijaServiceRestClient rezervacijaServiceRestClient;
    private UserServiceRestClient userServiceRestClient;

    private JPanel mainPanel;

    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JLabel firstNameLabel;
    private JTextField firstNameField;

    private JLabel lastNameLabel;
    private JTextField lastNameField;

    private JLabel emailLabel;
    private JTextField emailField;

    private JLabel dobLabel;
    private JTextField dobField;

    private JLabel employmentDateLabel;
    private JTextField employmentDateField;

    private JLabel gymLabel;
    private JComboBox<String> gymComboBox; // ComboBox za Fiskulturne sale

    private JButton registerButton;

    public RegisterManagerView() throws IOException {

        rezervacijaServiceRestClient = new RezervacijaServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();

        setTitle("Register Manager");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 2, 10, 10));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();

        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();

        emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        dobLabel = new JLabel("Date of Birth:");
        dobField = new JTextField();

        employmentDateLabel = new JLabel("Employment Date:");
        employmentDateField = new JTextField();

        gymLabel = new JLabel("Gym:");
        gymComboBox = new JComboBox<>(getGymOptions()); // Popunjavanje ComboBox-a sa salama

        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {

            try {
                userServiceRestClient.registerManager(usernameField.getText(),passwordField.getText(),
                        (String)gymComboBox.getSelectedItem(),firstNameField.getText(),
                        lastNameField.getText(),emailField.getText(),employmentDateField.getText(),dobField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(firstNameLabel);
        mainPanel.add(firstNameField);
        mainPanel.add(lastNameLabel);
        mainPanel.add(lastNameField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(dobLabel);
        mainPanel.add(dobField);
        mainPanel.add(employmentDateLabel);
        mainPanel.add(employmentDateField);
        mainPanel.add(gymLabel);
        mainPanel.add(gymComboBox);
        mainPanel.add(registerButton);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] getGymOptions() throws IOException {

        FiskulturnaSalaListDto fiskulturnaSalaListDto = rezervacijaServiceRestClient.getSala();

        List<String> sale = new ArrayList<>();
        for(FiskulturnaSalaDtoOut fis : fiskulturnaSalaListDto.getContent()){

            sale.add(fis.getNaziv());
        }

        return sale.toArray(new String[0]);
    }

}
