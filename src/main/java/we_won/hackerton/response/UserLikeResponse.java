package we_won.hackerton.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLikeResponse extends BasicResponse{

    private List<Long> articles;
    private String username;

    public UserLikeResponse(List<Long> articles, String username) {
        this.articles = articles;
        this.username = username;
    }
}
