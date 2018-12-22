package com.cardealership.service;

import com.cardealership.domain.entity.User;
import com.cardealership.domain.entity.UserRole;
import com.cardealership.domain.model.service.users.UserRoleServiceModel;
import com.cardealership.domain.model.service.users.UserServiceModel;
import com.cardealership.repository.UserRepository;
import com.cardealership.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.getByUsername(username);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        Set<UserRole> authorities = new HashSet<>();
        UserRoleServiceModel roleServiceModel = this.userRoleService.findByAuthority(userServiceModel.isAdmin() ? "ADMIN" : "USER");
        UserRole role = this.modelMapper.map(roleServiceModel, UserRole.class);
        authorities.add(role);
        userEntity.setAuthorities(authorities);
        this.userRepository.save(userEntity);
    }
}