package we_won.hackerton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "UserArticleScrap")
@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserArticleScrapID.class)
public class UserArticleScrap {
    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User_ user;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "article_id")
    private Article article;

}
