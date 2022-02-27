package we_won.hackerton.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

public class CommentDTO {

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommentRequest {
    private Long articleId;
    private String nickname;
    private Boolean isSecret;
    private String content;
    private String createdAt;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommentResponse {
    private Long id;
    private String nickname;
    private Boolean isSecret;
    private Long articleId;
    private int likeNum;
    private String content;
    private String createdAt;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommentDeleteRequest {
    private Long commentId; // 삭제하려는 코멘트의 아이디
    private String nickname; // 삭제하려는 유저의 닉네임
  }

}


