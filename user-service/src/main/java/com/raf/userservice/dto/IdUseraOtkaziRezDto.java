package com.raf.userservice.dto;

public class IdUseraOtkaziRezDto {

    private Long userId;

    public IdUseraOtkaziRezDto(){


    }

    public IdUseraOtkaziRezDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
