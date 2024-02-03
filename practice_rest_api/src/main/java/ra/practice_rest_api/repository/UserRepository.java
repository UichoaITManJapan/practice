package ra.practice_rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.practice_rest_api.model.User;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Page<User> findByNameContainsAndCreatedBetween(String userName, Date fromCreated, Date toCreated, Pageable pageable);
}
