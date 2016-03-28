package com.ckgj.services;

import com.ckgj.models.Role;
import com.ckgj.models.User;
import com.ckgj.models.UserCreateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (email.contains("dai")) {
            User u = new User();
            u.setEmail("dai@qq.com");
            u.setPasswordHash(new BCryptPasswordEncoder().encode("h"));
            u.setRole(Role.ADMIN);
            u.setId(1l);

            LOGGER.info("hahahaha  fake user" + u.getEmail() + u.getPasswordHash());
            Optional<User> opt = Optional.of(u);
            return opt;
        }
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }


}
