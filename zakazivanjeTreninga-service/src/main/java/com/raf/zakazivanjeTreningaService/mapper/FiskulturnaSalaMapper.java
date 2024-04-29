package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class FiskulturnaSalaMapper {


    public FiskulturnaSala fiskulturnaSalaDtoInToFiskulturnaSala(FiskulturnaSalaDtoIn fiskulturnaSalaDtoIn){

        FiskulturnaSala sala = new FiskulturnaSala();
        sala.setNaziv(fiskulturnaSalaDtoIn.getNaziv());
        sala.setOpis(fiskulturnaSalaDtoIn.getOpis());
        sala.setBrojTrenera(fiskulturnaSalaDtoIn.getBrojTrenera());
        sala.setPocetakRadnogVremena(fiskulturnaSalaDtoIn.getPocetakRadnogVremena());
        sala.setKrajRadnogVremena(fiskulturnaSalaDtoIn.getKrajRadnogVremena());
        sala.setPogodnostZaVereneKlijente(fiskulturnaSalaDtoIn.getPogodnostZaVereneKlijente());
        return sala;
    }

    public FiskulturnaSalaDtoOut fiskulturnaSalatoFiskulturnaSalaDtoOut(FiskulturnaSala fiskulturnaSala){

        FiskulturnaSalaDtoOut sala = new FiskulturnaSalaDtoOut();
        sala.setSalaId(fiskulturnaSala.getSalaId());
        sala.setNaziv(fiskulturnaSala.getNaziv());
        sala.setOpis(fiskulturnaSala.getOpis());
        sala.setBrojTrenera(fiskulturnaSala.getBrojTrenera());
        sala.setPocetakRadnogVremena(fiskulturnaSala.getPocetakRadnogVremena());
        sala.setKrajRadnogVremena(fiskulturnaSala.getKrajRadnogVremena());
        sala.setPogodnostZaVereneKlijente(fiskulturnaSala.getPogodnostZaVereneKlijente());
        sala.setManagerId(fiskulturnaSala.getManagerId());
        return sala;
    }

    public FiskulturnaSala fiskulturnaSalaUpdateDtoToFiskulturnaSala(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto, FiskulturnaSala fiskulturnaSala){

        fiskulturnaSala.setNaziv(fiskulturnaSalaUpdateDto.getNaziv());
        fiskulturnaSala.setOpis(fiskulturnaSalaUpdateDto.getOpis());
        fiskulturnaSala.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrojTrenera());
        fiskulturnaSala.setPocetakRadnogVremena(fiskulturnaSalaUpdateDto.getPocetakRadnogVremena());
        fiskulturnaSala.setKrajRadnogVremena(fiskulturnaSalaUpdateDto.getKrajRadnogVremena());
        fiskulturnaSala.setPogodnostZaVereneKlijente(fiskulturnaSalaUpdateDto.getPogodnostZaVereneKlijente());
        return fiskulturnaSala;
    }

}
