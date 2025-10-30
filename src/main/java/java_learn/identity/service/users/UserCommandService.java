package java_learn.identity.service.users;

import java_learn.identity.core.security.CustomPasswordEncoder;
import java_learn.identity.domain.users.User;
import java_learn.identity.repository.database.users.UserEntity;
import java_learn.identity.repository.database.users.UserMapper;
import java_learn.identity.repository.database.users.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserCommandService {

    @NonNull
    UserRepository repository;

    @NonNull
    UserMapper userMapper;
    @NonNull
    CustomPasswordEncoder passwordEncoder;

    public void save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setIsExpiredPwd(true);
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        repository.save(userEntity);
    }
}
