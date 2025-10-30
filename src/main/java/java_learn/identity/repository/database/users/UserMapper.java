package java_learn.identity.repository.database.users;

import java_learn.identity.core.model.mapper.EntityMapper;
import java_learn.identity.domain.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<User, UserEntity> {
  User toDto(UserEntity entity);

  UserEntity toEntity(User dto);
}
