package java_learn.identity.core.security.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "security")
@Component
public class SecurityProperties {
    private List<String> permitAll = new ArrayList<>();
}
