package com.raf.aplikacija.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class TreningListDto {

    private List<TreningDtoOut> content = new ArrayList<>();

    public TreningListDto(){


    }

    public List<TreningDtoOut> getContent() {
        return content;
    }

    public void setContent(List<TreningDtoOut> content) {
        this.content = content;
    }
}
