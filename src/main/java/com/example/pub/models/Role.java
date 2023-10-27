package com.example.pub.models;

public enum Role {
    USER("ROLE_USER"),
    BARTENDER("ROLE_BARTENDER");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
