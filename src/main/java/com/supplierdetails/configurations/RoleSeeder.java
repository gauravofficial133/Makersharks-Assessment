package com.supplierdetails.configurations;

import com.supplierdetails.entities.Roles;
import com.supplierdetails.enums.RolesEnum;
import com.supplierdetails.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles(){
        RolesEnum[] roleNames = new RolesEnum[] { RolesEnum.USER, RolesEnum.ADMIN, RolesEnum.SUPER_ADMIN };
        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Roles> role = roleRepository.findByName(roleName);
            role.ifPresentOrElse(System.out::println, () -> {
                Roles roles = new Roles();
                roles.setName(roleName);
                roleRepository.save(roles);
            });
        });
    }
}
