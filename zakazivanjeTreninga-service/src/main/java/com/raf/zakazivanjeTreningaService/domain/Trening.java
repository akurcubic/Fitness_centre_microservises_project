package com.raf.zakazivanjeTreningaService.domain;

import javax.persistence.*;

@Entity
public class Trening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTreninga;
    private String naziv;

    @ManyToOne
    private TipTreninga tipTreninga;

    public Trening(){


    }

    public Trening(String naziv, TipTreninga tipTreninga) {
        this.naziv = naziv;
        this.tipTreninga = tipTreninga;
    }

    public Long getId() {
        return idTreninga;
    }

    public void setId(Long id) {
        this.idTreninga = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }
}
