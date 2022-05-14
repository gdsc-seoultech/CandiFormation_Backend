package gdsc.candiformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gdsc.candiformation.dto.LikeDTO;
import gdsc.candiformation.dto.UserArticleScrapDTO;
import gdsc.candiformation.service.ArticleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("") //모든 뉴스 기사를 불러옴
    public ResponseEntity<?> getAllArticles_(){
        return articleService.getAllArticles();
    }

    @PostMapping("") //기사 스크랩할 때
    public ResponseEntity<?> scrapArticle(@RequestBody UserArticleScrapDTO userArticleScrapDTO){
        return articleService.scrapArticle(userArticleScrapDTO.getUsername(),userArticleScrapDTO.getArticle_id());
    }

    @GetMapping("/{username}") //유저가 스크랩한 글을 불러옴
    public ResponseEntity<?> getUserArticles(@PathVariable("username") String username){
        return articleService.getUserArticles(username);
    }

    //좋아요 수 증가 및 감소
    @PutMapping("")
    public ResponseEntity<?> updateArticleLikeNum(@RequestBody LikeDTO likeDTO){
        return articleService.updateArticleLikeNum(likeDTO.getArticle_id(),likeDTO.getUsername());
    }

    @GetMapping("/likes/{username}")
    public ResponseEntity<?> getUserLikeArticles(@PathVariable("username") String username){
        return articleService.getUserLikeArticles(username);
    }

    @GetMapping("/{articleId}/likes")
    public ResponseEntity<?> getArticleLikeNum(@PathVariable("articleId") Long articleId){
        return articleService.getArticleLikeNum(articleId);
    }

}
