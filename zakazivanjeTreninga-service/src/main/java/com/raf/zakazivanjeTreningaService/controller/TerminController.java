package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.*;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.TerminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/termin")
public class TerminController {

    private TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @ApiOperation(value = "Izlistaj sve termine")
    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<TerminDtoOut>> getAllTermini(@RequestHeader("Authorization") String authorization,Pageable pageable) {

        return new ResponseEntity<>(terminService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Dodaj termin")
    @PostMapping("/addTermin")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<TerminDtoOut> dodajTermin(@RequestHeader("Authorization") String authorization,@RequestBody @Valid TerminDtoIn terminDtoIn) {
        return new ResponseEntity<>(terminService.add(terminDtoIn), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Filtriraj termine po tipu treninga")
    @GetMapping("/filterPoTipu")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<List<TerminDtoOut>> filtriraniPoTipuTreninga(@RequestHeader("Authorization") String authorization,FilterTerminPoTipuTrenigaDto filterTerminPoTipuTrenigaDto) {

        return new ResponseEntity<>(terminService.filtrirajPoTipu(filterTerminPoTipuTrenigaDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Termini za odredjenu salu")
    @GetMapping("/{nazivSale}/terminZaOdredjenuSalu")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<TerminListDto> getTerminiZaOdredjenuSalu(@RequestHeader("Authorization") String authorization,@PathVariable("nazivSale") String nazivSale) {

        return new ResponseEntity<>(terminService.terminiZaOdredjenuSalu(nazivSale), HttpStatus.OK);
    }

    @ApiOperation(value = "Filtriraj termine po danu")
    @GetMapping("/filterPoDanu")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<List<TerminDtoOut>> filtriraniPoDanu(@RequestHeader("Authorization") String authorization,FilterTerminePoDanuDto filterTerminePoDanuDto) {

        return new ResponseEntity<>(terminService.filtrirajPoDanu(filterTerminePoDanuDto), HttpStatus.OK);
    }

}
