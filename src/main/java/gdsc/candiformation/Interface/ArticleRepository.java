package gdsc.candiformation.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gdsc.candiformation.entity.Article;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    //List<Article> findAll();

    @Override
    Optional<Article> findById(Long articleId);
}
