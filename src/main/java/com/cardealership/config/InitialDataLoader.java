package com.cardealership.config;

import com.cardealership.domain.model.binding.users.CreateUserRoleBindingModel;
import com.cardealership.domain.model.service.users.UserRoleServiceModel;
import com.cardealership.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationRunner {

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    @Autowired
    public InitialDataLoader(UserRoleService userRoleService, ModelMapper modelMapper) {
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
    }

    public void run(ApplicationArguments args) {
        UserRoleServiceModel userRole = this.userRoleService.findByAuthority("USER");
        UserRoleServiceModel adminRole = this.userRoleService.findByAuthority("ADMIN");

        if (userRole == null) {
            CreateUserRoleBindingModel createUserRoleBindingModel = new CreateUserRoleBindingModel();
            createUserRoleBindingModel.setAuthority("USER");
            UserRoleServiceModel userRoleServiceModel = this.modelMapper.map(createUserRoleBindingModel, UserRoleServiceModel.class);
            this.userRoleService.addRole(userRoleServiceModel);
        }

        if (adminRole == null) {
            CreateUserRoleBindingModel createUserRoleBindingModel = new CreateUserRoleBindingModel();
            createUserRoleBindingModel.setAuthority("ADMIN");
            UserRoleServiceModel userRoleServiceModel = this.modelMapper.map(createUserRoleBindingModel, UserRoleServiceModel.class);
            this.userRoleService.addRole(userRoleServiceModel);
        }
    }
}