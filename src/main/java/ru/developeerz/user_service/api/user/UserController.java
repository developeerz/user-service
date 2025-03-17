package ru.developeerz.user_service.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.user_service.api.ApiPaths;
import ru.developeerz.user_service.api.user.model.JwtResponse;
import ru.developeerz.user_service.api.user.model.LoginRequest;
import ru.developeerz.user_service.api.user.model.RegistrationRequest;
import ru.developeerz.user_service.api.user.model.VerificationRequest;
import ru.developeerz.user_service.core.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(ApiPaths.LOGIN)
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping(ApiPaths.REGISTRATION)
    public ResponseEntity<Void> registrationUser(@Valid @RequestBody RegistrationRequest request) {
        return userService.registrationUser(request);
    }

    @PostMapping(ApiPaths.VERIFY)
    public ResponseEntity<JwtResponse> verifyUser(@Valid @RequestBody VerificationRequest request) {
        return userService.verifyUser(request);
    }

}
