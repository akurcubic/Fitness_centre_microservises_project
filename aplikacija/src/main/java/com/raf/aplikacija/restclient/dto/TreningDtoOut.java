package com.raf.aplikacija.restclient.dto;

public class TreningDtoOut {

    private String naziv;
    private String tip;
    private Long idTreninga;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Long getIdTreninga() {
        return idTreninga;
    }

    public void setIdTreninga(Long idTreninga) {
        this.idTreninga = idTreninga;
    }
}
