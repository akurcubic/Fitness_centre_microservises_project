package com.raf.aplikacija.view;

import com.raf.aplikacija.listeneri.ZabraniKoriscenjeAplListener;
import com.raf.aplikacija.model.UserTableModel;
import com.raf.aplikacija.restclient.RezervacijaServiceRestClient;
import com.raf.aplikacija.restclient.UserServiceRestClient;
import com.raf.aplikacija.restclient.dto.UserListDto;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminView extends JDialog {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private UserTableModel userTableModel;
    private RezervacijaServiceRestClient rezervacijaServiceRestClient;
    private UserServiceRestClient userServiceRestClient;


    public AdminView() throws IllegalAccessException, NoSuchMethodException, IOException {
        setTitle("Admin View");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);


        userTableModel = new UserTableModel();
        rezervacijaServiceRestClient = new RezervacijaServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();
        table = new JTable(userTableModel);
        scrollPane = new JScrollPane(table);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        JButton zabrani = new JButton("Zabrani korisenje aplikacije");
        JButton dodaj = new JButton("Dodaj salu");
        JButton definisi = new JButton("Definisi notofikaciju");
        JButton azuriraj = new JButton("Azuriraj notifikacije");


        buttonPanel.add(zabrani);
        zabrani.addActionListener(new ZabraniKoriscenjeAplListener(this));

        buttonPanel.add(dodaj);
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodajSaluView dodajSaluView = null;
                try {
                    dodajSaluView = new DodajSaluView(AdminView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dodajSaluView.setVisible(true);
            }
        });

        buttonPanel.add(definisi);

        buttonPanel.add(azuriraj);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.EAST);


        init();

        setVisible(true);
    }

    public void init() throws IOException {
        this.setVisible(true);

        userTableModel.setRowCount(0);

        UserListDto userListDto = userServiceRestClient.getSviKorisnici();
        userListDto.getContent().forEach(userDto -> {
            System.out.println(userDto);
            if(!userDto.getUsername().equals("admin"))
                userTableModel.addRow(new Object[]{userDto.getId(), userDto.getEmail(), userDto.getFirstName(),userDto.getLastName(),userDto.getUsername(),userDto.getDateOfBirth(),userDto.isAktivan(),userDto.isZabranjen()});
        });
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public UserTableModel getUserTableModel() {
        return userTableModel;
    }

    public void setUserTableModel(UserTableModel userTableModel) {
        this.userTableModel = userTableModel;
    }

    public RezervacijaServiceRestClient getRezervacijaServiceRestClient() {
        return rezervacijaServiceRestClient;
    }

    public void setRezervacijaServiceRestClient(RezervacijaServiceRestClient rezervacijaServiceRestClient) {
        this.rezervacijaServiceRestClient = rezervacijaServiceRestClient;
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public void setUserServiceRestClient(UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }
}
