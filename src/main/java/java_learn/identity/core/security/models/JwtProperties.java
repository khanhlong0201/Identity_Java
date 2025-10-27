package java_learn.identity.core.security.models;

import java_learn.identity.core.utils.StringHelper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
/*
* @Data
* Bao gom tat ca:
* @Getter
* @Setter
* @ToString()
* @EqualsAndHashCode
* @RequiredArgsConstructor
* */
@Data
public class JwtProperties {
    String privateKey;
    String publicKey;
    long jwtRefreshExpirationS = 604800L;//7 ngay
    long jwtAccessTokenExpirationS = 86400L;//1 ngay
    String issuer;
    String jwtSetUri;

    public RSAPublicKey getPublicKey() {
        return RsaKeyConverters
                .x509()
                .convert(new ByteArrayInputStream(publicKey.getBytes()));
    }

    public RSAPrivateKey getPrivateKey() {
        return RsaKeyConverters
                .pkcs8()
                .convert(new ByteArrayInputStream(privateKey.getBytes()));

    }

    public boolean emptyJwtSetUri(){
        return StringHelper.isNullOrEmpty(this.jwtSetUri);
    }
}
