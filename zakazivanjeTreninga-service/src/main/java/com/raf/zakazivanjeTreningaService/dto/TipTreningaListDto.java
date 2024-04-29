package com.raf.zakazivanjeTreningaService.dto;

import java.util.ArrayList;
import java.util.List;

public class TipTreningaListDto {

    private List<FiskulturnaSalaDtoOut> content = new ArrayList<>();

    public TipTreningaListDto(){


    }

    public List<FiskulturnaSalaDtoOut> getContent() {
        return content;
    }

    public void setContent(List<FiskulturnaSalaDtoOut> content) {
        this.content = content;
    }
}
