package gdsc.candiformation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessAndFailureResponse<T> extends BasicResponse {

    private T message;
    private int status;

    public SuccessAndFailureResponse(T message, int status) {
        this.message = message;
        this.status = status;
    }
}
