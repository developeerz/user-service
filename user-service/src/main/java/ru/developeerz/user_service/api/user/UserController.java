package ru.developeerz.user_service.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.user_service.api.ApiPaths;
import ru.developeerz.user_service.api.user.model.JwtResponse;
import ru.developeerz.user_service.api.user.model.LoginRequest;

@RestController
public class UserController {

    @PostMapping(ApiPaths.LOGIN)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken("access");
        jwtResponse.setRefreshToken("refresh");
        return ResponseEntity.ok(jwtResponse);
    }
}
