package ru.developeerz.user_service.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "authorities")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Authority {

    @Id
    @Enumerated(EnumType.STRING)
    private AuthorityId id;

    @Column(name = "description", nullable = false)
    private String description;
}
