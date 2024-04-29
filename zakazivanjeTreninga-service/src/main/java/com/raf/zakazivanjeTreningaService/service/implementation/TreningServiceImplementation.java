package com.raf.zakazivanjeTreningaService.service.implementation;

import com.raf.zakazivanjeTreningaService.domain.TipTreninga;
import com.raf.zakazivanjeTreningaService.domain.Trening;
import com.raf.zakazivanjeTreningaService.dto.TreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.TreningaDtoIn;
import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import com.raf.zakazivanjeTreningaService.mapper.TreningMapper;
import com.raf.zakazivanjeTreningaService.repository.TipTreningaRepository;
import com.raf.zakazivanjeTreningaService.repository.TreningRepository;
import com.raf.zakazivanjeTreningaService.service.TreningService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service
public class TreningServiceImplementation implements TreningService {

    private TreningRepository treningRepository;
    private TreningMapper treningMapper;
    private TipTreningaRepository tipTreningaRepository;

    public TreningServiceImplementation(TreningRepository treningRepository, TreningMapper treningMapper, TipTreningaRepository tipTreningaRepository) {
        this.treningRepository = treningRepository;
        this.treningMapper = treningMapper;
        this.tipTreningaRepository = tipTreningaRepository;
    }

    @Override
    public Page<TreningDtoOut> findAll(Pageable pageable) {
        return treningRepository.findAll(pageable).map(treningMapper::treningToTreningDtoOut);
    }

    @Override
    public TreningDtoOut add(TreningaDtoIn treningaDtoIn) {

        Trening trening = treningMapper.treningDtoInToTrening(treningaDtoIn);
        String tipTreniga = treningaDtoIn.getTip();
        TipTreninga tipTreninga = tipTreningaRepository.findTipTreningaByTip(tipTreniga).orElseThrow(() -> new NotFoundException(String.format("Tiptreninga sa nazivom: %s not found.", treningaDtoIn.getTip())));
        trening.setTipTreninga(tipTreninga);
        treningRepository.save(trening);
        return treningMapper.treningToTreningDtoOut(trening);
    }
}
