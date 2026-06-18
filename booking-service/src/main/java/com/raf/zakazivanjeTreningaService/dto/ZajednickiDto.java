package com.raf.zakazivanjeTreningaService.dto;

public class ZajednickiDto {


    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;

    private String dateOfBirth;
    boolean aktivan = false;
    boolean zabranjen = false;
    private String role;
    private int brojClanskeKarte;
    private int brojZakazanihTreninga;
    private String nazivFiskulturneSale;
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

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public boolean isZabranjen() {
        return zabranjen;
    }

    public void setZabranjen(boolean zabranjen) {
        this.zabranjen = zabranjen;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public int getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    public void setBrojZakazanihTreninga(int brojZakazanihTreninga) {
        this.brojZakazanihTreninga = brojZakazanihTreninga;
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
