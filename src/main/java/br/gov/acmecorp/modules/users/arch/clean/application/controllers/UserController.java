package br.gov.acmecorp.modules.users.arch.clean.application.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserPageDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserRequestDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.services.UsersService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/api")
@RequiredArgsConstructor
public class UserController   {

    private final UsersService usersService;

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO users) {
        return ResponseEntity.ok (usersService.create(users));
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable String id,
                                  @RequestBody UserRequestDTO users) {
        return usersService.update(id, users);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public UserPageDTO getAllUsers(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                   @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return usersService.getAllUsers(page, pageSize);
    }
}
