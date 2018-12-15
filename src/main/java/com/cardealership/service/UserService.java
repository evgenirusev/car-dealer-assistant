package com.cardealership.service;

import com.cardealership.domain.model.service.users.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void registerUser(UserServiceModel userServiceModel);
}