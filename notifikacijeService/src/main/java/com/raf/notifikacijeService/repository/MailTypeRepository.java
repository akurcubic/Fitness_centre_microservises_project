package com.raf.notifikacijeService.repository;

import com.raf.notifikacijeService.domain.MailType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailTypeRepository extends JpaRepository<MailType, Long> {


}
