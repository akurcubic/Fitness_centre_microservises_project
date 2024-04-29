package com.raf.userservice.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("Client")
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
