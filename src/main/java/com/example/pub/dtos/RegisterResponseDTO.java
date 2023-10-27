package com.example.pub.dtos;

public class RegisterResponseDTO {
    private final String status;

    public RegisterResponseDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}