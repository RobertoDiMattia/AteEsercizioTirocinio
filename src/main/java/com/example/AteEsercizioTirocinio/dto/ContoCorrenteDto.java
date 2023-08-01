package com.example.AteEsercizioTirocinio.dto;

public class ContoCorrenteDto {

    private Long id;
    private Long userId;
    private String pan;

    public ContoCorrenteDto() {}

    public ContoCorrenteDto(Long id, Long userId, String pan) {
        this.id = id;
        this.userId = userId;
        this.pan = pan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
