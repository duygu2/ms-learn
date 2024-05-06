package com.turkcell.authserver.services.abstracts;

import com.turkcell.authserver.dtos.LoginRequest;
import com.turkcell.authserver.dtos.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
}
