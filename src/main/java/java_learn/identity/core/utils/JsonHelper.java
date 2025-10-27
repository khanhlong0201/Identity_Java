package java_learn.identity.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java_learn.identity.core.common.exceptions.ApplicationException;
import java_learn.identity.core.constants.CodeErrorCodes;
import org.springframework.http.HttpStatus;

public final class JsonHelper {
   private static final ObjectMapper mapper = new ObjectMapper();
    private JsonHelper() {
        throw new UnsupportedOperationException("Utility Class should bot be instantiated");
    }

    /*
    * User user = new User("Tony", "tony@example.com", 25);
    * String json = JsonHelper.toJson(user);
        System.out.println("JSON: " + json);
        // Output: {"name":"Tony","email":"tony@example.com","age":25}

    * * */
    public static String toJson(Object obj) {
        try{
            return mapper.writeValueAsString(obj);
        }catch(Exception ex) {
            return "";
        }
    }

    /*
* Stirng json = {"name":"Tony","email":"tony@example.com","age":25}
* User user = JsonHelper.fromJson(json);
    User user = new User("Tony", "tony@example.com", 25);
* */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try{
            return mapper.readValue(json, clazz);
        }catch(Exception ex){
            throw new ApplicationException(
                    CodeErrorCodes.CONFLICT,
                    "Error parse JSON",
                    HttpStatus.BAD_REQUEST

            );
        }
    }




}
