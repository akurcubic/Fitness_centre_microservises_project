package com.raf.zakazivanjeTreningaService.repository;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.domain.Trening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreningRepository extends JpaRepository<Trening,Long> {

    Optional<Trening> findTreningByNaziv(String naziv);

}
