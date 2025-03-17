package ru.developeerz.user_service.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.developeerz.user_service.api.authentication.model.AuthenticationResponse;
import ru.developeerz.user_service.api.authentication.model.JwtTokenRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;

    public AuthenticationResponse authentication(JwtTokenRequest request) {
//        String jwtToken = request.jwtToken();
//        if ()

        return null;
    }

}
