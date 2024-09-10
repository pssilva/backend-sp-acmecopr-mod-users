package br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String email;
    private String password;
    private List<String> rules;
    private UserStatus status;
    private LocalDateTime created;
    private LocalDateTime modified;
}
