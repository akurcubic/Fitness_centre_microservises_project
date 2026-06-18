package com.raf.aplikacija.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class TipTreningaListDto {

    private List<TipTreningaDtoOut> content = new ArrayList<>();

    public TipTreningaListDto(){


    }

    public List<TipTreningaDtoOut> getContent() {
        return content;
    }

    public void setContent(List<TipTreningaDtoOut> content) {
        this.content = content;
    }
}
