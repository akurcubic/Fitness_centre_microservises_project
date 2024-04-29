package com.raf.zakazivanjeTreningaService.service.implementation;


import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSalaTrening;
import com.raf.zakazivanjeTreningaService.domain.Termin;
import com.raf.zakazivanjeTreningaService.domain.Trening;
import com.raf.zakazivanjeTreningaService.dto.*;
import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import com.raf.zakazivanjeTreningaService.mapper.TerminMapper;
import com.raf.zakazivanjeTreningaService.repository.FiskulturnaSalaRepository;
import com.raf.zakazivanjeTreningaService.repository.FiskulturnaSalaTreningRepository;
import com.raf.zakazivanjeTreningaService.repository.TerminRepository;
import com.raf.zakazivanjeTreningaService.repository.TreningRepository;
import com.raf.zakazivanjeTreningaService.service.TerminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TerminServiceImpl implements TerminService {

    private TerminRepository terminRepository;
    private FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository;
    private TerminMapper terminMapper;
    private TreningRepository treningRepository;
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;


    public TerminServiceImpl(TerminRepository terminRepository, FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository, TerminMapper terminMapper, TreningRepository treningRepository, FiskulturnaSalaRepository fiskulturnaSalaRepository) {
        this.terminRepository = terminRepository;
        this.fiskulturnaSalaTreningRepository = fiskulturnaSalaTreningRepository;
        this.terminMapper = terminMapper;
        this.treningRepository = treningRepository;
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
    }

    @Override
    public Page<TerminDtoOut> findAll(Pageable pageable) {

        return terminRepository.findAll(pageable).map(terminMapper::terminToTerminDtoOut);
    }

    @Override
    public TerminDtoOut add(TerminDtoIn terminDtoIn) {

        Termin termin = terminMapper.terminDtoInToTermin(terminDtoIn);
        String nazivTreninga = terminDtoIn.getNazivTreninga();
        String nazivSale = terminDtoIn.getNazivSale();
        Trening trening = treningRepository.findTreningByNaziv(nazivTreninga).orElseThrow(() -> new NotFoundException(String.format("Trening sa nazivom: %s not found.", nazivTreninga)));
        FiskulturnaSala fiskulturnaSala = fiskulturnaSalaRepository.findFiskulturnaSalaByNaziv(nazivSale).orElseThrow(() -> new NotFoundException(String.format("Sala sa nazivom: %s not found.", nazivSale)));
        FiskulturnaSalaTrening fiskulturnaSalaTrening = fiskulturnaSalaTreningRepository.findFiskulturnaSalaTreningByFiskulturnaSalaAndTrening(fiskulturnaSala,trening).orElseThrow(() -> new NotFoundException(String.format("Sala sa nazivom: %s i treningom %s not found.", nazivSale,nazivTreninga)));
        termin.setFiskulturnaSalaTrening(fiskulturnaSalaTrening);
        termin.setTipTreninga(fiskulturnaSalaTrening.getTrening().getTipTreninga().getTip());
        termin.setCenaTreninga(fiskulturnaSalaTrening.getCena());
        termin.setKapacitet(fiskulturnaSalaTrening.getKapacitet());
        terminRepository.save(termin);
        return terminMapper.terminToTerminDtoOut(termin);
    }

    @Override
    public List<TerminDtoOut> filtrirajPoTipu(FilterTerminPoTipuTrenigaDto filterTerminPoTipuTrenigaDto) {

        String tipTreninga = filterTerminPoTipuTrenigaDto.getTipTreninga();
        List<TerminDtoOut> filtriranitermini = new ArrayList<>();
        for(Termin termin : terminRepository.findAll()){

            if(termin.getTipTreninga().equalsIgnoreCase(tipTreninga)){

                TerminDtoOut terminDtoOut = terminMapper.terminToTerminDtoOut(termin);
                filtriranitermini.add(terminDtoOut);
            }
        }
        return filtriranitermini;
    }

    @Override
    public List<TerminDtoOut> filtrirajPoDanu(FilterTerminePoDanuDto filterTerminePoDanuDto) {

        String dan = filterTerminePoDanuDto.getDan();
        List<TerminDtoOut> filtriranitermini = new ArrayList<>();
        for(Termin termin : terminRepository.findAll()){

            if(termin.getDan().equalsIgnoreCase(dan)){

                TerminDtoOut terminDtoOut = terminMapper.terminToTerminDtoOut(termin);
                filtriranitermini.add(terminDtoOut);
            }
        }
        return filtriranitermini;
    }

    @Override
    public TerminListDto terminiZaOdredjenuSalu(String nazivSale) {

        FiskulturnaSala fiskulturnaSala = fiskulturnaSalaRepository.findFiskulturnaSalaByNaziv(nazivSale).orElseThrow(() -> new NotFoundException(String.format("Sala sa nazivom: %s not found.", nazivSale)));
        List<TerminDtoOut> list = new ArrayList<>();

        for(Termin termin : terminRepository.findAll()){

            if(termin.getNazivSale().equals(fiskulturnaSala.getNaziv())){

                list.add(terminMapper.terminToTerminDtoOut(termin));
            }
        }
        TerminListDto terminListDto = new TerminListDto();
        terminListDto.setContent(list);
        return terminListDto;
    }
}
