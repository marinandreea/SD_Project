package com.example.Assignment_A3.auth;

import com.example.Assignment_A3.config.JwtService;
import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.repository.UserRepository;
import com.example.Assignment_A3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.Assignment_A3.model.Role.CASHIER;


@Service
@RequiredArgsConstructor

public class AuthenticationService {



    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    private  final JwtService jwtService;

    private final UserService userService;


    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(String.valueOf(CASHIER))
                .build();



        com.example.Assignment_A3.model.User uu = new com.example.Assignment_A3.model.User(user.getUsername(),user.getPassword(),CASHIER);
        uu.setUsername(request.getUsername());
        uu.setPassword(request.getPassword());

        com.example.Assignment_A3.model.User u = new com.example.Assignment_A3.model.User(request.getUsername(), request.getPassword(), CASHIER);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }
}

