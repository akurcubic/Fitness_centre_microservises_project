package com.raf.zakazivanjeTreningaService.client.userservice.dto;

public class IdUseraNapraviRezDto {

    public IdUseraNapraviRezDto() {
    }

    public IdUseraNapraviRezDto(Long userId) {
        this.userId = userId;
    }

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
