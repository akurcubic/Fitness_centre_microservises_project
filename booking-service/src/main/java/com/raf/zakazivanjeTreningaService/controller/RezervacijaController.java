package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.*;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.RezervacijaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

    private RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }


    @ApiOperation(value = "Otkazi rezervaciju")
    @PostMapping("/otkaziRezervaciju")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<Void> otkaziRezervaciju(@RequestHeader("Authorization") String authorization,@RequestBody @Valid OtkaziRezervacijuDto otkaziRezervacijuDto) {
        rezervacijaService.otkaziRezervaciju(otkaziRezervacijuDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Rezervacije za klijenta")
    @GetMapping("/{id}/rezervacijeZaKlijenta")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<RezervacijaListDto> getRezervacijeZaKlijenta(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id) {

        return new ResponseEntity<>(rezervacijaService.sveRezervacijeZaKlijenta(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Rezervacije za salu")
    @GetMapping("/{nazivSale}/rezervacijeZaSalu")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<RezervacijaListDto> getRezervacijeZaKlijenta(@RequestHeader("Authorization") String authorization,@PathVariable("nazivSale") String nazivSale) {

        return new ResponseEntity<>(rezervacijaService.sveRezervacijeZaSalu(nazivSale), HttpStatus.OK);
    }
    @ApiOperation(value = "Dodaj rezervaciju")
    @PostMapping("/addRezervacija")
    //@CheckSecurity(roles = {"ROLE_CLIENT"})
    public ResponseEntity<Void> dodajRezervaciju(@RequestBody @Valid RezervacijaDtoIn rezervacijaDtoIn) {
        rezervacijaService.addReservation(rezervacijaDtoIn);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
