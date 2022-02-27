package we_won.hackerton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import we_won.hackerton.Interface.ArticleRepository;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.dao.UserArticleDAO;
import we_won.hackerton.entity.Article;
import we_won.hackerton.entity.UserArticleScrap;
import we_won.hackerton.entity.User_;
import we_won.hackerton.response.SuccessAndFailureResponse;
import we_won.hackerton.response.UserLikeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final UserArticleDAO userArticleDAO;



    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, UserArticleDAO userArticleDAO) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.userArticleDAO = userArticleDAO;

    }



    //모든 뉴스를 불러옴
    //뉴스란에서 다 볼 수 있게


    //유저가 원하는 기사를 스크랩 함
    public ResponseEntity<?> scrapArticle(String username, long article_id){

        final Optional<User_> user = userRepository.findByUsername(username);
        final Optional<Article> article = articleRepository.findById(article_id);
        final SuccessAndFailureResponse result = new SuccessAndFailureResponse("스크랩을 성공했습니다.",200);
        final UserArticleScrap newArticleScrap = new UserArticleScrap();
        newArticleScrap.setUser(user.get());
        newArticleScrap.setArticle(article.get());

        userArticleDAO.save(newArticleScrap);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //유저가 스크랩한 글을 볼 수 있음
    public ResponseEntity<?> getUserArticles(String username){
        final Optional<User_> user = userRepository.getByUsername(username);
        if(user.isEmpty()){
            final SuccessAndFailureResponse result = new SuccessAndFailureResponse("아이디가 존재하지 않습니다.",400);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        final List<UserArticleScrap> userArticleScraps = userArticleDAO.findAllByUser_id(user.get().getId());
        final List<Article> articleList = new ArrayList<>();
        for(int i = 0;i<userArticleScraps.size();i++){
            articleList.add(userArticleScraps.get(i).getArticle());
        }

        return new ResponseEntity<>(articleList,HttpStatus.OK);
    }

    //기사의 좋아요수를 업데이트 함
    public ResponseEntity<?> updateArticleLikeNum(long article_id,String username){
        Optional<User_> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            final SuccessAndFailureResponse result = new SuccessAndFailureResponse("존재하지 않는 유저입니다.",400);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        Optional<Article> article = articleRepository.findById(article_id);
        if(article.isEmpty()){
            final SuccessAndFailureResponse result = new SuccessAndFailureResponse("존재하지 않는 기사입니다.",400);
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        Optional<UserArticleScrap> userArticleScrap = userArticleDAO.findUserArticleScrapByArticle_IdAndUser_Id(article_id,user.get().getId());

        if(userArticleScrap.isPresent()){
            //이미 좋아요가 눌러졌을 때
            long like_num = article.get().getLike_num();
            if(like_num > 0){
                //좋아요 수를 1 감소 시키고
                //다시 db에 저장
                article.get().setLike_num(like_num - 1);
                articleRepository.save(article.get());
            }

            //좋아요 취소된 걸 DAO에서 삭제함
            final UserArticleScrap newArticleScrap = new UserArticleScrap();
            newArticleScrap.setUser(user.get());
            newArticleScrap.setArticle(article.get());
            userArticleDAO.delete(newArticleScrap);

            final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("좋아요를 취소 했습니다.",200);
            return new ResponseEntity<>(successAndFailureResponse,HttpStatus.OK);
        }

        //뉴스의 전체 좋아요 개수
        List<UserArticleScrap> userArticleScrap_list = userArticleDAO.findAllByArticle_id(article_id);

        //좋아요 눌러진 뉴스의 좋아요 수 + 1 증가
        article.get().setLike_num(userArticleScrap_list.size() + 1);
        articleRepository.save(article.get());

        //좋아요 누른 뉴스 저장
        final UserArticleScrap newArticleScrap = new UserArticleScrap();
        newArticleScrap.setUser(user.get());
        newArticleScrap.setArticle(article.get());
        userArticleDAO.save(newArticleScrap);

        //메시지 출력
        final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("좋아요를 눌렀습니다.",200);
        return new ResponseEntity<>(successAndFailureResponse,HttpStatus.OK);
    }

    public ResponseEntity<?> getUserLikeArticles(String username){
        Optional<User_> user = userRepository.findByUsername(username);
        List<UserArticleScrap> userLikes = userArticleDAO.findAllByUser_id(user.get().getId());

        //System.out.println(userLikes);
        List<Long> userLikesArticleIds = new ArrayList<>();
        for(int i = 0;i<userLikes.size();i++){
            userLikesArticleIds.add(userLikes.get(i).getArticle().getId());
        }
        UserLikeResponse userLikeResponse = new UserLikeResponse(userLikesArticleIds,username);
        return new ResponseEntity<>(userLikeResponse,HttpStatus.OK);
    }

    public ResponseEntity<?> getAllArticles(){
        List<Article> allArticle = articleRepository.findAll();
        return new ResponseEntity<>(allArticle, HttpStatus.OK);
    }

    public ResponseEntity<?> getArticleLikeNum(Long articleId){
        Optional<Article> article = articleRepository.findById(articleId);
        HashMap<String,Long> hashMap = new HashMap<>();
        hashMap.put("likes",article.get().getLike_num());
        return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }

}
