package java_learn.identity.core.encrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java_learn.identity.core.common.exceptions.CoreException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class AesAlgorithm {
  @Value("${aes.secret-key}")
  private String secretKey; // từ application.yml

  private static final String ALGORITHM = "AES";
  private static final String TRANSFORMATION = "AES/GCM/NoPadding";
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private static final int IV_SIZE = 12; // chuẩn GCM là 12 bytes
  private static final int GCM_TAG_LENGTH = 128; // 16 bytes = 128 bits

  /** Encrypt với IV ngẫu nhiên, trả về Base64 (IV + cipherText) */
  public String encrypt(String plainText) {
    try {
      byte[] iv = new byte[IV_SIZE];
      new SecureRandom().nextBytes(iv); // IV ngẫu nhiên

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(
          Cipher.ENCRYPT_MODE,
          new SecretKeySpec(secretKey.getBytes(CHARSET), ALGORITHM),
          new GCMParameterSpec(GCM_TAG_LENGTH, iv));

      byte[] encrypted = cipher.doFinal(plainText.getBytes(CHARSET));

      // ghép IV + cipherText
      byte[] combined = new byte[IV_SIZE + encrypted.length];
      System.arraycopy(iv, 0, combined, 0, IV_SIZE);
      System.arraycopy(encrypted, 0, combined, IV_SIZE, encrypted.length);

      return Base64.getEncoder().encodeToString(combined);
    } catch (Exception e) {
      throw new CoreException();
    }
  }

  /** Decrypt từ Base64 (lấy IV từ đầu) */
  public String decrypt(String cipherText) {
    try {
      byte[] combined = Base64.getDecoder().decode(cipherText);

      byte[] iv = Arrays.copyOfRange(combined, 0, IV_SIZE);
      byte[] encrypted = Arrays.copyOfRange(combined, IV_SIZE, combined.length);

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(
          Cipher.DECRYPT_MODE,
          new SecretKeySpec(secretKey.getBytes(CHARSET), ALGORITHM),
          new GCMParameterSpec(GCM_TAG_LENGTH, iv));

      byte[] decrypted = cipher.doFinal(encrypted);
      return new String(decrypted, CHARSET);
    } catch (Exception e) {
      throw new CoreException();
    }
  }
}
