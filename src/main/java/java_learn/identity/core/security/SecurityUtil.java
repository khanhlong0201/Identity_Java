package java_learn.identity.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component("securityUtil")
@Slf4j
public class SecurityUtil {

    public boolean isAdmin() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof JwtAuthenticationToken jwtAuth) {
                Long actorType = jwtAuth.getToken().getClaim("actorType");
                log.info("Checking admin access - ActorType: {}", actorType);
                return actorType != null && actorType == 0L;
            }
            return false;
        } catch (Exception e) {
            log.error("Error checking admin role", e);
            return false;
        }
    }
}