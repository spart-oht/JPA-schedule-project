package org.sparta.jpaschedule.user.repository;

import org.sparta.jpaschedule.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
