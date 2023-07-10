package com.autoria.cadastroLegal.security.entity;

public enum UserRole {

    ADMIN("admin"),
    USER("usuario");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
