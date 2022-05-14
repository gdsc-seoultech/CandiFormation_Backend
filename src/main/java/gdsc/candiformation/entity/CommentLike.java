package gdsc.candiformation.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "comment_like")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLike extends BaseEntity {

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long commentId;

}
