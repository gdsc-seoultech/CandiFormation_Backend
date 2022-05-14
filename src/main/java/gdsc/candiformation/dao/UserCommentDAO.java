package gdsc.candiformation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gdsc.candiformation.entity.CommentLike;
import gdsc.candiformation.entity.CommentLikeID;

public interface UserCommentDAO extends JpaRepository<CommentLike, CommentLikeID> {
//  CommentLike findByUser_id(long user_id);
//  CommentLike findByComment_id(long comment_id);
}
