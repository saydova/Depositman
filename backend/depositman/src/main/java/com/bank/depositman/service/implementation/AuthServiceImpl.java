package com.bank.depositman.service.implementation;

import com.bank.depositman.model.User;
import com.bank.depositman.repository.UserRepository;
import com.bank.depositman.security.JwtService;
import com.bank.depositman.service.AuthService;
import com.bank.depositman.utils.dto.AuthDTO;
import com.bank.depositman.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<?> register(AuthDTO.RegisterRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        PasswordEncoder passwordEncoderAlt = new BCryptPasswordEncoder(); // originnya

        var user = User.builder()
                .username(request.getUsername())
                .roles(request.getRoles())
//                .password(passwordEncoderAlt.encode(request.getPassword()))
                .password(passwordEncoder.encode(request.getPassword()))
                .accountBalanced(0)
                .build();
        User savedUser = repository.save(user);
        savedUser.setPassword(null); // biar gak return password
        return Response.renderJSON(
//                savedUser
               null,
                "New User Created",
                HttpStatus.CREATED
        );
    }

    @Override
    public AuthDTO.AuthenticationResponse authenticate(AuthDTO.AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthDTO.AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }
}
