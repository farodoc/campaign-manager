package com.example.campaignmanager.controller;

import com.example.campaignmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getUserBalance(@PathVariable Long id) {
        double balance = userService.getUserBalance(id);
        return ResponseEntity.ok(balance);
    }
}