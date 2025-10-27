package java_learn.identity.domain.users;

public record UserId(Integer value) {
    public String asText(){
        return value.toString();
    }
}
