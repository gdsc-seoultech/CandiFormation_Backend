package gdsc.candiformation.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import gdsc.candiformation.dto.UserDTO;
import gdsc.candiformation.security.filter.JwtPreProcessingToken;
import gdsc.candiformation.security.jwt.JwtDecoder;
import gdsc.candiformation.security.token.PostAuthorizationToken;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String     token      = (String) authentication.getPrincipal();
        UserDTO accountDTO = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFormUserDetails(accountDTO);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }

}
