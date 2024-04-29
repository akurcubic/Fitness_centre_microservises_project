package com.raf.zakazivanjeTreningaService.repository;

import com.raf.zakazivanjeTreningaService.domain.FiskulturnaSala;
import com.raf.zakazivanjeTreningaService.domain.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {

}
