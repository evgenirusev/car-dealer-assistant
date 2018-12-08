package com.cardealership.domain.model.binding;

public class CreateUserRoleBindingModel {
    private String authority;

    public CreateUserRoleBindingModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
