package com.raf.aplikacija.view;

import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.listeneri.OtkaziListener;
import com.raf.aplikacija.model.RezervacijaTableModel;
import com.raf.aplikacija.restclient.RezervacijaServiceRestClient;
import com.raf.aplikacija.restclient.dto.RezervacijaListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RezervacijeView extends JDialog {
    private JTable table;
    private RezervacijaTableModel rezervacijaTableModel;
    private RezervacijaServiceRestClient rezervacijaServiceRestClient;
    private ClientView clientView;
    private ManagerView managerView;
    JButton otkaziButton = new JButton("Otkazi rezervaciju");


    public RezervacijeView(ClientView clientView) throws IllegalAccessException, NoSuchMethodException, IOException {

        this.clientView = clientView;
        instanciraj();
        otkaziButton.addActionListener(new OtkaziListener(this,clientView));
    }

    public RezervacijeView(ManagerView managerView) throws IllegalAccessException, NoSuchMethodException, IOException {

        this.managerView = managerView;
        instanciraj();
        otkaziButton.addActionListener(new OtkaziListener(this,managerView));

    }

    private void instanciraj() throws IllegalAccessException, NoSuchMethodException, IOException {


        setTitle("Prikaz rezervacija");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Kreiranje tabele za prikaz rezervacija
        rezervacijaTableModel = new RezervacijaTableModel();
        rezervacijaServiceRestClient = new RezervacijaServiceRestClient();
        table = new JTable(rezervacijaTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Dugme za otkazivanje rezervacije


        panel.add(otkaziButton, BorderLayout.EAST);

        add(panel);

        if(clientView != null)
            initKlijent();
        else
            initManager();

        setVisible(true);
    }

    public void initKlijent() throws IOException {
        this.setVisible(true);

        rezervacijaTableModel.setRowCount(0);

        RezervacijaListDto rezervacijaListDto = rezervacijaServiceRestClient.getRezervacijeZaKlijenta(ClientApplication.getInstance().getClient().getId());
        rezervacijaListDto.getContent().forEach(rezervacijaDtoOut -> {
            System.out.println(rezervacijaDtoOut);
            rezervacijaTableModel.addRow(new Object[]{rezervacijaDtoOut.getIdRezervacije(),rezervacijaDtoOut.getIdTermina(), rezervacijaDtoOut.getNazivSale(), rezervacijaDtoOut.getNazivTreninga(),rezervacijaDtoOut.getTipTreninga(),rezervacijaDtoOut.getCenaTreninga(),rezervacijaDtoOut.getDatum(),rezervacijaDtoOut.getDan(),rezervacijaDtoOut.getVremeOd(),rezervacijaDtoOut.getVremeDo()});
        });
    }

    public void initManager() throws IOException {
        this.setVisible(true);

        rezervacijaTableModel.setRowCount(0);

        RezervacijaListDto rezervacijaListDto = rezervacijaServiceRestClient.getRezervacijeZaSalu(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
        rezervacijaListDto.getContent().forEach(rezervacijaDtoOut -> {
            System.out.println(rezervacijaDtoOut);
            rezervacijaTableModel.addRow(new Object[]{rezervacijaDtoOut.getIdRezervacije(),rezervacijaDtoOut.getIdTermina(), rezervacijaDtoOut.getNazivSale(), rezervacijaDtoOut.getNazivTreninga(),rezervacijaDtoOut.getTipTreninga(),rezervacijaDtoOut.getCenaTreninga(),rezervacijaDtoOut.getDatum(),rezervacijaDtoOut.getDan(),rezervacijaDtoOut.getVremeOd(),rezervacijaDtoOut.getVremeDo()});
        });
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public RezervacijaTableModel getRezervacijaTableModel() {
        return rezervacijaTableModel;
    }

    public void setRezervacijaTableModel(RezervacijaTableModel rezervacijaTableModel) {
        this.rezervacijaTableModel = rezervacijaTableModel;
    }

    public RezervacijaServiceRestClient getRezervacijaServiceRestClient() {
        return rezervacijaServiceRestClient;
    }

    public void setRezervacijaServiceRestClient(RezervacijaServiceRestClient rezervacijaServiceRestClient) {
        this.rezervacijaServiceRestClient = rezervacijaServiceRestClient;
    }
}