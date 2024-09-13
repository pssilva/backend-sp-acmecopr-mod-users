package br.gov.acmecorp.modules.users.arch.clean.application.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.gov.acmecorp.modules.users.arch.clean.application.controllers.api.AuthApi;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.*;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login.LoginMapper;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login.LoginSourceDestinationMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.repository.UsersRepository;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final LoginMapper loginMapper;


    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @PostMapping(value="/login", consumes = {"application/json"})
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body){
        UsersEntity user = this.repository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        if(passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            LoginResponseDTO loginResponseDTO = loginMapper.toDTO(user)
                    .profilesActive(springProfilesActive)
                    .token(token);

            return ResponseEntity.ok(loginResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value="/register", consumes = {"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO body){
        Optional<UsersEntity> opUser = this.repository.findByEmail(body.getEmail());

        if(opUser.isEmpty()) {
            UsersEntity newUser = new UsersEntity();
            newUser.setId(UUID.randomUUID().toString());
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setEmail(body.getEmail());
            newUser.setName(body.getName());
            newUser.setLastName(body.getLastName());
            newUser.setRules(List.of(Rules.valueOf(Rules.USER.name())));
            newUser.setStatus(UserStatus.ACTIVE.getValue());
            this.repository.save(newUser);
            opUser = this.repository.findByEmail(body.getEmail());
            return ResponseEntity.ok(loginMapper.toUserDTO(opUser.orElse(newUser)));
        }
        return ResponseEntity.badRequest().build();
    }
}
