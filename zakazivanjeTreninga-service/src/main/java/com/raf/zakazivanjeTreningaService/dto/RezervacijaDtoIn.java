package com.raf.zakazivanjeTreningaService.dto;

public class RezervacijaDtoIn {

    private Long klijentId;
    private Long terminId;


    public Long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Long klijentId) {
        this.klijentId = klijentId;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }
}
