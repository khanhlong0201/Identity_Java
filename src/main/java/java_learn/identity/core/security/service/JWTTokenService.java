package java_learn.identity.core.security.service;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java_learn.identity.core.security.ActorType;
import java_learn.identity.core.security.models.JwtProperties;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

/*
 * Dinh nghia bean, de spring quan ly vong loi va tiem dependency
 * Quan ly vong doi Bean
 * */
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JWTTokenService {
  @NonNull JwtProperties jwtProperties;

  /*Phan tich token de lay claim*/
  public Jwt getJwt(final String token) {
    return this.jwtDecoder().decode(token);
  }

  /*Cho phep Security xac thuc JWT*/
  @Bean
  protected JwtDecoder jwtDecoder() {
    if (jwtProperties.emptyJwtSetUri()) {
      return NimbusJwtDecoder.withPublicKey(jwtProperties.getPublicKey()).build();
    } else {
      return NimbusJwtDecoder.withJwkSetUri(jwtProperties.getJwtSetUri()).build();
    }
  }

  /*Cho he thong phat hanh JWT*/
  @Bean
  protected JwtEncoder jwtEncoder() {
    JWK jwk =
        (new RSAKey.Builder(jwtProperties.getPublicKey()))
            .privateKey(jwtProperties.getPrivateKey())
            .build();
    JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwkSource);
  }

  /*Sinh ra token co chu ky RSA*/
  public String createAccessToken(ActorType actorType, String subject, Map<String, Object> claims) {
    Instant now = Instant.now();
    JwtClaimsSet.Builder claimBuilder = JwtClaimsSet.builder();
    claimBuilder
        .subject(subject)
        .issuer(jwtProperties.getIssuer())
        .issuedAt(now)
        .expiresAt(now.plusSeconds(jwtProperties.getJwtAccessTokenExpirationS()));
    Objects.requireNonNull(claimBuilder);
    claims.forEach(claimBuilder::claim);
    claimBuilder.claim("actorType", actorType.ordinal());
    return jwtEncoder().encode(JwtEncoderParameters.from(claimBuilder.build())).getTokenValue();
  }

  public String createRefreshToken(
      ActorType actorType, String subject, Map<String, Object> claims) {
    Instant now = Instant.now();
    JwtClaimsSet.Builder claimBuilder = JwtClaimsSet.builder();
    claimBuilder
        .issuer(this.jwtProperties.getIssuer())
        .issuedAt(now)
        .expiresAt(now.plusSeconds(this.jwtProperties.getJwtRefreshExpirationS()))
        .subject(subject);
    Objects.requireNonNull(claimBuilder);
    claims.forEach(claimBuilder::claim);
    claimBuilder.claim("actorType", actorType.ordinal());
    return this.jwtEncoder()
        .encode(JwtEncoderParameters.from(claimBuilder.build()))
        .getTokenValue();
  }
}
