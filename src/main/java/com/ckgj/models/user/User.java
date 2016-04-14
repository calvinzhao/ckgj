package com.ckgj.models.user;

import com.ckgj.models.company.Company;
import com.ckgj.models.wxuser.WxUser;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by calvin on 3/25/16.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="wxuser_id")
    private WxUser wxUser;

    public Date getDateCreated() {

        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {

        return id;
    }

    // getters, setters
    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public String getWxNickname() {
        WxUser wxuser = getWxUser();
        if (wxuser != null) {
            return wxuser.getNickname();
        } else {
            return "__未绑定__";
        }
    }
}


