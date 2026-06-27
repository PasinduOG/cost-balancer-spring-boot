package dev.pasindu.costbalancer.controller;

import dev.pasindu.costbalancer.dto.LoginRequest;
import dev.pasindu.costbalancer.dto.LoginResponse;
import dev.pasindu.costbalancer.dto.RegisterRequest;
import dev.pasindu.costbalancer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.loginUser(request);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequest request) {
        return service.registerUser(request);
    }
}
