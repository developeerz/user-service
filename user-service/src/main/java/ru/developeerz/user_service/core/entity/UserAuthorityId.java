package ru.developeerz.user_service.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserAuthorityId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "role_id", nullable = false)
    private AuthorityId authorityId;
}
