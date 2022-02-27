package we_won.hackerton.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import we_won.hackerton.constant.UserRole;

import javax.persistence.*;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User_ extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username; //이메일

    private String password;
    //travis용 주석ㅎㅇㅎㅇ
    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    @Builder
    public User_(String username, String password, UserRole role,String nickname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }
}
