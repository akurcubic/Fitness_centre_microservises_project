package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.*;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.FiskulturnaSalaTreningService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fiskulturnaSalaTrening")
public class FiskulturnaSalaTreningController {

    private FiskulturnaSalaTreningService fiskulturnaSalaTreningService;

    public FiskulturnaSalaTreningController(FiskulturnaSalaTreningService fiskulturnaSalaTreningService) {
        this.fiskulturnaSalaTreningService = fiskulturnaSalaTreningService;
    }

    @ApiOperation(value = "Izlistaj sve fiskulturne sale i njihove treninge")
    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<FiskulturnaSalaTreningDtoOut>> getAllFiskulturnaSalaAndTrening(@RequestHeader("Authorization") String authorization,Pageable pageable) {

        return new ResponseEntity<>(fiskulturnaSalaTreningService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Dodaj trening za fiskulturnu salu")
    @PostMapping("/addFiskulturnaSalaAndTrening")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<FiskulturnaSalaTreningDtoOut> dodajTreningZaSalu(@RequestHeader("Authorization") String authorization,@RequestBody @Valid FiskulturnaSalaTreningDtoIn fiskulturnaSalaTreningDtoIn) {
        return new ResponseEntity<>(fiskulturnaSalaTreningService.add(fiskulturnaSalaTreningDtoIn), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Treninzi za salu")
    @GetMapping("/{nazivSale}/treninziZaSalu")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<FiskulturnaSalaTreningListDto> getTreninziZaSalu(@RequestHeader("Authorization") String authorization,@PathVariable("nazivSale") String nazivSale) {

        return new ResponseEntity<>(fiskulturnaSalaTreningService.getTreninziZaSalu(nazivSale), HttpStatus.OK);
    }
}
