package com.raf.userservice.domain;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "UserType")
public class User {

    private static int brojac = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    private String dateOfBirth;

    boolean aktivan = false;
    boolean zabranjen = false;
    private String stringZaAktivaciju = "string" + brojac++;

    @ManyToOne(optional = false)
    private Role role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStringZaAktivaciju() {
        return stringZaAktivaciju;
    }

    public void setStringZaAktivaciju(String stringZaAktivaciju) {
        this.stringZaAktivaciju = stringZaAktivaciju;
    }
}
