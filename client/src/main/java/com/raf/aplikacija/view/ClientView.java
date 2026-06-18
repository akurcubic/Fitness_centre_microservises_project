package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.ZakaziListener;
import com.raf.aplikacija.model.TerminTableModel;
import com.raf.aplikacija.restclient.RezervacijaServiceRestClient;
import com.raf.aplikacija.restclient.UserServiceRestClient;
import com.raf.aplikacija.restclient.dto.TerminListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientView extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private TerminTableModel terminTableModel;
    private RezervacijaServiceRestClient rezervacijaServiceRestClient;
    private UserServiceRestClient userServiceRestClient;


    public ClientView() throws IllegalAccessException, NoSuchMethodException, IOException {
        setTitle("Client View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);


        terminTableModel = new TerminTableModel();
        rezervacijaServiceRestClient = new RezervacijaServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();
        table = new JTable(terminTableModel);
        scrollPane = new JScrollPane(table);

        // Kreiranje panela za dugmiće
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        JButton btnZakaziTermin = new JButton("Zakazi termin");
        JButton btnZakazaniTermini = new JButton("Zakazani termini");
        JButton btnFiltrirajPoTipu = new JButton("Filtriraj po tipu");
        JButton btnFiltrirajPoDanu = new JButton("Filtriraj po danu");
        JButton btnUrediProfil = new JButton("Uredi profil");
        JButton btnPromeniLozniku = new JButton("Promeni lozniku");
        JButton btnSviTermini = new JButton("Svi termini");

        // Dodavanje dugmića na panel
        buttonPanel.add(btnZakaziTermin);
        btnZakaziTermin.addActionListener(new ZakaziListener(this));
        buttonPanel.add(btnZakazaniTermini);
        btnZakazaniTermini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RezervacijeView rezervacijeView = null;
                try {
                    rezervacijeView = new RezervacijeView(ClientView.this);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                rezervacijeView.setVisible(true);
            }
        });

        buttonPanel.add(btnFiltrirajPoTipu);
        btnFiltrirajPoTipu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterTipView filterTipView = new FilterTipView(ClientView.this);
                filterTipView.setVisible(true);
            }
        });
        buttonPanel.add(btnFiltrirajPoDanu);
        btnFiltrirajPoDanu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterDanView filterDanView = new FilterDanView(ClientView.this);
                filterDanView.setVisible(true);
            }
        });
        buttonPanel.add(btnSviTermini);
        btnSviTermini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonPanel.add(btnUrediProfil);
        btnUrediProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUserView updateUserView = new UpdateUserView(ClientView.this);
                updateUserView.setVisible(true);
            }
        });
        buttonPanel.add(btnPromeniLozniku);
        btnPromeniLozniku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PromeniLozinkuView promeniLozinkuView = new PromeniLozinkuView(ClientView.this);
                promeniLozinkuView.setVisible(true);

            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.EAST);


        init();

        setVisible(true);
    }


    public void init() throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getClient().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            System.out.println(terminDtoOut);
            if(terminDtoOut.getKapacitet() > 0 && terminDtoOut.isDostupan())
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }

    public void filterPoTipu(String tip) throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getClient().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            if(terminDtoOut.getTipTreninga().equals(tip))
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }

    public void filterPoDanu(String dan) throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getClient().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            if(terminDtoOut.getDan().equals(dan))
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public void setUserServiceRestClient(UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public TerminTableModel getTerminTableModel() {
        return terminTableModel;
    }

    public void setTerminTableModel(TerminTableModel terminTableModel) {
        this.terminTableModel = terminTableModel;
    }

    public RezervacijaServiceRestClient getRezervacijaServiceRestClient() {
        return rezervacijaServiceRestClient;
    }

    public void setRezervacijaServiceRestClient(RezervacijaServiceRestClient rezervacijaServiceRestClient) {
        this.rezervacijaServiceRestClient = rezervacijaServiceRestClient;
    }
}
