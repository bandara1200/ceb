package com.sergain.electricitymeter.service;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Role;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.model.UserRole;
import com.sergain.electricitymeter.repository.UserRepository;
import com.sergain.electricitymeter.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserRoleRepository userRoleRepository;

    public UserService(
            UserRepository userRepository,
            RoleService roleService,
            UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void saveUser(User user){
        User existsUser = userRepository.findByUsername(user.getUsername());
        if(existsUser == null){
            Role role = new Role();
            role.setRoleName(user.getRoleName());
            Role savedRole = roleService.saveRole(role);
            User savedUser = userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setRole(savedRole);
            userRole.setUser(savedUser);
            userRoleRepository.save(userRole);
        }
    }

    public User getUser(String username) throws EntityNotFoundException {
        return Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
    }
}
