package com.genuinecoder.learnspringsecurity.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepositoy extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String userName) ;

}
