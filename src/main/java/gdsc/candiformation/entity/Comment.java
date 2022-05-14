package gdsc.candiformation.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity{

  @ManyToOne(targetEntity = User_.class)
  private User_ user;

  @ManyToOne(targetEntity = Article.class)
  private Article article;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Boolean isSecret = true;

  @Column(nullable = false)
  private int likeNum = 0;

  @Column(nullable = false)
  private String createdAt;


  public void changeBasicInfo(User_ user, Article article, String content, Boolean isSecret, int likeNum) {
    this.user = user;
    this.article = article;
    this.content = content;
    this.isSecret = isSecret;
    this.likeNum = likeNum;
    //this.createdAt = LocalDateTime.parse(createdAt);
  }

}
