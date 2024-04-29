package com.raf.zakazivanjeTreningaService.dto;

public class TerminDtoOut {

    private Long idTermina;

    private String nazivSale;
    private String nazivTreninga;
    private String tipTreninga;
    private int cenaTreninga;
    private String datum;
    private String dan;
    private String vremeOd;
    private String vremeDo;
    private int kapacitet;
    private boolean dostupan;

    public Long getIdTermina() {
        return idTermina;
    }

    public String getDan() {
        return dan;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public boolean isDostupan() {
        return dostupan;
    }

    public void setDostupan(boolean dostupan) {
        this.dostupan = dostupan;
    }

    public void setDan(String dan) {
        this.dan = dan;
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
