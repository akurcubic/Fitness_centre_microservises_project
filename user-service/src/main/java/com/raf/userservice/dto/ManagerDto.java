package com.raf.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ManagerDto {

    @NotBlank
    private Long id;
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String nazivFiskulturneSale;
    @NotBlank
    private String datumZaposljavanja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNazivFiskulturneSale() {
        return nazivFiskulturneSale;
    }

    public void setNazivFiskulturneSale(String nazivFiskulturneSale) {
        this.nazivFiskulturneSale = nazivFiskulturneSale;
    }

    public String getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(String datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }
}
