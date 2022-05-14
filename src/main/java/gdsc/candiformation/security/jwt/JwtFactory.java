package gdsc.candiformation.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import gdsc.candiformation.Interface.UserRepository;
import gdsc.candiformation.entity.User_;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);


    private final UserRepository userRepository;

    @Autowired
    public JwtFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1.
    public String generateToken(UserDetails userDetails) {
        String token = null;
        try {
            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
            String role = roles.iterator().next();

            Optional<User_> user = userRepository.findByUsername(userDetails.getUsername());

            token = JWT.create()
                    .withIssuer("jjunpro")
                    .withClaim("USERNAME", userDetails.getUsername())
                    .withClaim("USER_ROLE", role)
                    .withClaim("USER_NICKNAME",user.get().getNickname())
                    .withClaim("EXP", new Date(System.currentTimeMillis() + 864000000))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    // 2.
    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        String signingKey = "jwttest";
        return Algorithm.HMAC256(signingKey);
    }
}