package com.raf.zakazivanjeTreningaService.repository;


import com.raf.zakazivanjeTreningaService.domain.TipTreninga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipTreningaRepository extends JpaRepository<TipTreninga,Long> {

    Optional<TipTreninga> findTipTreningaByTip(String tip);
}
