- @Autowired dùng này để tiêm vào Service vào Controller hoặc
- @RequiredArgsConstructor và @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE

- @MappedSuperclass: đánh dấu 1 class không phải l entity, nhưng các file trong class đó được kế thua
  va anh xa trong entity con trong database
    
- @EntityListeners: tự động tạo 1 số đoạn code khi entity 
    đựợc tạo mới, cap nhat, xoa, 

- Serializable: chuyen object -> byte[], quan ly session rollback, can serialize de truyen du lieu giua service,
  phuc vu luu cache, gui qua network
  
- @EqualsAndHashCode(callSuper = true)
  lombok se goi them equals() va hashCode() cua class cha (auditable) de so sanh

- @Mapper: danh dau interface la MapStruct, se tu doc generate code de map giua cac class dua vao field
- componetModel = "spring" : MapStruct se tu doong implement duong dang Spring Bean (@Compenet), giup @Autowired mapper 
  va service/controller truc tiep ma hong can instance thu cong
- unmappedTargetPolicy = ReporttingPolicy.IGNORE : khi map tu source -> targe ma field khong khop , se bo qua khong thong bao loi

- Refresh Gradle & Rebuild Project 
./gradlew clean build --refresh-dependencies

- record la immutable (bat bien): record da co getter 

- @hidden dung de an khoi Swagger UI

- UserDetails là một interface có sẵn trong Spring Security — dùng để biểu diễn thông tin của người dùng (username, password, roles, trạng thái, v.v.).
  Bạn thường tạo class riêng implement nó

- BCryptPasswordEncoder(10): 10 la 2^10= 1024 vong xu ly 
  gom salt + pass = hash 1023 lan hash ke tiep

- @ConfigurationProperties(prefix = "security")
  noi voi spring boot hay doc cau hinh co prefix la security trong file application.yml
 
- /*class khong dc phep instance va hong cos subclass */
  public final class

- @Bean: la doi tuong do Spring Ioc containner quan ly
- Bean lifecycle: tao -> tiem -> chay -> huy ( @PostConstruct, @PreDestroy)

- Secret Key: (Khóa bí mật / Symmetric Key)
Cơ chế: Symmetric Encryption (mã hóa đối xứng).
Nguyên tắc: Cùng một khóa được dùng để mã hóa và giải mã dữ liệu.
Ưu điểm: Nhanh, đơn giản, hiệu suất cao.
Nhược điểm: Khó khăn trong việc chia sẻ khóa an toàn giữa các bên.

- Private Key (Khóa riêng / Asymmetric Key)
Cơ chế: Asymmetric Encryption (mã hóa bất đối xứng).
Nguyên tắc: Một cặp private key + public key:
Private key: giữ bí mật, dùng để giải mã hoặc ký số.
Public key: công khai, dùng để mã hóa hoặc xác thực chữ ký.

- Public Key (Khóa công khai / Asymmetric Key)
Cơ chế: Cùng cặp với private key.
Nguyên tắc:
Có thể chia sẻ công khai, mọi người dùng để mã hóa dữ liệu gửi tới chủ sở hữu private key.
Hoặc dùng để xác thực chữ ký được tạo bằng private key.

- PEM (Privacy-Enhanced Mail)= định dạng lưu trữ key/certificate
  PEM là định dạng văn bản dùng để lưu trữ dữ liệu mã hóa (key/cert) an toàn, dễ chia sẻ và được tiêu chuẩn hóa quốc tế.
  .pem
  Text base64, có header/footer
  File .pem thực ra là một file văn bản (text), chứa dữ liệu mã hóa base64,
  được bao quanh bởi các dòng bắt đầu và kết thúc như thế này:
  -----BEGIN PRIVATE KEY-----
  MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQ...
  -----END PRIVATE KEY-----

- @RestControllerAdvice là một global error handler trong Spring Boot.

- @PreAuthorize	Kiểm tra quyền trước khi thực thi
- @PostAuthorize	Kiểm tra quyền trên kết quả trả về
- @PreFilter	Lọc danh sách đầu vào
- @PostFilter	Lọc danh sách đầu ra
- @Secured	Check role đơn giản
- @RolesAllowed	Chuẩn JSR-250

- Cấu hình :
- @EnableMethodSecurity(
  prePostEnabled = true,      // Cho @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter
  securedEnabled = true,      // Cho @Secured
  jsr250Enabled = true        // Cho @RolesAllowed
  )

- Unit test
- given: Chuẩn bị điều kiện ban đầu (dữ liệu, mock, state) -> “Giả sử có tình huống này...
- When: Hành động đang được test -> “Khi tôi gọi hàm / thực hiện thao tác này...”
- then: kết quả mong đợi
