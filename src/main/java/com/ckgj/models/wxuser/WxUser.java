package com.ckgj.models.wxuser;

import com.ckgj.models.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class WxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "open_id", nullable = false, unique = true)
    private String openId;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    @Column(name = "nickname")
    private String nickname;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Date getDateCreated() {

        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
