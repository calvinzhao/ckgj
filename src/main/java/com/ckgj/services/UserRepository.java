package com.ckgj.services;

import com.ckgj.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
    //Optional<User> email();
    //String findOneByEmail();
    //List findAllByEmail();
}
