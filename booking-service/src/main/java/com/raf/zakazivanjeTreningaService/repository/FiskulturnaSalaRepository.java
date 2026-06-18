package com.raf.zakazivanjeTreningaService.repository;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiskulturnaSalaRepository extends JpaRepository<FiskulturnaSala,Long> {

    Optional<FiskulturnaSala> findFiskulturnaSalaByNaziv(String naziv);

}
