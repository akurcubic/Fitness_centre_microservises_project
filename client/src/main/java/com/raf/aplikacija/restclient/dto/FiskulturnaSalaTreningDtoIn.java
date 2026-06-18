package com.raf.aplikacija.restclient.dto;

public class FiskulturnaSalaTreningDtoIn {

    private String nazivSale;
    private String trening;
    private int cena;
    private int trajanjeTreninga;
    private int kapacitet;

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }

    public String getTrening() {
        return trening;
    }

    public void setTrening(String trening) {
        this.trening = trening;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getTrajanjeTreninga() {
        return trajanjeTreninga;
    }

    public void setTrajanjeTreninga(int trajanjeTreninga) {
        this.trajanjeTreninga = trajanjeTreninga;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
}
