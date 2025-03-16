package ru.developeerz.user_service.core;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.developeerz.user_service.api.user.model.RegistrationRequest;
import ru.developeerz.user_service.core.entity.User;
import ru.developeerz.user_service.core.service.PasswordUtil;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashedPassword", source = "password", qualifiedByName = "hashPassword")
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isVerified", constant = "false")
    User map(RegistrationRequest registrationRequest);

    @Named("hashPassword")
    default String hashPassword(String password) {
        return PasswordUtil.getHashed(password);
    }
}
