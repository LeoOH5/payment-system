package com.sparta.paymentsystem.controller;

import com.sparta.paymentsystem.entity.Users;
import com.sparta.paymentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class DatabaseTestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public Users createUser(@RequestParam String email,
                            @RequestParam String passwordHash,
                            @RequestParam(required = false) String name) {
        Users user = new Users(email, passwordHash, name);
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public Optional<Users> getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @GetMapping("/user/email/{email}")
    public Optional<Users> getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    @PutMapping("/user/{userId}")
    public Users updateUser(@PathVariable Long userId,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String passwordHash,
                           @RequestParam(required = false) String name) {
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (email != null) user.setEmail(email);
            if (passwordHash != null) user.setPasswordHash(passwordHash);
            if (name != null) user.setName(name);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}
