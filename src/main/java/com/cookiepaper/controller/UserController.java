package com.cookiepaper.controller;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.service.MailService;
import com.cookiepaper.service.MailServiceImpl;
import com.cookiepaper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/*")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    public UserController(UserService userService, MailServiceImpl mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    // 아이디 중복 확인
    @RequestMapping(value={ "checkId" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity checkId(String usId) throws Exception {
        Long count = userService.checkId(usId);
        return count <= 0 ? new ResponseEntity<>("ok", HttpStatus.OK) : new ResponseEntity<>("not ok", HttpStatus.OK);
    }

    // 이메일 중복 확인
    @RequestMapping(value={ "checkEmail" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity checkEmail(String usEmail) throws Exception {
        Long count = userService.checkEmail(usEmail);
        if (count <= 0) {  // 사용 가능한 이메일인 경우 인증번호 발송
            String authKey = mailService.sendMail(usEmail, "signUp");
            return new ResponseEntity<>(authKey, HttpStatus.OK);
        } else {  // 사용 불가능한 이메일인 경우
            return new ResponseEntity<>("not ok", HttpStatus.OK);
        }
    }

    // 회원가입
    @RequestMapping(value={ "signUp" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity signUp(UserDto userDto) throws Exception {
        return userService.signUp(userDto) != null ?
                new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.OK);
    }

    // 로그인
    @RequestMapping(value={ "login" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity login(UserDto userDto) throws Exception {
        Map<String, Object> map = userService.login(userDto);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 아이디 찾기
    @RequestMapping(value={ "findId" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity findId(String usEmail) throws Exception {
        Long count = userService.checkEmail(usEmail);
        if (count == 1) {  // 존재하는 이메일인 경우 아이디 발송
            String usId = mailService.sendMail(usEmail, "findId");
            return new ResponseEntity<>(usId, HttpStatus.OK);
        } else {  // 존재하지 않는 이메일인 경우
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    // 비밀번호 찾기 - 인증번호 이메일 발송
    @RequestMapping(value={ "findPassword" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity findPassword(String usId, String usEmail) throws Exception {
        Long count = userService.checkUser(usId, usEmail);
        if (count == 1) {  // 존재하는 회원인 경우 아이디 발송
            String authKey = mailService.sendMail(usEmail, "findPassword");
            return new ResponseEntity<>(authKey, HttpStatus.OK);
        } else {  // 존재하지 않는 회원인 경우
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    // 비밀번호 재설정
    @RequestMapping(value={ "updatePassword" }, method= RequestMethod.POST)
    public @ResponseBody ResponseEntity updatePassword(String usId, String usPassword) throws Exception {
        return userService.updatePassword(usId, usPassword) != null ?
                new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.OK);
    }

}