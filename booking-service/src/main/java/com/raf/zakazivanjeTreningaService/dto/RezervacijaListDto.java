package com.raf.zakazivanjeTreningaService.dto;

import java.util.ArrayList;
import java.util.List;

public class RezervacijaListDto {

    private List<RezervacijaDtoOut> content = new ArrayList<>();

    public RezervacijaListDto(){


    }

    public List<RezervacijaDtoOut> getContent() {
        return content;
    }

    public void setContent(List<RezervacijaDtoOut> content) {
        this.content = content;
    }
}
