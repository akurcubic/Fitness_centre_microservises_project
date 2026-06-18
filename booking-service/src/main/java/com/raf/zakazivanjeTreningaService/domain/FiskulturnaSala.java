package com.raf.zakazivanjeTreningaService.domain;

import javax.persistence.*;


@Entity
public class FiskulturnaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long salaId;
    private Long managerId;
    @Column(unique = true)
    private String naziv;
    private String opis;
    private int brojTrenera;
    private String pocetakRadnogVremena;
    private String krajRadnogVremena;
    private int pogodnostZaVereneKlijente;

    public FiskulturnaSala(){


    }

    public FiskulturnaSala(String naziv, String opis, int brojTrenera, String pocetakRadnogVremena, String krajRadnogVremena, int pogodnostZaVereneKlijente) {
        this.naziv = naziv;
        this.opis = opis;
        this.brojTrenera = brojTrenera;
        this.pocetakRadnogVremena = pocetakRadnogVremena;
        this.krajRadnogVremena = krajRadnogVremena;
        this.pogodnostZaVereneKlijente = pogodnostZaVereneKlijente;
    }

    public int getPogodnostZaVereneKlijente() {
        return pogodnostZaVereneKlijente;
    }

    public void setPogodnostZaVereneKlijente(int pogodnostZaVereneKlijente) {
        this.pogodnostZaVereneKlijente = pogodnostZaVereneKlijente;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
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
}
