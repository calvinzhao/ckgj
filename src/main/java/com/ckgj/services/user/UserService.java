package com.ckgj.services.user;

import com.ckgj.models.user.User;
import com.ckgj.models.user.UserForm;
import com.ckgj.models.wxuser.WxUser;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByPhone(String phone);

    Collection<User> getAllUsers();

    User createOrUpdate(UserForm form);

    void deleteUser(Long id);

    User bindWxUser(User user, WxUser wxUser);

    Optional<User> validUser(String phone, String password);
}
