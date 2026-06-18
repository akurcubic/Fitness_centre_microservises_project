package com.raf.zakazivanjeTreningaService.service;

import com.raf.zakazivanjeTreningaService.dto.TreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.TreningaDtoIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface TreningService {

    Page<TreningDtoOut> findAll(Pageable pageable);
    TreningDtoOut add(TreningaDtoIn treningaDtoIn);

}
