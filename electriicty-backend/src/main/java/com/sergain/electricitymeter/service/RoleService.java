package com.sergain.electricitymeter.service;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Role;
import com.sergain.electricitymeter.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role){
       return roleRepository.save(role);
    }

    public Role getRole(Role role) throws EntityNotFoundException {
        return Optional.ofNullable(roleRepository.findByRoleName(role.getRoleName())).orElseThrow(() ->
                new EntityNotFoundException("Role not found"));
    }
}
