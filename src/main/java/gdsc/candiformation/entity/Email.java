package gdsc.candiformation.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.persistence.Column;

@Getter
@Setter
@RedisHash(value = "email", timeToLive = 300)
@EnableRedisRepositories(basePackageClasses = Email.class)
public class Email{

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    public Email(String email, String code) {
        this.email = email;
        this.code = code;
    }

}
