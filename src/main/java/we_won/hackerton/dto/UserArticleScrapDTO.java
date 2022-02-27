package we_won.hackerton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserArticleScrapDTO {

    private String username;
    private long article_id;

    @Builder
    public UserArticleScrapDTO(String username, long article_id) {
        this.username = username;
        this.article_id = article_id;
    }
}
