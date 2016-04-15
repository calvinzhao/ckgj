package com.ckgj.controller;

import com.ckgj.models.user.User;
import com.ckgj.models.wxuser.WxOauthState;
import com.ckgj.models.wxuser.WxUser;
import com.ckgj.services.user.UserService;
import com.ckgj.services.wechat.WeChatService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    WeChatService weChatService;
    @Autowired
    UserService userService;
    private final static String OPEN_ID_COOKIE = "wechatopenid";

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @RequestMapping(value = "home")
    public String homePage(@CookieValue(OPEN_ID_COOKIE) Optional<String> openId, Model model) {
        // step 3 用户用手机，密码登录，验证成功则绑定用户
        // TODO
        if (!openId.isPresent()) {
            return "redirect:" + weChatService.getOauth2Url();
        }
        if (!model.containsAttribute("user_name")) {
            Optional<User> userOptional = weChatService.getUserByOpenId(openId.get());
            if (!userOptional.isPresent()) {
                return "redirect:" + weChatService.getOauth2Url(WxOauthState.RELOGIN_STATE);
            }
            User user = userOptional.get();
            model.addAttribute("user_name", user.getName());
            model.addAttribute("user_phone", user.getPhone());
            model.addAttribute("user_company", user.getCompany());
        }
        return "wechat/home";
    }

    // 返回json https://spring.io/guides/gs/actuator-service/
    @RequestMapping(value = "bind", method = RequestMethod.POST)
    public String bindWeChat(@RequestParam(name="phone") String phone, @RequestParam(name="password") String password,
                                   @CookieValue(OPEN_ID_COOKIE) Optional<String> openId, RedirectAttributes attr) {
        // step 3 用户用手机，密码登录，验证成功则绑定用户
        // 用户验证失败或已经绑定其它用户,弹警告窗
        // 没有找到wxuser,openid不存在活着有错,重新微信登陆
        if (!openId.isPresent()) {
            return "redirect:" + weChatService.getOauth2Url();
        }
        User user = userService.validUser(phone, password).orElseThrow(() -> new IllegalArgumentException("user not exist or password is wrong."));
        try {
            user = weChatService.bindUser(openId.get(), user);
            attr.addAttribute("user_name", user.getName());
            attr.addAttribute("user_company", user.getCompany());
            attr.addAttribute("user_phone", user.getPhone());
        } catch (NoSuchElementException e) {
            return "redirect:" + weChatService.getOauth2Url(WxOauthState.RELOGIN_STATE);
        }
        return "redirect:/wechat/home";
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam Optional<String> code, HttpServletResponse response) {
        // step 2, 微信服务器重定向到服务器带code，用code得到用户token and 用户信息
        // 若成功返回登陆页面或用户主页
        WxUser wxUser;
        if (!code.isPresent()) {
            throw new IllegalArgumentException("login error");
        }
        try {
            wxUser = weChatService.createOrGetWxUser(code.get());
        } catch (WxErrorException e) {
            throw new NoSuchElementException("wechat server error");
        }
        Cookie cookie = new Cookie(OPEN_ID_COOKIE, wxUser.getOpenId());
        response.addCookie(cookie);

        ModelAndView retView;
        User user = wxUser.getUser();
        if (user != null) {
            retView = new ModelAndView("wechat/home", "user_name", user.getName());
            retView.addObject("user_phone", user.getPhone());
            retView.addObject("user_company", user.getCompany());
        } else {
            retView = new ModelAndView("wechat/login");
        }

        return retView;
    }

    @RequestMapping("oauth2")
    public @ResponseBody String oauth2Url() {
        // step 1 微信oauth2链接
        return weChatService.getOauth2Url();
    }

    @RequestMapping("token")
    public String token(@RequestParam String echostr) {
        return echostr;
    }
}
