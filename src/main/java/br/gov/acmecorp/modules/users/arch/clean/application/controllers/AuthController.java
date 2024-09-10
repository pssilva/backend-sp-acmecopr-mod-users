package br.gov.acmecorp.modules.users.arch.clean.application.controllers;

import java.util.Optional;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login.LoginMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.repository.UsersRepository;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class AuthController implements br.gov.acmecorp.modules.users.arch.clean.application.controllers.api.AuthApi {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final LoginMapper loginMapper;

    @PostMapping(value="/login", consumes = {"application/xml","application/json"})
    public ResponseEntity<br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.LoginResponseDTO> login(@RequestBody br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.LoginRequestDTO body){
        UsersEntity user = this.repository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        if(passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(loginMapper.toDTO(user).token(token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO> register(@RequestBody UserRequestDTO body){
        Optional<UsersEntity> opUser = this.repository.findByEmail(body.getEmail());

        if(opUser.isEmpty()) {
            UsersEntity newUser = new UsersEntity();
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setEmail(body.getEmail());
            newUser.setName(body.getName());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(loginMapper.toUserDTO(opUser.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}
