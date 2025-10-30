package java_learn.identity.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java_learn.identity.controller.api.user.model.UserModelMapper;
import java_learn.identity.controller.api.user.model.UserRequest;
import java_learn.identity.core.common.exceptions.ApplicationException;
import java_learn.identity.core.constants.ErrorCode;
import java_learn.identity.domain.users.User;
import java_learn.identity.service.users.UserCommandService;
import java_learn.identity.service.users.UserQueryService;
import java_learn.identity.service.users.UserUseCaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class UserUseCaseServiceImplTest {

  @Mock private UserQueryService userQueryService;

  @Mock private UserModelMapper userModelMapper;

  @Mock private UserCommandService userCommandService;

  @InjectMocks private UserUseCaseServiceImpl userUseCaseService;

  private UserRequest request;
  private User user;

  @BeforeEach
  void setup() {
    request = new UserRequest("tony", "Tony@123", "tony@example.com");

    user =
        new User(
            1, // userId
            "tony@example.com", // email
            "tony", // username
            "Tony@123", // password
            false // isExpiredPwd
            );
  }

  // ✅ Case 1: username chưa tồn tại -> save user
  @Test
  void saveUser_WhenUsernameNotExists_ShouldSaveUser() {
    // given
    when(userQueryService.existsByUsername("tony")).thenReturn(false);
    when(userModelMapper.toDto(request)).thenReturn(user);

    // when
    userUseCaseService.saveUser(request);

    // then
    verify(userQueryService).existsByUsername("tony");
    verify(userModelMapper).toDto(request);
    verify(userCommandService).save(user);
  }

  // ❌ Case 2: username đã tồn tại -> throw ApplicationException
  @Test
  void saveUser_WhenUsernameExists_ShouldThrowException() {
    // given
    when(userQueryService.existsByUsername("tony")).thenReturn(true);

    // when + then
    ApplicationException exception =
        assertThrows(ApplicationException.class, () -> userUseCaseService.saveUser(request));

    // verify error code & message
    assertEquals(ErrorCode.DUPLICATE_USERNAME, exception.getErrorCode());
    assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());

    // ensure save() không được gọi
    verify(userCommandService, never()).save(any());
  }
}
