package com.example.SpringTestTask.service;

import com.example.SpringTestTask.models.userModels.User;
import com.example.SpringTestTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public User findById(Long id){
        return userRepository.findById(id).get();
    }
    public  User save(User user) {
        return userRepository.save(user);
    }
}
