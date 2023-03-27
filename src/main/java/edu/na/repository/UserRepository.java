package edu.na.repository;

import edu.na.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u.user_name from User u where u.user_name=?1")
    User findByUserName(String username);
}
