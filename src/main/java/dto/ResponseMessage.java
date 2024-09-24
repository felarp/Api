package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor (force = true)

@Data

public class ResponseMessage {

    private String message;
    private final User user;

    public ResponseMessage(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public User getUser() {
        return user;
    }
}
