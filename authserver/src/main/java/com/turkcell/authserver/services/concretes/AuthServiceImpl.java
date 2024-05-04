package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.core.utils.exceptions.types.InvalidCredentialsException;
import com.turkcell.authserver.dtos.LoginRequest;
import com.turkcell.authserver.dtos.RegisterRequest;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword())); //hassas bilgiler veritabanına plain text olarak yazılmaz

        userRepository.save(user);

    }

    @Override
    public void login(LoginRequest request) {
        try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

    }
}
