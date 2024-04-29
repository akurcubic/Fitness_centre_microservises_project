package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaUpdateDto;
import com.raf.zakazivanjeTreningaService.dto.RezervacijaListDto;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.FiskulturnaSalaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fiskulturnaSala")
public class FiskulturnaSalaController {

    private FiskulturnaSalaService fiskulturnaSalaService;

    public FiskulturnaSalaController(FiskulturnaSalaService fiskulturnaSalaService) {
        this.fiskulturnaSalaService = fiskulturnaSalaService;
    }

    @ApiOperation(value = "Izlistaj sve fiskulturne sale")
    @GetMapping("/all")
    public ResponseEntity<Page<FiskulturnaSalaDtoOut>> getAllFiskulturnaSala(Pageable pageable) {

        return new ResponseEntity<>(fiskulturnaSalaService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Dodaj salu")
    @PostMapping("/addFiskulturnaSala")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FiskulturnaSalaDtoOut> dodajSalu(@RequestHeader("Authorization") String authorization,@RequestBody @Valid FiskulturnaSalaDtoIn fiskulturnaSalaDtoIn) {
        return new ResponseEntity<>(fiskulturnaSalaService.add(fiskulturnaSalaDtoIn), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Promeni salu")
    @PostMapping("/updateFiskulturnaSala")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<FiskulturnaSalaDtoOut> updateFiskulturnaSala(@RequestHeader("Authorization") String authorization,@RequestBody @Valid FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        return new ResponseEntity<>(fiskulturnaSalaService.update(fiskulturnaSalaUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Pronadji salu")
    @GetMapping("/{nazivSale}/pronadjiSaluPoNazivu")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<FiskulturnaSalaDtoOut> getSalaPoNazivu(@RequestHeader("Authorization") String authorization,@PathVariable("nazivSale") String nazivSale) {

        return new ResponseEntity<>(fiskulturnaSalaService.pronadjiSaluPoNazivu(nazivSale), HttpStatus.OK);
    }
}
