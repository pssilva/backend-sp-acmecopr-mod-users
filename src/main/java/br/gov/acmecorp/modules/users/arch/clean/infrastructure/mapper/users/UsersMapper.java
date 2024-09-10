package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import lombok.RequiredArgsConstructor;

@Component

@RequiredArgsConstructor
public class UsersMapper {


    private final PasswordEncoder passwordEncoder;

    public br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO toDTO(UsersEntity users) {
        if (users == null) {
            return null;
        }
        br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO userResponseDTO = new br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO();
        userResponseDTO.setId(users.getId());
        userResponseDTO.setName(users.getName());
        userResponseDTO.setLastName(users.getLastName());
        userResponseDTO.setEmail(users.getEmail());
        userResponseDTO.setPassword(users.getPassword());
        userResponseDTO.setStatus(users.getStatus());

        return userResponseDTO;
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
