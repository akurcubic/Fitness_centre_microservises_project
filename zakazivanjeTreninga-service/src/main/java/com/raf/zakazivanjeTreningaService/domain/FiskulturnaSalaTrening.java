package com.raf.zakazivanjeTreningaService.domain;

import javax.persistence.*;

@Entity
public class FiskulturnaSalaTrening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int cena;
    private int trajanjeTreninga;
    private int kapacitet;

    @ManyToOne
    private FiskulturnaSala fiskulturnaSala;
    @ManyToOne
    private Trening trening;

    public FiskulturnaSalaTrening(){

    }

    public FiskulturnaSalaTrening(int cena, int trajanjeTreninga, int kapacitet, FiskulturnaSala fiskulturnaSala, Trening trening) {
        this.cena = cena;
        this.trajanjeTreninga = trajanjeTreninga;
        this.kapacitet = kapacitet;
        this.fiskulturnaSala = fiskulturnaSala;
        this.trening = trening;
    }

    public void setFiskulturnaSala(FiskulturnaSala fiskulturnaSala) {
        this.fiskulturnaSala = fiskulturnaSala;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    public FiskulturnaSala getFiskulturnaSala() {
        return fiskulturnaSala;
    }

    public Trening getTrening() {
        return trening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getTrajanjeTreninga() {
        return trajanjeTreninga;
    }

    public void setTrajanjeTreninga(int trajanjeTreninga) {
        this.trajanjeTreninga = trajanjeTreninga;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
}
