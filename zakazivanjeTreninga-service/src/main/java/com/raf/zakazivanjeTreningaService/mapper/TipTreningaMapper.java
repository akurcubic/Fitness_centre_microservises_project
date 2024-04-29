package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.TipTreninga;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoOut;
import org.springframework.stereotype.Component;

@Component
public class TipTreningaMapper {

    public TipTreninga tipTreningaDtoInToTipTreninga(TipTreningaDtoIn tipTreningaDtoIn){

        TipTreninga tipTreninga = new TipTreninga();
        tipTreninga.setTip(tipTreningaDtoIn.getTip());
        return tipTreninga;
    }

    public TipTreningaDtoOut tipTreningaToTipTreningaDtoOut(TipTreninga tipTreninga){

        TipTreningaDtoOut tipTreningaDtoOut = new TipTreningaDtoOut();
        tipTreningaDtoOut.setTip(tipTreninga.getTip());
        tipTreningaDtoOut.setId(tipTreninga.getIdTipa());
        return tipTreningaDtoOut;
    }
}
