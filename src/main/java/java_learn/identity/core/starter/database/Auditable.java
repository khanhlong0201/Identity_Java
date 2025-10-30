package java_learn.identity.core.starter.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java_learn.identity.core.common.BaseEntityListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@EntityListeners({AuditingEntityListener.class, BaseEntityListener.class})
@MappedSuperclass
public abstract class Auditable {
  @JsonProperty(
      access =
          JsonProperty.Access.READ_ONLY) // khong dc gán từ request ,mà chỉ được trả ra từ response)
  @Column(name = "created_by")
  protected Integer createdBy;

  @JsonProperty(
      access =
          JsonProperty.Access.READ_ONLY) // khong dc gán từ request ,mà chỉ được trả ra từ response)
  @Column(
      name = "created_at",
      nullable = false,
      updatable =
          false) // cột không thể nullupdatable = false // khi update thì khong thay doi cot nay
  @CreatedDate // spring tự đọng gán thời gian khi entity đưược tạo lần đầu
  @Temporal(TemporalType.TIMESTAMP) // JPA chinh dinh mapping luu ngay gio tu java vao sql
  protected LocalDateTime createdAt;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Column(name = "updated_by")
  protected Integer updatedBy;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Column(name = "updated_at")
  @LastModifiedDate // spring tu dong ghi nhan thoi diem cap nhat cuoi cung
  @Temporal(TemporalType.TIMESTAMP) // JPA chinh dinh mapping luu ngay gio tu java vao sql
  protected LocalDateTime updatedAt;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Column(name = "created_program")
  private String createdProgram;
}
