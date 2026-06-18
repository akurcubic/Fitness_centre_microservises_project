package com.raf.aplikacija.restclient.dto;

public class RezervacijaDtoOut {

    private Long idRezervacije;
    private Long idTermina;
    private String nazivSale;
    private String nazivTreninga;
    private String tipTreninga;
    private int cenaTreninga;
    private String datum;
    private String dan;
    private String vremeOd;
    private String vremeDo;

    public Long getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Long idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    public Long getIdTermina() {
        return idTermina;
    }

    public void setIdTermina(Long idTermina) {
        this.idTermina = idTermina;
    }

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }

    public String getNazivTreninga() {
        return nazivTreninga;
    }

    public void setNazivTreninga(String nazivTreninga) {
        this.nazivTreninga = nazivTreninga;
    }

    public String getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(String tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public int getCenaTreninga() {
        return cenaTreninga;
    }

    public void setCenaTreninga(int cenaTreninga) {
        this.cenaTreninga = cenaTreninga;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
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
