package com.raf.zakazivanjeTreningaService.service.implementation;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaUpdateDto;
import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import com.raf.zakazivanjeTreningaService.mapper.FiskulturnaSalaMapper;
import com.raf.zakazivanjeTreningaService.repository.FiskulturnaSalaRepository;
import com.raf.zakazivanjeTreningaService.service.FiskulturnaSalaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class FiskulturnaSalaServiceImpl implements FiskulturnaSalaService {

    private FiskulturnaSalaRepository fiskulturnaSalaRepository;
    private FiskulturnaSalaMapper fiskulturnaSalaMapper;


    public FiskulturnaSalaServiceImpl(FiskulturnaSalaRepository fiskulturnaSalaRepository, FiskulturnaSalaMapper fiskulturnaSalaMapper) {
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
        this.fiskulturnaSalaMapper = fiskulturnaSalaMapper;
    }

    @Override
    public Page<FiskulturnaSalaDtoOut> findAll(Pageable pageable) {

        return fiskulturnaSalaRepository.findAll(pageable)
                .map(fiskulturnaSalaMapper::fiskulturnaSalatoFiskulturnaSalaDtoOut);
    }

    @Override
    public FiskulturnaSalaDtoOut add(FiskulturnaSalaDtoIn fiskulturnaSalaDtoIn) {

        FiskulturnaSala sala = fiskulturnaSalaMapper.fiskulturnaSalaDtoInToFiskulturnaSala(fiskulturnaSalaDtoIn);
        fiskulturnaSalaRepository.save(sala);
        return fiskulturnaSalaMapper.fiskulturnaSalatoFiskulturnaSalaDtoOut(sala);
    }

    @Override
    public FiskulturnaSalaDtoOut update(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {

        FiskulturnaSala sala = fiskulturnaSalaRepository.findFiskulturnaSalaByNaziv(fiskulturnaSalaUpdateDto.getStariNaziv()).orElseThrow(() -> new NotFoundException(String.format("Sala with username: %s not found.", fiskulturnaSalaUpdateDto.getStariNaziv())));
        sala = fiskulturnaSalaMapper.fiskulturnaSalaUpdateDtoToFiskulturnaSala(fiskulturnaSalaUpdateDto,sala);
        fiskulturnaSalaRepository.save(sala);
        return fiskulturnaSalaMapper.fiskulturnaSalatoFiskulturnaSalaDtoOut(sala);
    }

    @Override
    public FiskulturnaSalaDtoOut pronadjiSaluPoNazivu(String nazivSale) {

        FiskulturnaSala sala = fiskulturnaSalaRepository.findFiskulturnaSalaByNaziv(nazivSale).orElseThrow(() -> new NotFoundException(String.format("Sala with username: %s not found.", nazivSale)));
        return fiskulturnaSalaMapper.fiskulturnaSalatoFiskulturnaSalaDtoOut(sala);

    }
}
