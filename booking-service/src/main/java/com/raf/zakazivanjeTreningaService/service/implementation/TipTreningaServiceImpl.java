package com.raf.zakazivanjeTreningaService.service.implementation;

import com.raf.zakazivanjeTreningaService.domain.TipTreninga;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoOut;
import com.raf.zakazivanjeTreningaService.mapper.TipTreningaMapper;
import com.raf.zakazivanjeTreningaService.repository.TipTreningaRepository;
import com.raf.zakazivanjeTreningaService.service.TipTreningaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TipTreningaServiceImpl implements TipTreningaService {

    private TipTreningaRepository tipTreningaRepository;
    private TipTreningaMapper tipTreningaMapper;

    public TipTreningaServiceImpl(TipTreningaRepository tipTreningaRepository, TipTreningaMapper tipTreningaMapper) {
        this.tipTreningaRepository = tipTreningaRepository;
        this.tipTreningaMapper = tipTreningaMapper;
    }

    @Override
    public Page<TipTreningaDtoOut> findAll(Pageable pageable) {

        return tipTreningaRepository.findAll(pageable).map(tipTreningaMapper::tipTreningaToTipTreningaDtoOut);
    }

    @Override
    public TipTreningaDtoOut add(TipTreningaDtoIn tipTreningaDtoIn) {

        TipTreninga tipTreninga = tipTreningaMapper.tipTreningaDtoInToTipTreninga(tipTreningaDtoIn);
        tipTreningaRepository.save(tipTreninga);
        return tipTreningaMapper.tipTreningaToTipTreningaDtoOut(tipTreninga);
    }
}
