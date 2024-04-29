package com.raf.userservice.controller;


import com.raf.userservice.dto.*;
import com.raf.userservice.security.CheckSecurity;
import com.raf.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                  Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/brojZakazanihTreninga")
    //@CheckSecurity(roles = {"ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<BrojZakazanihTreningaDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findBrojZakazanihTreninga(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tipUsera")
    public ResponseEntity<TipUseraDto> getTipUsera(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findTipUsera(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Pronadji korisnika")
    @GetMapping("/{id}/pronadjiKorisnika")
    public ResponseEntity<ZajednickiDto> pronadjiKorisnika(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.pronadjiKorisnika(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Register client")
    @PostMapping("/registerClient")
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.addClient(userCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Register manager")
    @PostMapping("/registerManager")
    public ResponseEntity<ManagerDto> addManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update User")
    @PostMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return new ResponseEntity<>(userService.updateUser(userUpdateDto), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @ApiOperation(value = "Omoguci pristupUser-u")
    @PostMapping("/omoguciPristup")
    public ResponseEntity<OmoguciPristupUseruDtoOut> omoguciPristup(@RequestHeader("Authorization") String authorization,@RequestBody @Valid OmogociPristupUseruDtoIn omogociPristupUseruDtoIn) {
        return new ResponseEntity<>(userService.omoguciPristup(omogociPristupUseruDtoIn), HttpStatus.OK);
    }

    @ApiOperation(value = "Promena lozinke")
    @PostMapping("/promeniLozinku")
    public ResponseEntity<PromenaLoznikeDtoOut> promeniLozniku(@RequestBody @Valid PromenaLoznikeDtoIn promenaLoznikeDtoIn) {
        return new ResponseEntity<>(userService.promeniLozinku(promenaLoznikeDtoIn), HttpStatus.OK);
    }

    @ApiOperation(value = "Aktiviraj nalog")
    @GetMapping("/{string}/activate")
    public ResponseEntity<Void> pronadjiKorisnika(@PathVariable("string") String string) {
        return new ResponseEntity<>(userService.aktivirajNalog(string), HttpStatus.OK);
    }




}
