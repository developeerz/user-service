package ru.developeerz.user_service.api.user.model;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;


public record LoginRequest(

        @NotBlank
        @Parameter(name = "пароль", required = true)
        String password,

        @NotBlank
        @Parameter(name = "почта", required = true)
        String email
) {
}
