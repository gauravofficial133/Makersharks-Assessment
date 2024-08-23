package com.supplierdetails.repositories;

import com.supplierdetails.entities.Roles;
import com.supplierdetails.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(RolesEnum name);
}
