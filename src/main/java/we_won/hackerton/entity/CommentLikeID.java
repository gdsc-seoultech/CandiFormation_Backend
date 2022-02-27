package we_won.hackerton.entity;

import java.io.Serializable;

public class CommentLikeID implements Serializable {
  private long user;
  private long comment;

  public CommentLikeID() {
  }

  public CommentLikeID(long user, long comment) {
    this.user = user;
    this.comment = comment;
  }
}

/*
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

 */