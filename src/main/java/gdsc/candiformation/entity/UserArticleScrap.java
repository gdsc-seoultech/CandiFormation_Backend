package gdsc.candiformation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Table(name = "UserArticleScrap")
@Entity
@Getter
@NoArgsConstructor
@IdClass(UserArticleScrapID.class)
public class UserArticleScrap {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User_ user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public UserArticleScrap(User_ user, Article article) {
        this.user = user;
        this.article = article;
    }
}
