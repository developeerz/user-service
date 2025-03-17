package ru.developeerz.user_service.api.authentication.model;

import java.util.Set;

public record AuthenticationResponse(

        long userId,

        Set<String> authority
) {

}