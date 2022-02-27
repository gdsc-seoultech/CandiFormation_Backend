package we_won.hackerton.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import we_won.hackerton.entity.CommentLike;
import we_won.hackerton.entity.CommentLikeID;
import we_won.hackerton.entity.UserArticleScrap;

import java.util.List;

public interface UserCommentDAO extends JpaRepository<CommentLike, CommentLikeID> {
//  CommentLike findByUser_id(long user_id);
//  CommentLike findByComment_id(long comment_id);
}
