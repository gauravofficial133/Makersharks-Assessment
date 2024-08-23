package com.supplierdetails.services;

import com.supplierdetails.entities.Roles;
import com.supplierdetails.entities.User;
import com.supplierdetails.enums.RolesEnum;
import com.supplierdetails.repositories.RoleRepository;
import com.supplierdetails.repositories.UserRepository;
import com.supplierdetails.requests.LoginRequest;
import com.supplierdetails.responses.LoginResponse;
import com.supplierdetails.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    public void registerNewUser(){
        if (userRepository.findAll().isEmpty()) {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

            User user = new User();
            user.setUsername("Gaurav");
            user.setPassword(bCryptPasswordEncoder.encode("Test@123"));
            user.setEnabled(true);

            Roles adminRole = roleRepository.findByName(RolesEnum.ADMIN)
                    .orElseThrow(() -> new RuntimeException("ADMIN Role doesn't exist."));
            Roles userRole = roleRepository.findByName(RolesEnum.USER)
                    .orElseThrow(() -> new RuntimeException("USER Role doesn't exist."));

            Set<Roles> roles = new HashSet<>(Arrays.asList(adminRole, userRole));
            user.setRoles(roles);

            userRepository.save(user);
        }
    }

    public LoginResponse login(LoginRequest loginRequest){
        registerNewUser();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        LoginResponse loginResponse = new LoginResponse();

        if (authentication.isAuthenticated()){
            loginResponse.setAccessToken(jwtService.generateJwtToken(loginRequest.getUsername()));
        } else {
            loginResponse.setAccessToken("False");
        }

        return loginResponse;
    }
}
