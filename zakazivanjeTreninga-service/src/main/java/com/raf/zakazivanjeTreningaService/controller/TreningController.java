package com.raf.zakazivanjeTreningaService.controller;


import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoIn;
import com.raf.zakazivanjeTreningaService.dto.FiskulturnaSalaDtoOut;
import com.raf.zakazivanjeTreningaService.dto.TreningDtoOut;
import com.raf.zakazivanjeTreningaService.dto.TreningaDtoIn;
import com.raf.zakazivanjeTreningaService.security.CheckSecurity;
import com.raf.zakazivanjeTreningaService.service.TreningService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trening")
public class TreningController {

    private TreningService treningService;

    public TreningController(TreningService treningService) {
        this.treningService = treningService;
    }

    @ApiOperation(value = "Izlistaj sve treninge")
    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<Page<TreningDtoOut>> getAllTrening(@RequestHeader("Authorization") String authorization,Pageable pageable) {

        return new ResponseEntity<>(treningService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Dodaj trening")
    @PostMapping("/addTrening")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<TreningDtoOut> dodajTrening(@RequestHeader("Authorization") String authorization,@RequestBody @Valid TreningaDtoIn treningaDtoIn) {
        return new ResponseEntity<>(treningService.add(treningaDtoIn), HttpStatus.CREATED);
    }
}
