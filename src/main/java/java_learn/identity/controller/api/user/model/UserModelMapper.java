package java_learn.identity.controller.api.user.model;

import java_learn.identity.core.model.mapper.ModelMapper;
import java_learn.identity.domain.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserModelMapper extends ModelMapper <UserResponse, User>{
    UserResponse toModel(User user);
    User toDto(UserRequest userRequest);
}
