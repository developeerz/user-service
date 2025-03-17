package ru.developeerz.user_service.api.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.user_service.api.ApiPaths;
import ru.developeerz.user_service.api.authentication.model.AuthenticationResponse;
import ru.developeerz.user_service.api.authentication.model.JwtTokenRequest;
import ru.developeerz.user_service.core.service.AuthService;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping(ApiPaths.AUTHENTICATION)
    public AuthenticationResponse authentication(@Valid @RequestBody JwtTokenRequest request) {
        return authService.authentication(request);
    }
}
