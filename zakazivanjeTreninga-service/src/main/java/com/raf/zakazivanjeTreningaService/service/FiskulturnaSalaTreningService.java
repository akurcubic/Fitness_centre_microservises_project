package com.raf.zakazivanjeTreningaService.service;

import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaTreningListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface FiskulturnaSalaTreningService {

    Page<FiskulturnaSalaTreningDtoOut> findAll(Pageable pageable);
    FiskulturnaSalaTreningDtoOut add(FiskulturnaSalaTreningDtoIn fiskulturnaSalaTreningDtoIn);
    FiskulturnaSalaTreningListDto getTreninziZaSalu(String nazivSale);

}
