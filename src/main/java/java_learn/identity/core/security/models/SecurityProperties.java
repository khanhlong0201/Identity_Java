package java_learn.identity.core.security.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "security")
@Component
public class SecurityProperties {
  private List<String> permitAll = new ArrayList<>();
}
