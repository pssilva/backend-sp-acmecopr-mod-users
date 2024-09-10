
package br.gov.acmecorp.modules.users.arch.clean.infrastructure.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserPageDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.users.UsersMapper;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.repository.UsersRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    public br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO getUserById(String id) {
      return usersRepository.findById(id)
        .map(usersMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO create(br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO usersRequestDTO) {
      return usersMapper.toDTO(usersRepository.save(usersMapper.toEntity(usersRequestDTO)));
    }

    public br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO update(String id, br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO usersRequestDTO) {
      return usersRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(usersRequestDTO.getName());
            recordFound.setEmail(usersRequestDTO.getEmail());
            recordFound.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
            return usersMapper.toDTO(usersRepository.save(recordFound));
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(String id) {
      usersRepository.delete(usersRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public UserPageDTO getAllUsers(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {
        Page<UsersEntity> pageUsers = usersRepository.findAll(PageRequest.of(page, pageSize));
        List<br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO> listUsers = pageUsers.get().map(usersMapper::toDTO).collect(Collectors.toList());
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setUsers(listUsers);
        userPageDTO.setTotalElements(BigDecimal.valueOf(pageUsers.getTotalElements()));
        userPageDTO.setTotalPages(pageUsers.getTotalPages());
        return userPageDTO;
    }

}
