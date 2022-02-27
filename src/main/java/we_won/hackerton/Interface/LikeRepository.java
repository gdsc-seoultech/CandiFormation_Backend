package we_won.hackerton.Interface;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import we_won.hackerton.entity.Comment;
import we_won.hackerton.entity.CommentLike;
import we_won.hackerton.entity.User_;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<CommentLike, Long> {
//  Boolean findByUser(User_ user);
//
//  Boolean findByComment(Comment comment);
//
//  Boolean existsByUserAndComment(User_ user, Optional<Comment> comment);
}

