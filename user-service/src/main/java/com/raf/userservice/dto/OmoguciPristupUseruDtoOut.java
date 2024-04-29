package com.raf.userservice.dto;

public class OmoguciPristupUseruDtoOut {

    private String username;
    private boolean zabranjen;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isZabranjen() {
        return zabranjen;
    }

    public void setZabranjen(boolean zabranjen) {
        this.zabranjen = zabranjen;
    }
}
