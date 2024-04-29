package com.raf.userservice.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends User{

    private String nazivFiskulturneSale;
    private String datumZaposljavanja;

    public String getNazivFiskulturneSale() {
        return nazivFiskulturneSale;
    }

    public void setNazivFiskulturneSale(String nazivFiskulturneSale) {
        this.nazivFiskulturneSale = nazivFiskulturneSale;
    }

    public String getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(String datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }
}
