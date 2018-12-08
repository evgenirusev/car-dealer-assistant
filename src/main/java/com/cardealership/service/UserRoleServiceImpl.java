package com.cardealership.service;

import com.cardealership.domain.entity.UserRole;
import com.cardealership.domain.model.service.users.UserRoleServiceModel;
import com.cardealership.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRoleServiceModel findByAuthority(String authority) {
        UserRole userRoleEntity = this.userRoleRepository.getUserRoleByAuthority(authority);
        UserRoleServiceModel serviceModel = null;
        if (userRoleEntity != null){
            serviceModel = this.modelMapper.map(userRoleEntity, UserRoleServiceModel.class);
        }
        return serviceModel;
    }

    @Override
    public void addRole(UserRoleServiceModel userRoleServiceModel) {
        UserRole userRoleEntity = this.modelMapper.map(userRoleServiceModel, UserRole.class);
        this.userRoleRepository.save(userRoleEntity);
    }
}
