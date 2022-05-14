package gdsc.candiformation.entity;

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