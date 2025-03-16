package ru.developeerz.user_service.core;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.developeerz.user_service.api.user.model.RegistrationRequest;
import ru.developeerz.user_service.core.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashedPassword", source = "password", qualifiedByName = "hashPassword")
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isVerified", constant = "false")
    User map(RegistrationRequest registrationRequest);

    @Named("hashPassword")
    default String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
