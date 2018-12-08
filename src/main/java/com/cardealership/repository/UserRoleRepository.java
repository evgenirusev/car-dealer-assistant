package com.cardealership.repository;

import com.cardealership.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole getUserRoleByAuthority(String authority);
}