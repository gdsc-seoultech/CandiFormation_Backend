package we_won.hackerton.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.dto.TokenDTO;
import we_won.hackerton.entity.User_;
import we_won.hackerton.security.jwt.JwtFactory;
import we_won.hackerton.security.token.PostAuthorizationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 2.
    private final JwtFactory factory;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    // 1.
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest req,
            HttpServletResponse res,
            Authentication auth
    ) throws IOException {

        PostAuthorizationToken token   = (PostAuthorizationToken) auth;
        UserDetails userDetails = token.getUserDetails();

        // 2.
        String tokenString = factory.generateToken(userDetails);
        Optional<User_> user = userRepository.findByUsername(userDetails.getUsername());
        // 3.
        TokenDTO tokenDTO = new TokenDTO(tokenString, userDetails.getUsername(),user.get().getNickname());

        processResponse(res, tokenDTO);
    }

    private void processResponse(
            HttpServletResponse res,
            TokenDTO dto
    ) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
