package com.ckgj.services.wechat;

import com.ckgj.models.wxuser.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WxUserRepository  extends JpaRepository<WxUser, Long> {
    Optional<WxUser> findOneByOpenId(String openId);
}
