package org.sparta.jpaschedule.user.repository;

import org.sparta.jpaschedule.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
