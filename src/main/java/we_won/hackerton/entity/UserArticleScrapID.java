package we_won.hackerton.entity;

import java.io.Serializable;

public class UserArticleScrapID implements Serializable {

    private long user;
    private long article;

    public UserArticleScrapID() {}

    public UserArticleScrapID(long user, long article) {
        this.user = user;
        this.article = article;
    }
}
