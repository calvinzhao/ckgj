package com.ckgj.services;

import com.ckgj.models.User;
import com.ckgj.models.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
