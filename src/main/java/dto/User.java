package dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class User {

private String username;
private String password;

    public User(String login, String password) {
        this.username = login;
        this.password = password;
    }
}
