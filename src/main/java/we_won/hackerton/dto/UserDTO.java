package we_won.hackerton.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;

    private String role;

//    private String nickname;
//
//    private String tel;

    public UserDTO(String username, String role) {
        super();
        this.username = username;
        this.role = role;
//        this.nickname = nickname;
//        this.tel = tel;
    }
}