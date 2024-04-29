package com.raf.zakazivanjeTreningaService.service;

import com.raf.zakazivanjeTreningaService.dto.OtkaziRezervacijuDto;
import com.raf.zakazivanjeTreningaService.dto.RezervacijaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.RezervacijaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.RezervacijaListDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public interface RezervacijaService {

    void addReservation(RezervacijaDtoIn rezervacijaDto);
    void otkaziRezervaciju(OtkaziRezervacijuDto otkaziRezervacijuDto);

    RezervacijaListDto sveRezervacijeZaKlijenta(Long id);
    RezervacijaListDto sveRezervacijeZaSalu(String nazivSale);


}
