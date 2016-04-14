package com.ckgj.controller;

import com.ckgj.models.user.User;
import com.ckgj.models.wxuser.WxUser;
import com.ckgj.services.user.UserService;
import com.ckgj.services.wechat.WeChatService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    WeChatService weChatService;
    @Autowired
    UserService userService;
    private final static String OPEN_ID_COOKIE = "wechatopenid";


    // 返回错误：https://www.jayway.com/2014/10/19/spring-boot-error-responses/
    // 返回json https://spring.io/guides/gs/actuator-service/
    // post请求redirect， http://stackoverflow.com/questions/2543797/spring-redirect-after-post-even-with-validation-errors
    @RequestMapping(value = "bind", method = RequestMethod.POST)
    public String bindWeChat(@RequestParam(name="phone") String phone, @RequestParam(name="password") String password,
                                   @CookieValue(OPEN_ID_COOKIE) Optional<String> openId, Model model) {
        // step 3 用户用手机，密码登录，验证成功则绑定用户
        // TODO
        Optional<User> userOptional = userService.validUser(phone, password);
        User user = weChatService.bindUser(openId.get(), userOptional.get());
        model.addAttribute("user", user);
        return "redirect:wechat/home";
    }

    @RequestMapping("login")
    public String login(@RequestParam Optional<String> code, HttpServletResponse response) {
        // step 2, 微信服务器重定向到服务器带code，用code得到用户token and 用户信息
        // 若成功返回登陆页面
        WxUser wxUser;
        // TODO: code.empty() re ouaht2 or promote
        try {
            wxUser = weChatService.createOrGetWxUser(code.get());
        } catch (WxErrorException e) {
            // TODO: reoauth2 or promote error msg
        }
        Cookie cookie = new Cookie(OPEN_ID_COOKIE, wxUser.getOpenId());

        response.addCookie(cookie);

        // TODO: check wxUser.getUser() null return view, else ?
        return "";
    }

    @RequestMapping("oauth2")
    public String oauth2Url() {
        // step 1 微信oauth2链接
        return weChatService.getOauth2Url();
    }
}
