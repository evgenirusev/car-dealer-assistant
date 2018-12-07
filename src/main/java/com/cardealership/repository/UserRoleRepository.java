package com.cardealership.repository;

import com.cardealership.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    UserRole getUserRoleByAuthority(String authority);
}
