package gdsc.candiformation.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gdsc.candiformation.entity.CommentLike;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

  Boolean existsByUserIdAndCommentId(Long userId, Long commentId);
}
