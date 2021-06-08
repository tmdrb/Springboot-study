package com.google.tmdrb.testblog.model;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    MANAGER("manager");

    private String role;

    Role( String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
