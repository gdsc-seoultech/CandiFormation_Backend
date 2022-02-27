package we_won.hackerton.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponse extends BasicResponse{

    private String message;
    private boolean verify;

    public EmailResponse(String message, boolean verify) {
        this.message = message;
        this.verify = verify;
    }
}
