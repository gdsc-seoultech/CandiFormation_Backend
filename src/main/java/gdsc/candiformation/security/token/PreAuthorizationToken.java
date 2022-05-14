package gdsc.candiformation.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import gdsc.candiformation.dto.UserFormDTO;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public PreAuthorizationToken(UserFormDTO dto) {
        this(dto.getUsername(), dto.getPassword());
    }

    public String getUsername() {
        return (String) super.getPrincipal();
    }

    public String getUserPassword() {
        return (String) super.getCredentials();
    }
}
