package com.raf.zakazivanjeTreningaService.mapper;

import com.raf.zakazivanjeTreningaService.domain.Rezervacija;
import com.raf.zakazivanjeTreningaService.dto.RezervacijaDtoOut;
import org.springframework.stereotype.Component;

@Component
public class RezervacijaMapper {

    public RezervacijaDtoOut rezervacijaToRezervacijaDtoOut(Rezervacija rezervacija){

        RezervacijaDtoOut rezervacijaDtoOut = new RezervacijaDtoOut();
        rezervacijaDtoOut.setIdRezervacije(rezervacija.getIdRezervacije());
        rezervacijaDtoOut.setIdTermina(rezervacija.getTermin().getIdTermina());
        rezervacijaDtoOut.setNazivSale(rezervacija.getTermin().getNazivSale());
        rezervacijaDtoOut.setNazivTreninga(rezervacija.getTermin().getNazivTreninga());
        rezervacijaDtoOut.setTipTreninga(rezervacija.getTermin().getTipTreninga());
        rezervacijaDtoOut.setCenaTreninga(rezervacija.getCena());
        rezervacijaDtoOut.setDatum(rezervacija.getTermin().getDatum());
        rezervacijaDtoOut.setDan(rezervacija.getTermin().getDan());
        rezervacijaDtoOut.setVremeOd(rezervacija.getTermin().getVremeOd());
        rezervacijaDtoOut.setVremeDo(rezervacija.getTermin().getVremeDo());
        return rezervacijaDtoOut;

    }
}
