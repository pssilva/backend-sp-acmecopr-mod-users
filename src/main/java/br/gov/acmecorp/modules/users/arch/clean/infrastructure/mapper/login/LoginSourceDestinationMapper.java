package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.LoginResponseDTO;
import org.mapstruct.Mapper;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import org.mapstruct.Mapping;

//@Mapper
public interface LoginSourceDestinationMapper {

    UsersEntity loginDtoToEntity(LoginResponseDTO source);

    //@Mapping(target = "userDTO", source = "")
    LoginResponseDTO loginEntityToDto(UsersEntity destination);

}
