package com.example.pub.dtos;

public class RegisterRequestDTO {
    private final String username;
    private final String name;
    private final boolean isAdult;
    private final String password;
    private final int pocket;


    public RegisterRequestDTO(String username, String name, boolean isAdult, String password) {
        this.username = username;
        this.name = name;
        this.isAdult = isAdult;
        this.password = password;
        this.pocket = 0;
    }


    public String getName() {
        return name;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public int getPocket() {
        return pocket;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

