package com.raf.zakazivanjeTreningaService.dto;

public class TerminDtoIn {

    private String nazivSale;
    private String nazivTreninga;
    private String datum;
    private String dan;
    private String vremeOd;
    private String vremeDo;

    public String getNazivTreninga() {
        return nazivTreninga;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }

    public void setNazivTreninga(String nazivTreninga) {
        this.nazivTreninga = nazivTreninga;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(String vremeOd) {
        this.vremeOd = vremeOd;
    }

    public String getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(String vremeDo) {
        this.vremeDo = vremeDo;
    }
}
