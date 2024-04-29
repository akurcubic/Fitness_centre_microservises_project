package com.raf.zakazivanjeTreningaService.service.implementation;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSalaTrening;
import com.raf.zakazivanjeTreningaService.domain.Trening;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningListDto;
import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import com.raf.zakazivanjeTreningaService.mapper.FiskulturnaSalaTreningMapper;
import com.raf.zakazivanjeTreningaService.repository.FiskulturnaSalaRepository;
import com.raf.zakazivanjeTreningaService.repository.FiskulturnaSalaTreningRepository;
import com.raf.zakazivanjeTreningaService.repository.TreningRepository;
import com.raf.zakazivanjeTreningaService.service.FiskulturnaSalaTreningService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class FiskulturnaSalaTreningServiceImpl implements FiskulturnaSalaTreningService {

    private FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository;
    private FiskulturnaSalaTreningMapper fiskulturnaSalaTreningMapper;
    private TreningRepository treningRepository;
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    public FiskulturnaSalaTreningServiceImpl(FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository, FiskulturnaSalaTreningMapper fiskulturnaSalaTreningMapper, TreningRepository treningRepository, FiskulturnaSalaRepository fiskulturnaSalaRepository) {
        this.fiskulturnaSalaTreningRepository = fiskulturnaSalaTreningRepository;
        this.fiskulturnaSalaTreningMapper = fiskulturnaSalaTreningMapper;
        this.treningRepository = treningRepository;
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
    }

    @Override
    public Page<FiskulturnaSalaTreningDtoOut> findAll(Pageable pageable) {

        return fiskulturnaSalaTreningRepository.findAll(pageable).map(fiskulturnaSalaTreningMapper::fiskulturnaSalaTreningToFiskulturnaSalaTreningDtoOut);
    }

    @Override
    public FiskulturnaSalaTreningDtoOut add(FiskulturnaSalaTreningDtoIn fiskulturnaSalaTreningDtoIn) {

        FiskulturnaSalaTrening fiskulturnaSalaTrening = fiskulturnaSalaTreningMapper.fiskulturnaSalaTreningDtoIntoFiskulturnaSalaTrening(fiskulturnaSalaTreningDtoIn);
        String nazivTreninga = fiskulturnaSalaTreningDtoIn.getTrening();
        String nazivFiskulturnaSala = fiskulturnaSalaTreningDtoIn.getNazivSale();
        Trening trening = treningRepository.findTreningByNaziv(nazivTreninga).orElseThrow(() -> new NotFoundException(String.format("Trening sa nazivom: %s not found.", nazivTreninga)));
        FiskulturnaSala fiskulturnaSala = fiskulturnaSalaRepository.findFiskulturnaSalaByNaziv(nazivFiskulturnaSala).orElseThrow(() -> new NotFoundException(String.format("Sala sa nazivom: %s not found.", nazivFiskulturnaSala)));
        fiskulturnaSalaTrening.setTrening(trening);
        fiskulturnaSalaTrening.setFiskulturnaSala(fiskulturnaSala);
        fiskulturnaSalaTreningRepository.save(fiskulturnaSalaTrening);
        return fiskulturnaSalaTreningMapper.fiskulturnaSalaTreningToFiskulturnaSalaTreningDtoOut(fiskulturnaSalaTrening);
    }

    @Override
    public FiskulturnaSalaTreningListDto getTreninziZaSalu(String nazivSale) {

        FiskulturnaSalaTreningListDto fiskulturnaSalaTreningListDto = new FiskulturnaSalaTreningListDto();

        List<FiskulturnaSalaTreningDtoOut> list = new ArrayList<>();

        for(FiskulturnaSalaTrening fiskulturnaSalaTrening : fiskulturnaSalaTreningRepository.findAll()){

            if(fiskulturnaSalaTrening.getFiskulturnaSala().getNaziv().equals(nazivSale)){

                list.add(fiskulturnaSalaTreningMapper.fiskulturnaSalaTreningToFiskulturnaSalaTreningDtoOut(fiskulturnaSalaTrening));
            }
        }

        fiskulturnaSalaTreningListDto.setContent(list);
        return fiskulturnaSalaTreningListDto;
    }
}
