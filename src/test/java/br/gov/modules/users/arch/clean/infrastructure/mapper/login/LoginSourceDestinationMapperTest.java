package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login.LoginSourceDestinationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LoginSourceDestinationMapperTest {

    @Autowired
    LoginSourceDestinationMapper loginSourceDestinationMapper;

    @Mock
    UserResponseDTO userResponseDTO;

    @Mock
    UsersEntity usersEntity;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testLoginDtoToEntity() {
    }

    @Test
    void testLoginEntityToDto() {
    }
}