package gdsc.candiformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gdsc.candiformation.dto.CommentLikeDTO;
import gdsc.candiformation.service.LikeService;

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
