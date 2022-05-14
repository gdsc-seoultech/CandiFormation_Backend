package gdsc.candiformation.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gdsc.candiformation.entity.User_;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_,Long> {
    Optional<User_> findByUsername(String login_id);
    Optional<User_> getByUsername(String username);

    Optional<User_> findByNickname(String nickname);

    User_ getByNickname(String nickname);
}
