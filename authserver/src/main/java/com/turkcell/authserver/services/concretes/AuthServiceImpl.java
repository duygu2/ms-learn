package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.core.services.JwtService;
import com.turkcell.authserver.core.utils.exceptions.types.InvalidCredentialsException;
import com.turkcell.authserver.dtos.LoginRequest;
import com.turkcell.authserver.dtos.RegisterRequest;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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
    public String login(LoginRequest request) {
        try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }


        // add extra claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId",1);
        claims.put("Deneme", "Turkcell");


        return jwtService.generateToken(request.getEmail(),claims);

    }
}
