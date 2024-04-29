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

public class ManagerView extends JFrame {
   private JTable table;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private TerminTableModel terminTableModel;
    private RezervacijaServiceRestClient rezervacijaServiceRestClient;
    private UserServiceRestClient userServiceRestClient;


    public ManagerView() throws IllegalAccessException, NoSuchMethodException, IOException {
        setTitle("Client View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        rezervacijaServiceRestClient = new RezervacijaServiceRestClient();
        userServiceRestClient = new UserServiceRestClient();

        terminTableModel = new TerminTableModel();
        userServiceRestClient = new UserServiceRestClient();
        table = new JTable(terminTableModel);
        scrollPane = new JScrollPane(table);

        // Kreiranje panela za dugmiće
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(11, 1)); // 5 redova za 5 dugmića
        JButton btnZakazaniTermini = new JButton("Zakazani termini");
        JButton btnFiltrirajPoTipu = new JButton("Filter po tipu");
        JButton btnFiltrirajPoDanu = new JButton("Filtriraj po danu");
        JButton btnPodaciOSali = new JButton("Podaci o sali");
        JButton btnDodajTipTreninga = new JButton("Dodaj tip treninga");
        JButton btnDodajTrening = new JButton("Dodaj trening");
        JButton btnDodajTreningZaSalu = new JButton("Dodaj trening za salu");
        JButton btnDodajTermin = new JButton("Dodaj termin");
        JButton btnUrediProfil = new JButton("Uredi profil");
        JButton btnPromeniLozniku = new JButton("Promeni lozniku");
        JButton btnSviTermini = new JButton("Svi termini");

        // Dodavanje dugmića na panel
        buttonPanel.add(btnZakazaniTermini);
        btnZakazaniTermini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RezervacijeView rezervacijeView = null;
                try {
                    rezervacijeView = new RezervacijeView(ManagerView.this);
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
                FilterTipView filterTipView = new FilterTipView(ManagerView.this);
                filterTipView.setVisible(true);
            }
        });
        buttonPanel.add(btnFiltrirajPoDanu);
        btnFiltrirajPoDanu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterDanView filterDanView = new FilterDanView(ManagerView.this);
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
        buttonPanel.add(btnPodaciOSali);
        btnPodaciOSali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaUpdateView salaUpdateView = null;
                try {
                    salaUpdateView = new SalaUpdateView(ManagerView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                salaUpdateView.setVisible(true);
            }
        });
        buttonPanel.add(btnDodajTipTreninga);
        btnDodajTipTreninga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipTreningaView tipTreningaView = new TipTreningaView(ManagerView.this);
                tipTreningaView.setVisible(true);
            }
        });
        buttonPanel.add(btnDodajTrening);
        btnDodajTrening.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreningView treningView = null;
                try {
                    treningView = new TreningView(ManagerView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                treningView.setVisible(true);
            }
        });
        buttonPanel.add(btnDodajTreningZaSalu);
        btnDodajTreningZaSalu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreningSalaView treningSalaView = null;
                try {
                    treningSalaView = new TreningSalaView(ManagerView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                treningSalaView.setVisible(true);
            }
        });
        buttonPanel.add(btnDodajTermin);
        btnDodajTermin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodajTerminView dodajTerminView = null;
                try {
                    dodajTerminView = new DodajTerminView(ManagerView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dodajTerminView.setVisible(true);
            }
        });
        buttonPanel.add(btnUrediProfil);
        btnUrediProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUserView updateUserView = new UpdateUserView(ManagerView.this);
                updateUserView.setVisible(true);
            }
        });
        buttonPanel.add(btnPromeniLozniku);
        btnPromeniLozniku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PromeniLozinkuView promeniLozinkuView = new PromeniLozinkuView(ManagerView.this);
                promeniLozinkuView.setVisible(true);
            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.EAST);

        System.out.println("pre inita");
        init();
        System.out.println("posle inita");

        setVisible(true);
    }


    public void init() throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            System.out.println("nalazim termine za salu");
            System.out.println(terminDtoOut);
            if(terminDtoOut.getKapacitet() > 0 && terminDtoOut.isDostupan())
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }


    public void filterPoTipu(String tip) throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            if(terminDtoOut.getTipTreninga().equals(tip))
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }

    public void filterPoDanu(String dan) throws IOException {
        this.setVisible(true);

        terminTableModel.setRowCount(0);

        TerminListDto terminListDto = rezervacijaServiceRestClient.getTerminiZaOdredjenuSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
        terminListDto.getContent().forEach(terminDtoOut -> {
            if(terminDtoOut.getDan().equals(dan))
                terminTableModel.addRow(new Object[]{terminDtoOut.getIdTermina(), terminDtoOut.getNazivSale(), terminDtoOut.getNazivTreninga(),terminDtoOut.getTipTreninga(),terminDtoOut.getCenaTreninga(),terminDtoOut.getDatum(),terminDtoOut.getDan(),terminDtoOut.getVremeOd(),terminDtoOut.getVremeDo(),terminDtoOut.getKapacitet(), terminDtoOut.isDostupan()});
        });
    }

    public RezervacijaServiceRestClient getRezervacijaServiceRestClient() {
        return rezervacijaServiceRestClient;
    }

    public void setRezervacijaServiceRestClient(RezervacijaServiceRestClient rezervacijaServiceRestClient) {
        this.rezervacijaServiceRestClient = rezervacijaServiceRestClient;
    }
}