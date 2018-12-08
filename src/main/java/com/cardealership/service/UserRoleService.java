package com.cardealership.service;

import com.cardealership.domain.model.service.UserRoleServiceModel;

public interface UserRoleService {

    UserRoleServiceModel findByAuthority(String authority);

    void addRole(UserRoleServiceModel roleDto);
}