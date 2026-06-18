package com.raf.aplikacija.restclient.dto;

public class OtkaziRezervacijuDto {

    private Long idRezervacije;
    private Long idUsera;

    public Long getIdUsera() {
        return idUsera;
    }

    public void setIdUsera(Long idUsera) {
        this.idUsera = idUsera;
    }

    public Long getIdRezervacije() {
        return idRezervacije;
    }

    public void setIdRezervacije(Long idRezervacije) {
        this.idRezervacije = idRezervacije;
    }
}
