package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSalaTrening;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoOut;
import org.springframework.stereotype.Component;

@Component
public class FiskulturnaSalaTreningMapper {

    public FiskulturnaSalaTrening fiskulturnaSalaTreningDtoIntoFiskulturnaSalaTrening(FiskulturnaSalaTreningDtoIn fiskulturnaSalaTreningDtoIn){

        FiskulturnaSalaTrening fst = new FiskulturnaSalaTrening();
        fst.setCena(fiskulturnaSalaTreningDtoIn.getCena());
        fst.setTrajanjeTreninga(fiskulturnaSalaTreningDtoIn.getTrajanjeTreninga());
        fst.setKapacitet(fiskulturnaSalaTreningDtoIn.getKapacitet());
        return fst;
    }

    public FiskulturnaSalaTreningDtoOut fiskulturnaSalaTreningToFiskulturnaSalaTreningDtoOut(FiskulturnaSalaTrening fiskulturnaSalaTrening){

        FiskulturnaSalaTreningDtoOut fiskulturnaSalaTreningDtoOut = new FiskulturnaSalaTreningDtoOut();
        fiskulturnaSalaTreningDtoOut.setId(fiskulturnaSalaTrening.getId());
        fiskulturnaSalaTreningDtoOut.setTrening(fiskulturnaSalaTrening.getTrening().getNaziv());
        fiskulturnaSalaTreningDtoOut.setNazivSale(fiskulturnaSalaTrening.getFiskulturnaSala().getNaziv());
        fiskulturnaSalaTreningDtoOut.setCena(fiskulturnaSalaTrening.getCena());
        fiskulturnaSalaTreningDtoOut.setTrajanjeTreninga(fiskulturnaSalaTrening.getTrajanjeTreninga());
        fiskulturnaSalaTreningDtoOut.setKapacitet(fiskulturnaSalaTrening.getKapacitet());
        return fiskulturnaSalaTreningDtoOut;
    }
}
