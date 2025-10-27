package java_learn.identity.core.security;

import java_learn.identity.core.security.models.JwtProperties;
import java_learn.identity.core.security.service.JWTTokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import java_learn.identity.core.security.models.SecurityProperties;
/*
* Class này sẽ được Spring Boot tự động nạp khi ứng dụng khởi động (nếu thoả điều kiện).
Thường dùng trong module core / library để cấu hình sẵn các bean chung (giống như spring-boot-starter-security của Spring).
* */
@AutoConfiguration
/*
Chỉ kích hoạt class này nếu class JwtDecoder tồn tại trong classpath.
* */
@ConditionalOnClass(JwtDecoder.class)
/*Cho phép Spring Boot bind giá trị trong file application.yml hoặc application.properties*/
@EnableConfigurationProperties({JwtProperties.class, SecurityProperties.class})

/*
* Spring Boot sẽ tự động quét toàn bộ package java_learn.identity để tìm:
Các @Component, @Service, @Repository, @Controller
Các cấu hình khác (@Configuration, @RestController, v.v.)
👉 Điều này giúp bạn gom tất cả bean liên quan đến bảo mật vào core package và tự động load chúng khi import module này.
* */
@ComponentScan(basePackages = "java_learn.identity")
@Import({
        JWTTokenService.class,
        SecurityConfiguration.class
})
public class SecurityAutoConfiguration {
}
