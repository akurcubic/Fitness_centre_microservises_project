package com.raf.zakazivanjeTreningaService.service;

import com.raf.zakazivanjeTreningaService.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public interface TerminService {

    Page<TerminDtoOut> findAll(Pageable pageable);
    TerminDtoOut add(TerminDtoIn terminDtoIn);
    List<TerminDtoOut> filtrirajPoTipu(FilterTerminPoTipuTrenigaDto filterTerminPoTipuTrenigaDto);
    List<TerminDtoOut> filtrirajPoDanu(FilterTerminePoDanuDto filterTerminePoDanuDto);
    TerminListDto terminiZaOdredjenuSalu(String nazivSale);
}
