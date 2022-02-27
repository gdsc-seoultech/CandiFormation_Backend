package we_won.hackerton.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.Interface.UserService;
import we_won.hackerton.entity.User_;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User_> account = this.userRepository.findByUsername(username);


        // 로드임
        /**
         * Username 값이 DATA DB 에 존재하지 않는 경우
         * UsernameNotFoundException 에러 메소드를 사용합니다.
         * */
        if (account.isPresent()) {
            return User.builder()
                    .username(account.get().getUsername())
                    .password(account.get().getPassword())
                    .roles(account.get().getRole().getKey())
                    .build();
        } else {
            throw new UsernameNotFoundException(username + "정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public User_ saveOrUpdateAccount(User_ user) {
        user.encodePassword(this.passwordEncoder);

        return this.userRepository.save(user);
    }

    public ResponseEntity<?> duplicateUsernameAndNickname(User_ user) {
        boolean isNicknamePresent = userRepository.findByNickname(user.getNickname()).isPresent();
        boolean isUsernamePresent = userRepository.findByUsername(user.getUsername()).isPresent();
        if (isNicknamePresent) {
            return new ResponseEntity<>("해당 닉네임이 이미 존재합니다.", HttpStatus.BAD_REQUEST);
        } else if (isUsernamePresent) {
            return new ResponseEntity<>("해당 이메일이 이미 존재합니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
