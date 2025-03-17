package ru.developeerz.user_service.api.user.model;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(

        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
