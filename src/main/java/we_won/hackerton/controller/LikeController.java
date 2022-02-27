package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import we_won.hackerton.dto.CommentLikeDTO;
import we_won.hackerton.dto.UserArticleScrapDTO;
import we_won.hackerton.service.LikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class LikeController {

  @Autowired
  private LikeService likeService;

//  @PostMapping("/{commentId}/like")
//  public ResponseEntity<?> clickLike(@PathVariable("commentId") Long commentId,
//                                     @RequestBody CommentLikeDTO request) {
//    return likeService.clickLike(request);
//  }

  /*
  String nickname;
  Long commentId;
   */

  @PostMapping("")
  public ResponseEntity<?> clickLike(@RequestBody CommentLikeDTO commentLikeDTO){
    return likeService.clickLike(commentLikeDTO.getNickname(),commentLikeDTO.getCommentId());
  }
}
