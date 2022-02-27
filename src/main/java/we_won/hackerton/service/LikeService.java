package we_won.hackerton.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import we_won.hackerton.Interface.*;
import we_won.hackerton.dao.UserCommentDAO;
import we_won.hackerton.dto.CommentLikeDTO;
import we_won.hackerton.entity.Comment;
import we_won.hackerton.entity.CommentLike;
import we_won.hackerton.entity.User_;
import we_won.hackerton.response.SuccessAndFailureResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;
  private final LikeRepository likeRepository;
  private final UserCommentDAO userCommentDAO;
  private final CommentLikeRepository commentLikeRepository;

  /*
    String nickname;
    Long commentId;
   */
//  public ResponseEntity<?> clickLike(@RequestBody CommentLikeDTO request) {
//    Optional<User_> user = userRepository.findByNickname(request.getNickname());
//    Optional<Comment> comment = commentRepository.findById(request.getCommentId());
//    Optional<Like> like = Optional.ofNullable(likeRepository.findByUserAndComment(user.get(), comment));
////    Like like = new Like();
////    like.setUser(user);
//    //like.setComment(commentRepository.findById(request.getCommentId()).get());
//
//    if (user.isEmpty()) {
//      final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("로그인을 한 회원만 좋아요를 누를 수 있습니다.", HttpStatus.OK);
//      return new ResponseEntity<>(successAndFailureResponse, HttpStatus.BAD_REQUEST);
//    } else if (likeRepository.existsByUserAndComment(user.get(), comment)) {
//      final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("이미 좋아요를 누른 댓글입니다.", HttpStatus.OK);
//      return new ResponseEntity<>(successAndFailureResponse, HttpStatus.BAD_REQUEST);
//    } else {
//      comment.get().setLikeNum(comment.get().getLikeNum() + 1);
//      likeRepository.save(like.get());
//      final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("좋아요를 눌렀습니다", HttpStatus.OK);
//      return new ResponseEntity<>(successAndFailureResponse, HttpStatus.OK);
//    }
//
//
////    else if (comment.get().getLikedUser().contains(user)) {
////      return new ResponseEntity<>("이미 좋아요를 누른 댓글입니다.", HttpStatus.BAD_REQUEST);
////    } else {
////      comment.get().getLikedUser().add(user);
////      comment.get().setLikeNum(comment.get().getLikeNum() + 1);
////      return new ResponseEntity<>(HttpStatus.OK);
//  }
//////    if (commentRepository.findByUser(userRepository.findByNickname(request.getNickname())).getLikedUser().contains(userRepository.findByNickname(request.getNickname()))) {
//////      return new ResponseEntity<>("이미 좋아요를 누른 댓글입니다.", HttpStatus.BAD_REQUEST);
//////    } else {
//////      commentRepository.findByUser(userRepository.findByNickname(request.getNickname())).setLikeNum(commentRepository.findByUser(userRepository.findByNickname(request.getNickname())).getLikeNum()+1);
//////
//////    }

//  public ResponseEntity<?> clickLike(String nickname, Long commentId) {
//    final Optional<User_> user = userRepository.findByNickname(nickname);
//    final Optional<Comment> comment = commentRepository.findById(commentId);
//    final SuccessAndFailureResponse result = new SuccessAndFailureResponse("댓글을 좋아요 했습니다", HttpStatus.OK);
//    final CommentLike commentLike = new CommentLike();
//    commentLike.setUser(user.get());
//    commentLike.setComment(comment.get());
//
//    userCommentDAO.save(commentLike);
//
//    return new ResponseEntity<>(result, HttpStatus.OK);
//
//  }

  public ResponseEntity<?> clickLike(String nickname, Long commentId) {
    User_ user = userRepository.getByNickname(nickname);
    Long userId = user.getId();
    Optional<Comment> comment = commentRepository.findById(commentId);

    CommentLike commentLike = new CommentLike(userId, commentId);

    if (commentLikeRepository.existsByUserIdAndCommentId(userId, commentId)) {
      return new ResponseEntity<>("이미 좋아요를 누른 댓글입니다.", HttpStatus.BAD_REQUEST);
    } else {
      commentLikeRepository.save(commentLike);
      comment.get().setLikeNum(comment.get().getLikeNum() + 1);
      return new ResponseEntity<>("댓글을 좋아요 했습니다.", HttpStatus.OK);
    }

  }
}



