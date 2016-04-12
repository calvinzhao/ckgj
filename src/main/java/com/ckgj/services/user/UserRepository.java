package com.ckgj.services.user;

import com.ckgj.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByPhone(String phone);
}
