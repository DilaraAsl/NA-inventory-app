package edu.na.repository;

import edu.na.entity.Role;
import edu.na.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select u from User u where u.user_name=?1")
    User findByUserName(String username);
    @Query(value = "select u.role  from User u where u.user_name=?1")
    Role retrieveRoleOfUser(String username);
}
