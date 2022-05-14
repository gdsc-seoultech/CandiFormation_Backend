package gdsc.candiformation.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gdsc.candiformation.entity.CommentLike;

@Repository
public interface LikeRepository extends JpaRepository<CommentLike, Long> {
//  Boolean findByUser(User_ user);
//
//  Boolean findByComment(Comment comment);
//
//  Boolean existsByUserAndComment(User_ user, Optional<Comment> comment);
}

