package br.gov.acmecorp.modules.users.arch.clean.infrastructure.mapper.login;

import org.mapstruct.Mapper;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.api.UserResponseDTO;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;

@Mapper
public interface LoginSourceDestinationMapper {

    UsersEntity loginDtoToEntity(UserResponseDTO source);

    UserResponseDTO loginEntityToDto(UsersEntity destination);

}
