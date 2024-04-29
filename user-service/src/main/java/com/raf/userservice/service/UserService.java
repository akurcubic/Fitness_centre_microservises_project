package com.raf.userservice.service;

import com.raf.userservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    ZajednickiDto pronadjiKorisnika(Long id);
    ClientDto addClient(ClientCreateDto userCreateDto);

    ManagerDto addManager(ManagerCreateDto managerCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    UserDto updateUser(UserUpdateDto userUpdateDto);

    OmoguciPristupUseruDtoOut omoguciPristup(OmogociPristupUseruDtoIn omogociPristupUseruDtoIn);
    PromenaLoznikeDtoOut promeniLozinku(PromenaLoznikeDtoIn promenaLoznikeDtoIn);

    BrojZakazanihTreningaDto findBrojZakazanihTreninga(Long id);

    TipUseraDto findTipUsera(Long id);
    void povecajBrojZakazanihTreninga(IdUseraNapraviRezDto idUseraDto);
    void smanjiBrojZakazanihTreninga(IdUseraOtkaziRezDto idUseraOtkazi);
    Void aktivirajNalog(String aktivacijaString);


}
