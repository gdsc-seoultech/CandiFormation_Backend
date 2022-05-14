package gdsc.candiformation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import gdsc.candiformation.Interface.ArticleRepository;
import gdsc.candiformation.Interface.UserRepository;
import gdsc.candiformation.dto.CommentDTO;
import gdsc.candiformation.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentMapper {

  private final UserRepository userRepository;
  private final ArticleRepository articleRepository;

  public CommentDTO.CommentResponse toDomain(Comment comment) {
    return new CommentDTO.CommentResponse(comment.getId(), comment.getUser().getNickname(), comment.getIsSecret(), comment.getArticle().getId(), comment.getLikeNum(), comment.getContent(), comment.getCreatedAt().toString());
  }

//  public Comment toEntity(CommentDTO.CommentRequest request) {
//    return new Comment(userRepository.findByNickname(request.getNickname()),
//                        articleRepository.findById(request.getArticleId()),
//                          request.getContent(),
//                          request.getIsSecret(),
//                          request.getNickname(),
//                          request.getCreatedAt()
//      );
//  }
}


