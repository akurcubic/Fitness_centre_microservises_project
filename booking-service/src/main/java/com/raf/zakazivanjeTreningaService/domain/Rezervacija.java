package com.raf.zakazivanjeTreningaService.domain;

import javax.persistence.*;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRezervacije;
    private Long idKlijenta;
    private int cena;

    @ManyToOne
    private Termin termin;

    public Rezervacija(){


    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Long getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Long idRezervacije) {
        this.idRezervacije = idRezervacije;
    }

    public Long getIdKlijenta() {
        return idKlijenta;
    }

    public void setIdKlijenta(Long idKlijenta) {
        this.idKlijenta = idKlijenta;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
