package ru.developeerz.user_service.api.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private String accessToken;

    private String refreshToken;
}
