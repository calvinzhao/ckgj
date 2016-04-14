package com.ckgj.services.wechat;

import com.ckgj.config.WeChatConfig;
import com.ckgj.models.user.User;
import com.ckgj.models.wxuser.WxUser;
import com.ckgj.services.user.UserService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WeChatService {
    private final static Logger logger = LoggerFactory.getLogger(WeChatService.class);
    private final WxMpService wxMpService;
    @Autowired
    private final WeChatConfig weChatConfig;
    @Autowired
    private final WxUserRepository wxUserRepository;
    @Autowired
    private final UserService userService;

    @Autowired
    public WeChatService(WeChatConfig weChatConfig, WxUserRepository wxUserRepository, UserService userService) {
        logger.info("+++++++++++++++++++++++++init wechatservice" + weChatConfig);
        this.weChatConfig = weChatConfig;
        this.wxUserRepository = wxUserRepository;
        this.userService = userService;
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(weChatConfig.getWxAppId());
        config.setSecret(weChatConfig.getWxSecret());
        config.setToken(weChatConfig.getWxToken());
        config.setAesKey(weChatConfig.getWxAesKey());
//        config.setAppId("wx70c01952742520c8"); // 设置微信公众号的appid
//        config.setSecret("de544884eddf11c62af7d2bf0367d40e"); // 设置微信公众号的app corpSecret
//        config.setToken(""); // 设置微信公众号的token
//        config.setAesKey(""); // 设置微信公众号的EncodingAESKey

        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
    }

    public String getOauth2Url() {
        return wxMpService.oauth2buildAuthorizationUrl(weChatConfig.getRedirectUrl(), WxConsts.OAUTH2_SCOPE_USER_INFO, "123");
    }

    public WxMpUser getWxMpUser(String code) throws WxErrorException {
        WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(token, "zh_CN");
        return wxMpUser;
    }

    public Optional<WxUser> getWxUserByOpenId(String openId) {
        return wxUserRepository.findOneByOpenId(openId);
    }

    public WxUser createOrGetWxUser(String code) throws WxErrorException {
        WxMpUser wxMpUser = getWxMpUser(code);
        return createOrGetWxUser(wxMpUser);
    }

    public WxUser createOrGetWxUser(WxMpUser wxMpUser) {
        String openId = wxMpUser.getOpenId();
        String nickname = wxMpUser.getNickname();
        return createOrGetWxUser(openId, nickname);
    }

    public WxUser createOrGetWxUser(String openId, String nickname) {
        Optional<WxUser> wxUserOptional = getWxUserByOpenId(openId);
        if (wxUserOptional.isPresent()) {
            return wxUserOptional.get();
        } else {
            WxUser wxUser = new WxUser();
            wxUser.setOpenId(openId);
            wxUser.setNickname(nickname);
            wxUser.setDateCreated(new Date());
            return wxUserRepository.save(wxUser);
        }
    }

    public Optional<User> getUserByOpenId(String openId) {
        Optional<WxUser> wxUser = getWxUserByOpenId(openId);
        if (wxUser.isPresent()) {
            return Optional.ofNullable(wxUser.get().getUser());
        } else {
            return Optional.ofNullable(null);
        }
    }

    public User bindUser(String openId, User user) throws NoSuchElementException, InvalidParameterException {
        WxUser wxUser = getWxUserByOpenId(openId).orElseThrow(() -> new NoSuchElementException("[ERROR] WHEN GET WxUser BY OPENID " + openId));
        if (wxUser.getUser() != null) {
            if (wxUser.getUser().getId() != user.getId()) {
                throw new InvalidParameterException(String.format("[ERROR] TRY DOUBLE BIND TO openid=%s, old_user_id=%s, new_user_id=%s",
                        openId, wxUser.getUser().getId(), user.getId()));
            } else {
                return user;
            }
        }
        wxUser.setUser(user);
        wxUserRepository.save(wxUser);
        return userService.bindWxUser(user, wxUser);
    }
}
