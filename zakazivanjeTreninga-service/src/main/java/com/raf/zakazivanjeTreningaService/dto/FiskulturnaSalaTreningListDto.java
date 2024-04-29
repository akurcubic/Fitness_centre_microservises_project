package com.raf.zakazivanjeTreningaService.dto;

import java.util.ArrayList;
import java.util.List;

public class FiskulturnaSalaTreningListDto {

    private List<FiskulturnaSalaTreningDtoOut> content = new ArrayList<>();

    public FiskulturnaSalaTreningListDto(){


    }

    public List<FiskulturnaSalaTreningDtoOut> getContent() {
        return content;
    }

    public void setContent(List<FiskulturnaSalaTreningDtoOut> content) {
        this.content = content;
    }
}
