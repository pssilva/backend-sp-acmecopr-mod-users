package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.users;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.Rules;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component

@RequiredArgsConstructor
public class UsersMapper {


    private final PasswordEncoder passwordEncoder;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public UserResponseDTO toDTO(UsersEntity users) {
        if (users == null) {
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(users.getId());
        userResponseDTO.setName(users.getName());
        userResponseDTO.setLastName(users.getLastName());
        userResponseDTO.setEmail(users.getEmail());
        userResponseDTO.setEmail(users.getEmail());
        userResponseDTO.setPassword(users.getPassword());
        userResponseDTO.setStatus(UserStatus.valueOf(users.getStatus()));

        if(Objects.nonNull(users.getCreated()))
            userResponseDTO.setCreated(
                OffsetDateTime.parse(
                    users.getCreated()
                        .atOffset(ZoneOffset.UTC)
                            .toString()
                )
            );

        if(Objects.nonNull(users.getModified()))
            userResponseDTO.setModified(OffsetDateTime.parse(users.getModified().atOffset(ZoneOffset.UTC).toString()));

        userResponseDTO.setRules(List.of(Rules.valueOf(String.join(",", users.getRules()))));

        return userResponseDTO;
    }

    public UsersEntity toEntity(UserRequestDTO usersRequestDTO) {

        if (usersRequestDTO == null) {
            return null;
        }

        UsersEntity userEntity = new UsersEntity();
        if (usersRequestDTO.getEmail() != null) {
            userEntity.setEmail(usersRequestDTO.getEmail());
        }

        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
        userEntity.setEmail(usersRequestDTO.getEmail());
        userEntity.setName(usersRequestDTO.getName());
        userEntity.setLastName(usersRequestDTO.getLastName());
        userEntity.setRules(List.of(Rules.PADRAO.getValue()));
        userEntity.setStatus(UserStatus.ACTIVE.getValue());

        return userEntity;
    }
}
