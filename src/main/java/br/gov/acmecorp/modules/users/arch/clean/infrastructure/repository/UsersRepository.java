package br.gov.acmecorp.modules.users.arch.clean.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByEmail(String email);
}
