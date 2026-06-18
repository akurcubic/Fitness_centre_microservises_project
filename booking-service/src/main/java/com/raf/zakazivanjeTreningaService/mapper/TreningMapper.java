package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.Trening;
import com.raf.zakazivanjeTreningaService.dto.TreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.TreningaDtoIn;
import org.springframework.stereotype.Component;

@Component
public class TreningMapper {

    public Trening treningDtoInToTrening(TreningaDtoIn treningaDtoIn){

        Trening trening = new Trening();
        trening.setNaziv(treningaDtoIn.getNaziv());
        return trening;
    }

    public TreningDtoOut treningToTreningDtoOut(Trening trening){

        TreningDtoOut treningDtoOut = new TreningDtoOut();
        treningDtoOut.setNaziv(trening.getNaziv());
        treningDtoOut.setIdTreninga(trening.getId());
        treningDtoOut.setTip(trening.getTipTreninga().getTip());
        return treningDtoOut;
    }

}
