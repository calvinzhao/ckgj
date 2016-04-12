package com.ckgj.models.user;

import com.ckgj.models.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserForm {

    @NotEmpty
    private String phone = "";

    @NotEmpty
    private String password = "";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @NotNull
    private Long companyId = 0l;

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String passwordRepeated = "";
    @NotNull
    private Role role = Role.USER;
    private long id = 0l;

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public UserForm() {}

    public UserForm(User user) {
        role = user.getRole();
        id = user.getId();
        name = user.getName();
        companyId = user.getCompany().getId();
        password = "";
        passwordRepeated = "";
        phone = user.getPhone();
    }
    public void setId(long id) {
        this.id = id;
    }
}
