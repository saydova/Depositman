package com.bank.depositman.service;

import com.bank.depositman.utils.dto.AuthDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(AuthDTO.RegisterRequest request);
    AuthDTO.AuthenticationResponse authenticate(AuthDTO.AuthenticationRequest request);

}
