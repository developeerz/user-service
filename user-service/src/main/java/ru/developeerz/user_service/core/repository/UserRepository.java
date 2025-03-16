package ru.developeerz.user_service.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.developeerz.user_service.core.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
