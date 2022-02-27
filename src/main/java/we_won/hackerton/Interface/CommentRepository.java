package we_won.hackerton.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import we_won.hackerton.entity.Comment;
import we_won.hackerton.entity.User_;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByArticle_Id(@Param(value = "articleId") Long articleId);

  Comment findByUser(User_ byNickname);

  Optional<Comment> findById(Long id);

  List<Comment> findAllByUser_Id(Long userId);

}
