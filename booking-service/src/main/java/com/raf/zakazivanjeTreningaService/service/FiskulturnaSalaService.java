package com.raf.zakazivanjeTreningaService.service;

import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface FiskulturnaSalaService {


    Page<FiskulturnaSalaDtoOut> findAll(Pageable pageable);
    FiskulturnaSalaDtoOut add(FiskulturnaSalaDtoIn fiskulturnaSalaDtoIn);
    FiskulturnaSalaDtoOut update(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto);
    FiskulturnaSalaDtoOut pronadjiSaluPoNazivu(String nazivSale);

}
