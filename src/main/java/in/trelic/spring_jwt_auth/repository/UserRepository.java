package in.trelic.spring_jwt_auth.repository;

import in.trelic.spring_jwt_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username LIKE ?1")
    User findUserByUserName(String username);
}
