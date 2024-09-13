package br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.Rules;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    private List<String> rules;
    private String status;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(this.created)) this.created=LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        if(Objects.isNull(this.modified)) this.modified=LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    @PreUpdate
    public void PreUpdate() {
        this.modified=LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
