package we_won.hackerton.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Email extends BaseEntity{

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

}
