package java_learn.identity.core.security;

import java_learn.identity.core.security.models.SecurityProperties;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

import java.util.List;

@Configuration/*Cho Spring biết đây là class cấu hình (Config Bean).*/
@EnableWebSecurity/*Bật module bảo mật web của Spring Security*/
@EnableConfigurationProperties(SecurityProperties.class)/*Cho phép Spring tự load config từ application.yml vào class SecurityProperties*/
@RequiredArgsConstructor/*Tự động tạo constructor có tham số cho các field final*/
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableMethodSecurity // cho hoạt động PreAuthorize, PostAuthorize
public class SecurityConfiguration {
    /*Xử lý lỗi 401 - Unauthorized (khi người dùng chưa đăng nhập hoặc token sai).*/
    @NonNull
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /*Xử lý lỗi 403 - Forbidden (token đúng nhưng không đủ quyền).*/
    @NonNull CustomAccessDeniedHandler customAccessDeniedHandler;

    /*Nơi chứa danh sách các đường dẫn permitAll được định nghĩa trong file application.yml*/
    @NonNull SecurityProperties securityProperties;

    /*“hàng rào” bảo vệ tất cả các request.*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> permitAll = securityProperties.getPermitAll();
        permitAll.add("/swagger-ui/**");
        permitAll.add("/api-docs.yaml");
        permitAll.add("/api-docs/**");
        permitAll.add("/x/api-docs.yaml");
        permitAll.add("/x/api-docs/**");
        permitAll.add("/error/**");
        permitAll.add("/actuator/**");
        permitAll.add("/favicon.ico");

        /*
        Là 1 Resource Server ( do khởi tạo trong JWTTokenService)
        Spring sẽ tự động:
        Dò bean JwtDecoder (do bạn khai báo trong JWTTokenService).
        Dùng nó để giải mã và xác thực token.
        Nếu token hợp lệ → cho phép truy cập.
        Nếu sai → ném lỗi OAuth2AuthenticationException → gọi CustomAuthenticationEntryPoint.
        * */
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(
                exception ->
                        exception
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler));

        http.authorizeHttpRequests(
                request ->
                        request
                                .requestMatchers((permitAll.toArray(String[]::new)))
                                .permitAll()
                                .anyRequest()
                                .authenticated());
        http.headers(
                headers ->
                        headers
                                .xssProtection(
                                        xss ->
                                                xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                                .contentSecurityPolicy(cps -> cps.policyDirectives("script-src 'self'")));

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
