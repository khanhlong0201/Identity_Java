package java_learn.identity.controller.api.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*Cho phép annotation này xuất hiện trong JavaDoc (tài liệu API).*/
@Documented

/*
Nó nói cho Bean Validation (Jakarta Validation) biết rằng:
“Annotation này (@UniqueEmail) sẽ được xử lý (validate) bởi class UniqueEmailValidator.”
*/
@Constraint(validatedBy = UniqueEmailValidator.class)

/*
Chỉ định annotation này được phép gắn ở đâu.
Ở đây là FIELD → nghĩa là chỉ gắn được lên biến (field), ví dụ:
*/
@Target({ElementType.FIELD})
/*
 * annotation này sẽ tồn tại và có hiệu lực ở runtime (khi chương trình chạy).
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail { // đây là cách java định nghĩa annotation mới
  String message() default "Email đã tồn tại trong hệ thống";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
