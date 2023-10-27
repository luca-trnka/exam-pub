package com.example.pub.models;

import com.example.pub.dtos.RegisterRequestDTO;
import com.example.pub.repos.UserRepo;
import com.example.pub.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultBartender implements CommandLineRunner {

    private final UserRepo userRepo;

    public DefaultBartender (UserService userService, UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        User defaultBartender = new User("bartender007", "Bartender", true, "topsecretpassword");
        defaultBartender.setRole(Role.BARTENDER);
        userRepo.save(defaultBartender);
    }
}
