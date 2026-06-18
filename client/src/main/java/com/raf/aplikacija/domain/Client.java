package com.raf.aplikacija.domain;

public class Client extends User{

    private int brojClanskeKarte;
    private int brojZakazanihTreninga;
    private String nazivFiskulturneSale;

    public String getNazivFiskulturneSale() {
        return nazivFiskulturneSale;
    }

    public void setNazivFiskulturneSale(String nazivFiskulturneSale) {
        this.nazivFiskulturneSale = nazivFiskulturneSale;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public int getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    public void setBrojZakazanihTreninga(int brojZakazanihTreninga) {
        this.brojZakazanihTreninga = brojZakazanihTreninga;
    }
}
