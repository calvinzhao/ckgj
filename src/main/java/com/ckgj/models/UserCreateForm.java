package com.ckgj.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by calvin on 3/25/16.
 */
public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {

        return role;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @NotNull
    private Role role = Role.USER;

}
