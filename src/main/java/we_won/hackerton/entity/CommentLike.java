package we_won.hackerton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

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
