package com.cardealership.service;

import com.cardealership.domain.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserServiceModel userServiceModel);
}