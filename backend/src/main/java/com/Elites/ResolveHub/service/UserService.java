package com.Elites.ResolveHub.service;

import com.Elites.ResolveHub.model.User;
import com.Elites.ResolveHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // ✅ Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ METHOD (outside constructor)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // REGISTER
    public User registerUser(User user) {
        if (userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // LOGIN
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }


public User findByEmail(String email) {
    return userRepository.findByEmailIgnoreCase(email.trim())
            .orElseThrow(() -> new RuntimeException("User not found: " + email));

    }
public User updateProfile(User updatedUser) {
    User user = userRepository.findByEmailIgnoreCase(updatedUser.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setFirstName(updatedUser.getFirstName());
    user.setLastName(updatedUser.getLastName());
    user.setMobile(updatedUser.getMobile());

    return userRepository.save(user);
}
public void changePassword(String email, String oldPassword, String newPassword) {
    User user = userRepository.findByEmailIgnoreCase(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(oldPassword)) {
        throw new RuntimeException("Old password is incorrect");
    }

    user.setPassword(newPassword);
    userRepository.save(user);
}
}