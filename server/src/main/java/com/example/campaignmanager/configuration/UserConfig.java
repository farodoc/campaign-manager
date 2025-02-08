package com.example.campaignmanager.configuration;

import com.example.campaignmanager.model.User;
import com.example.campaignmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    private final UserRepository userRepository;

    public UserConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner addSampleUser() {
        return args -> {
            User user = User.builder().balance(100.0).build();
            userRepository.save(user);
        };
    }
}
