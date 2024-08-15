package com.bank.depositman.repository;

import com.bank.depositman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String email);

    User findById(User userId);

//    User findById(User userId);
}
