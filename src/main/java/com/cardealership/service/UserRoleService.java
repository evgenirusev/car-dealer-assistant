package com.cardealership.service;

import com.cardealership.domain.model.service.users.UserRoleServiceModel;

public interface UserRoleService {

    UserRoleServiceModel findByAuthority(String authority);

    void addRole(UserRoleServiceModel roleDto);
}