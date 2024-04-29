package com.raf.zakazivanjeTreningaService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipTreninga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTipa;
    private String tip;

    public TipTreninga(){


    }

    public TipTreninga(String tip) {
        this.tip = tip;
    }

    public Long getIdTipa() {
        return idTipa;
    }

    public void setIdTipa(Long idTipa) {
        this.idTipa = idTipa;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
