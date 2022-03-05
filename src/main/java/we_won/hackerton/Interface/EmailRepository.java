package we_won.hackerton.Interface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import we_won.hackerton.entity.Email;

import java.util.Optional;

@Repository
public interface EmailRepository extends CrudRepository<Email,String> {

    Optional<Email> findByCode(String code);
    Optional<Email> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    Optional<Email> findOne(Long id);
}
