package com.raf.zakazivanjeTreningaService.service;


import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface TipTreningaService {

    Page<TipTreningaDtoOut> findAll(Pageable pageable);
    TipTreningaDtoOut add(TipTreningaDtoIn tipTreningaDtoIn);
}
