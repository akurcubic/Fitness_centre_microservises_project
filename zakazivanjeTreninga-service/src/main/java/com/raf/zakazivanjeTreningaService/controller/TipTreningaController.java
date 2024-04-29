package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.TipTreningaDtoOut;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.TipTreningaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tipTreninga")
public class TipTreningaController {

    private TipTreningaService tipTreningaService;

    public TipTreningaController(TipTreningaService tipTreningaService) {
        this.tipTreningaService = tipTreningaService;
    }

    @ApiOperation(value = "Izlistaj sve tipove treninga")
    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<Page<TipTreningaDtoOut>> getAllTipTreninga(@RequestHeader("Authorization") String authorization,Pageable pageable) {

        return new ResponseEntity<>(tipTreningaService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Dodaj tip treninga")
    @PostMapping("/addTipTreninga")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<TipTreningaDtoOut> dodajTipTreninga(@RequestHeader("Authorization") String authorization,@RequestBody @Valid TipTreningaDtoIn tipTreningaDtoIn) {
        return new ResponseEntity<>(tipTreningaService.add(tipTreningaDtoIn), HttpStatus.CREATED);
    }
}
