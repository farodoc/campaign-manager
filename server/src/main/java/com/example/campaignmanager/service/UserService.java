package com.example.campaignmanager.service;

import com.example.campaignmanager.model.User;
import com.example.campaignmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public double getUserBalance(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getBalance();
        }
        return 0;
    }

    public void updateUserBalance(Long userId, Double money) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setBalance(updatedUser.getBalance() + money);
            userRepository.save(updatedUser);
        }
    }
}