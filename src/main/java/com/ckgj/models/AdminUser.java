package com.ckgj.models;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by calvin on 3/24/16.
 */
public class AdminUser {
    private Long id;
    @NotEmpty()
    private String email;
    @NotEmpty()
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {

        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
