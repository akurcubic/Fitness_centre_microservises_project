package com.raf.zakazivanjeTreningaService.repository;

import com.raf.zakazivanjeTreningaService.domain.Rezervacija;
import com.raf.zakazivanjeTreningaService.domain.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {


    Optional<Termin> findTerminByIdTermina(Long idTermina);

}
