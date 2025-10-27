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
