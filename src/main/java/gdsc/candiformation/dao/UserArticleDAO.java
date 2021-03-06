package gdsc.candiformation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gdsc.candiformation.entity.UserArticleScrap;
import gdsc.candiformation.entity.UserArticleScrapID;

import java.util.List;
import java.util.Optional;

public interface UserArticleDAO extends JpaRepository<UserArticleScrap, UserArticleScrapID> {
    List<UserArticleScrap> findAllByUser_id(long user_id);
    List<UserArticleScrap> findAllByArticle_id(long article_id);
    Optional<UserArticleScrap> findUserArticleScrapByArticle_IdAndUser_Id(long article_id, long user_id);
    Optional<UserArticleScrap> deleteUserArticleScrapByArticle_IdAndUser_Id(long article_id, long user_id);
}
