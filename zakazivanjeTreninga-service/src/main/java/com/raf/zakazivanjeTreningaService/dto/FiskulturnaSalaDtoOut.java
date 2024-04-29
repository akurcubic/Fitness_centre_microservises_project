package com.raf.zakazivanjeTreningaService.dto;

public class FiskulturnaSalaDtoOut {

    private Long salaId;
    private String naziv;
    private String opis;
    private int brojTrenera;
    private String pocetakRadnogVremena;
    private String krajRadnogVremena;
    private int pogodnostZaVereneKlijente;
    private Long managerId;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojTrenera() {
        return brojTrenera;
    }

    public void setBrojTrenera(int brojTrenera) {
        this.brojTrenera = brojTrenera;
    }

    public String getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(String pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public String getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(String krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }

    public int getPogodnostZaVereneKlijente() {
        return pogodnostZaVereneKlijente;
    }

    public void setPogodnostZaVereneKlijente(int pogodnostZaVereneKlijente) {
        this.pogodnostZaVereneKlijente = pogodnostZaVereneKlijente;
    }
}
