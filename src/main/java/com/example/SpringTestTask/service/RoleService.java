package com.example.SpringTestTask.service;

import com.example.SpringTestTask.models.userModels.ERole;
import com.example.SpringTestTask.models.userModels.Role;
import com.example.SpringTestTask.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Optional<Role> findByName(ERole name){
        return roleRepository.findByName(name);
    }
}
