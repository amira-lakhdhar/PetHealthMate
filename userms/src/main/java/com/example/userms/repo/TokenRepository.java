package com.example.userms.repo;

import com.example.userms.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Integer> {

//    @Query("select t from Token t where t.user = ?1")

    @Query( " select t from Token t inner join User u on t.user.id = u.id where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Long id);

    @Query("select t from Token t where t.token = ?1")
    Optional<Token> findByToken(String token);
}