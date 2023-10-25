package com.example.userms.repo;

import com.example.userms.entity.RoleEnum;
import com.example.userms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);



    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.role = ?1 ")
    List<User> findAllusersByrole(RoleEnum roleEnum);

    List<User> findByAccountLockedTrue();

    List<User> findByFailedLoginAttemptsGreaterThanOrderByFailedLoginAttemptsDesc(int count);

    User findById(User user);
}
