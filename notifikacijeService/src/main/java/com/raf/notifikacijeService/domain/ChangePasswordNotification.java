package com.raf.notifikacijeService.domain;


import java.io.Serializable;


public class ChangePasswordNotification implements Serializable {
    private String email;
    private String username;
    private Long receiverId;

    @Override
    public String toString(){
        return "Postovani " + username + ",\n\n" +
                "Vas zahtev za promenu lozinke je uspesno obradjen.\n\n" +
                "Vas ZakazivanjeServis";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
