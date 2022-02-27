package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import we_won.hackerton.dto.CommentDTO;
import we_won.hackerton.service.CommentService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping("/{articleId}")
  public List<CommentDTO.CommentResponse> getComments(@PathVariable("articleId") Long articleId) {
    System.out.println("Controller의 articleId" + articleId);
    return commentService.getComments(articleId);
  }

  @PostMapping("")
  public ResponseEntity<?> postComment(@RequestBody CommentDTO.CommentRequest commentDto) {
    return commentService.postComments(commentDto);
  }

  @DeleteMapping("/{nickname}/{articleId}/{commentId}")
  public ResponseEntity<?> deleteComment(@PathVariable("nickname") String nickname, @PathVariable("commentId") Long commentId
  ,@PathVariable("articleId") Long articleId) {
    return commentService.deleteComment(nickname,commentId,articleId);
  }

  @GetMapping("/users/{username}")
  public List<CommentDTO.CommentResponse> getUserComment(@PathVariable("username") String username){
    return commentService.getUserComment(username);
  }
}
