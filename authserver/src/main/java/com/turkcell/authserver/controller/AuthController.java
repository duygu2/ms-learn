package com.turkcell.authserver.controller;

import com.turkcell.authserver.dtos.LoginRequest;
import com.turkcell.authserver.dtos.RegisterRequest;
import com.turkcell.authserver.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest request){
        authService.register(request);
    }

    @PostMapping("login")
    public void login(@RequestBody LoginRequest request){
        authService.login(request);
    }
}
