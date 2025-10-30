package java_learn.identity.repository.database.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java_learn.identity.core.starter.database.Auditable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
public class UserEntity extends Auditable
    implements Serializable { // doi tuong nay co the chuyen thanh chuoi byte
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, name = "user_id")
  Integer userId;

  @Column(nullable = false, unique = true, name = "username")
  String username;

  @Column(nullable = false, name = "password")
  String password;

  @Column(nullable = false, name = "email")
  String email;

  @Builder.Default
  @Column(nullable = false, name = "deleted")
  Boolean deleted = false;

  @Builder.Default
  @Column(nullable = false, name = "is_expired_pwd")
  Boolean isExpiredPwd = true;
}
