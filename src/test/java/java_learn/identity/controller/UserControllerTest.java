package java_learn.identity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java_learn.identity.controller.api.user.model.UserModelMapper;
import java_learn.identity.controller.api.user.model.UserRequest;
import java_learn.identity.service.users.UserUseCaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
    properties = {
      "logging.level.org.springframework.validation=DEBUG",
      "logging.level.org.springframework.web=DEBUG"
    })
public class UserControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private UserModelMapper userModelMapper;

  @MockitoBean private UserUseCaseServiceImpl userUseCaseServiceImpl;

  @Test
  @WithMockUser
  void testSaveUser_ShouldReturnOk() throws Exception {
    // Arrange: chuẩn bị dữ liệu request
    UserRequest request =
        new UserRequest(
            "khanhlong", // username (8 ký tự)
            "KhanhLong@1", // password hợp lệ
            "khanhlong@gmail.com" // email hợp lệ
            );

    doNothing().when(userUseCaseServiceImpl).saveUser(any(UserRequest.class));

    // Act & Assert
    mockMvc
        .perform(
            post("/v1/api/user") // URL này phải đúng với UserApi của bạn
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void testSaveUser_InvalidPassword_ShouldReturnBadRequest() throws Exception {
    // Arrange: mật khẩu sai (thiếu ký tự đặc biệt)
    // Arrange (Given): chuẩn bị dữ liệu đầu vào
    UserRequest request = new UserRequest("khanhlong", "KhanhLong1", "khanhlong@gmail.com");
    System.out.println("=== VALIDATION DEBUG ===");
    System.out.println("Request: " + objectMapper.writeValueAsString(request));

    // Act & Assert
    // Act (When): thực hiện gọi API qua MockMvc
    mockMvc
        .perform(
            post("/v1/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        // Assert (Then): kiểm tra kết quả trả về
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  void testSaveUser_BlankUsername_ShouldReturnBadRequest() throws Exception {
    // Arrange
    UserRequest request = new UserRequest("", "KhanhLong@1", "khanhlong@gmail.com");

    // Act & Assert
    mockMvc
        .perform(
            post("/v1/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }
}
