package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.Termin;
import com.raf.zakazivanjeTreningaService.dto.TerminDtoIn;
import com.raf.zakazivanjeTreningaService.dto.TerminDtoOut;
import org.springframework.stereotype.Component;

@Component
public class TerminMapper {

    public Termin terminDtoInToTermin(TerminDtoIn terminDtoIn){

        Termin termin = new Termin();
        termin.setNazivSale(terminDtoIn.getNazivSale());
        termin.setNazivTreninga(terminDtoIn.getNazivTreninga());
        termin.setDatum(terminDtoIn.getDatum());
        termin.setDan(terminDtoIn.getDan());
        termin.setVremeDo(terminDtoIn.getVremeDo());
        termin.setVremeOd(terminDtoIn.getVremeOd());
        return termin;
    }

    public TerminDtoOut terminToTerminDtoOut(Termin termin){

        TerminDtoOut terminDtoOut = new TerminDtoOut();
        terminDtoOut.setIdTermina(termin.getIdTermina());
        terminDtoOut.setNazivSale(termin.getNazivSale());
        terminDtoOut.setNazivTreninga(termin.getNazivTreninga());
        terminDtoOut.setTipTreninga(termin.getTipTreninga());
        terminDtoOut.setCenaTreninga(termin.getCenaTreninga());
        terminDtoOut.setDatum(termin.getDatum());
        terminDtoOut.setDan(termin.getDan());
        terminDtoOut.setVremeDo(termin.getVremeDo());
        terminDtoOut.setVremeOd(termin.getVremeOd());
        terminDtoOut.setKapacitet(termin.getKapacitet());
        terminDtoOut.setDostupan(termin.isDostupan());
        return terminDtoOut;
    }
}
