package we_won.hackerton.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import we_won.hackerton.Interface.ArticleRepository;
import we_won.hackerton.Interface.CommentRepository;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.dto.CommentDTO;
import we_won.hackerton.entity.Article;
import we_won.hackerton.entity.Comment;
import we_won.hackerton.entity.User_;
import we_won.hackerton.mapper.CommentMapper;
import we_won.hackerton.response.SuccessAndFailureResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final ArticleRepository articleRepository;
  private final CommentMapper commentMapper;
  private final ModelMapper modelMapper;


  public ResponseEntity<?> postComments(CommentDTO.CommentRequest request) {

    Comment comment = modelMapper.map(request, Comment.class);
    Article article = articleRepository.getById(request.getArticleId());
    if (userRepository.findByNickname(request.getNickname()).isPresent()) {
      comment.setUser(userRepository.findByNickname(request.getNickname()).get());
    }
    comment.setArticle(articleRepository.getById(request.getArticleId()));
    article.setComment_num(article.getComment_num()+1);
    articleRepository.save(article);
    commentRepository.save(comment);

    final SuccessAndFailureResponse successAndFailureResponse = new SuccessAndFailureResponse("댓글 작성을 완료했습니다.",200);
    return new ResponseEntity<>(successAndFailureResponse, HttpStatus.OK);

  }

  public List<CommentDTO.CommentResponse> getComments(Long articleId) {
    //System.out.println("service의 articleId" + articleId);
    List<Comment> comments = commentRepository.findAllByArticle_Id(articleId);
    //System.out.println("servicec의 commentes " + comments);
    List<CommentDTO.CommentResponse> commentDTOList = new ArrayList<>();

    for (Comment comment : comments) {
      commentDTOList.add(commentMapper.toDomain(comment));
    }
    //System.out.println("servicec의 CommentDTO.CommentResponse " + commentDTOList);
    return commentDTOList;
  }

  public ResponseEntity<?> deleteComment(String nickname, Long commentId,Long articleId) {
    Optional<Comment> comment = commentRepository.findById(commentId);
    if (Objects.equals(nickname, comment.get().getUser().getNickname())) {
      commentRepository.deleteById(commentId);
      //삭제를 했으니..
      //다시 article_id로 commentRepository에서 댓글 개수를 찾아주고
      //그걸 article entity에 setter 해주고 다시 저장..?
      List<Comment> comments = commentRepository.findAllByArticle_Id(articleId); //articleid로 모든 댓글을 찾아줌
      int comments_num = comments.size(); //기사에 대한 모든 댓글 개수
      Optional<Article> article = articleRepository.findById(articleId); //뉴스 기사를 찾아주고
      article.get().setComment_num(comments_num); //개수 업데이트 해주고
      articleRepository.save(article.get()); //다시 저장
      return new ResponseEntity<>("댓글을 삭제했습니다.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("댓글을 삭제할 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
  }

//  public List<CommentDTO.CommentResponse> getComments(Long articleId) {
//    //System.out.println("service의 articleId" + articleId);
//    List<Comment> comments = commentRepository.findAllByArticle_Id(articleId);
//    //System.out.println("servicec의 commentes " + comments);
//    List<CommentDTO.CommentResponse> commentDTOList = new ArrayList<>();
//
//    for (Comment comment : comments) {
//      commentDTOList.add(commentMapper.toDomain(comment));
//    }
//    //System.out.println("servicec의 CommentDTO.CommentResponse " + commentDTOList);
//    return commentDTOList;
//  }
  public List<CommentDTO.CommentResponse> getUserComment(String username){
    Optional<User_> user = userRepository.findByUsername(username);
    List<Comment> comments = commentRepository.findAllByUser_Id(user.get().getId());

    List<CommentDTO.CommentResponse> commentDTOList = new ArrayList<>();
    for (Comment comment : comments) {
      commentDTOList.add(commentMapper.toDomain(comment));
    }

    return commentDTOList;
  }
//  private Long commentId; // 삭제하려는 코멘트의 아이디
//  private String nickname; // 삭제하려는 유저의 닉네임
}
