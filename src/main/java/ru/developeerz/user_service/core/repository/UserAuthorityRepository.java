package ru.developeerz.user_service.core.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.developeerz.user_service.core.entity.AuthorityId;
import ru.developeerz.user_service.core.entity.UserAuthority;
import ru.developeerz.user_service.core.entity.UserAuthorityId;

import java.util.Set;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityId> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addUserRole(@Param("userId") long userId, @Param("roleId") int roleId);

    @Query(value = """
    SELECT * from user_authorities
    where user_authorities.user_id = :userId
    """, nativeQuery = true)
    Set<AuthorityId> findAuthorityByUserId(@Param("userId") long userId); // maybe List<String>
}
