package com.cardealership.factories;

import com.cardealership.domain.entity.UserRole;

public final class UserRoleFactory {

    public UserRoleFactory() {}

    public final UserRole createUserRole(String authority) {
        UserRole userRole = new UserRole();

        userRole.setAuthority(authority);

        return userRole;
    }
}
