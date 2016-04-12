package com.ckgj.services.user;

import com.ckgj.models.user.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by calvin on 3/25/16.
*/

@Component
public class UserCreateFormValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm form = (UserForm) target;
        validatePasswords(errors, form);
        validatePhone(errors, form);
    }

    private void validatePasswords(Errors errors, UserForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validatePhone(Errors errors, UserForm form) {
        // TODO: validate phone number
        if (userService.getUserByPhone(form.getPhone()).isPresent()) {
            errors.reject("email.exists", "User with this phone already exists");
        }
    }
}
