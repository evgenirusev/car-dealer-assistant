package com.cardealership.domain.model.service;

public class UserRoleServiceModel {
    private Long id;
    private String authority;

    public UserRoleServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}