package com.turkcell.authserver.services.concretes;

import com.turkcell.authserver.core.services.JwtService;
import com.turkcell.authserver.core.utils.exceptions.types.InvalidCredentialsException;
import com.turkcell.authserver.dtos.LoginRequest;
import com.turkcell.authserver.dtos.RegisterRequest;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.AuthService;
import com.turkcell.authserver.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

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

        UserDetails user= userService.loadUserByUsername(request.getEmail());

        // add extra claims
        Map<String, Object> claims = new HashMap<>();
        List<String> roles= jwtService.extractRolesFromJwt(user);
        claims.put("roles", roles);

        return jwtService.generateToken(request.getEmail(),claims);

    }
}
