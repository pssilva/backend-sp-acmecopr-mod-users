package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.*;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginMapper {

    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO toUserDTO(UsersEntity users) {
        if (users == null) {
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(users.getId());
        userResponseDTO.setName(users.getName());
        userResponseDTO.setLastName(users.getLastName());
        userResponseDTO.setEmail(users.getEmail());
        userResponseDTO.setPassword(users.getPassword());
        userResponseDTO.setStatus(UserStatus.valueOf(users.getStatus()));
        userResponseDTO.setRules(users.getRules());

        return userResponseDTO;
    }

    public LoginResponseDTO toDTO(UsersEntity users) {
        if (users == null) {
            return null;
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUserDTO(this.toUserDTO(users));
        return loginResponseDTO;
    }

    public UsersEntity toEntity(UserRequestDTO usersRequestDTO) {

        if (usersRequestDTO == null) {
            return null;
        }

        UsersEntity users = new UsersEntity();
        if (usersRequestDTO.getEmail() != null) {
            users.setEmail(usersRequestDTO.getEmail());
        }
        users.setName(usersRequestDTO.getName());
        users.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
        
        return users;
    }
}
