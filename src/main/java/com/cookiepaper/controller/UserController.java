package com.cookiepaper.controller;

import com.cookiepaper.entity.User;
import com.cookiepaper.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 아이디 중복 확인
    @RequestMapping(value={ "checkId" }, method= RequestMethod.POST)
    public @ResponseBody String checkId(String usId) throws Exception {
        Long count = this.userService.checkId(usId);
        return count > 0 ? "ok" : "not ok";
    }

    // 이메일 중복 확인
    @RequestMapping(value={ "checkEmail" }, method= RequestMethod.POST)
    public @ResponseBody String checkEmail(String usEmail) throws Exception {
        Long count = this.userService.checkEmail(usEmail);
        return count > 0 ? "ok" : "not ok";
    }

    // 회원가입
    @RequestMapping(value={ "signUp" }, method= RequestMethod.POST)
    public @ResponseBody String signUp(User user) throws Exception {
        User newUser = new User().builder()
                        .usId(user.getUsId())
                        .usPassword(user.getUsPassword())
                        .usNickname(user.getUsNickname())
                        .usEmail(user.getUsEmail())
                        .build();
        return this.userService.signUp(newUser) != null ? "success" : "fail";
    }

}