package com.Elites.ResolveHub.controller;

import com.Elites.ResolveHub.model.User;
import com.Elites.ResolveHub.service.UserService;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }
    @PutMapping("/update")
    public User updateProfile(@RequestBody User updatedUser) {
        return userService.updateProfile(updatedUser);
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestBody Map<String, String> data) {
        userService.changePassword(
                data.get("email"),
                data.get("oldPassword"),
                data.get("newPassword")
        );
        return "Password updated";
    }
}