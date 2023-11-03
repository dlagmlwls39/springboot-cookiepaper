package com.cookiepaper.controller;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.entity.User;
import com.cookiepaper.service.MailService;
import com.cookiepaper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/*")
@CrossOrigin(origins = { "http://localhost:3000", "http://15.165.55.131:80", "https://cookie-paper.vercel.app" })
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    public UserController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    // 아이디 중복 확인
    @PostMapping("checkId")
    public @ResponseBody ResponseEntity checkId(@RequestBody UserDto userDto) throws Exception {
        Long count = userService.checkId(userDto.getUsId());
        return new ResponseEntity<>(count <= 0 ? "ok" : "not ok", HttpStatus.OK);
    }

    // 이메일 중복 확인
    @PostMapping("checkEmail")
    public @ResponseBody ResponseEntity checkEmail(@RequestBody UserDto userDto) throws Exception {
        Long count = userService.checkEmail(userDto.getUsEmail());
        if (count <= 0) {  // 사용 가능한 이메일인 경우 인증번호 발송
            String authKey = mailService.sendMail(userDto.getUsEmail(), "signUp");
            return new ResponseEntity<>(authKey, HttpStatus.OK);
        } else {  // 사용 불가능한 이메일인 경우
            return new ResponseEntity<>("not ok", HttpStatus.OK);
        }
    }

    // 회원가입
    @PostMapping("signUp")
    public @ResponseBody ResponseEntity signUp(@RequestBody UserDto userDto) throws Exception {
        User user = userService.signUp(userDto);
        return new ResponseEntity<>(user != null ? "success" : "fail", HttpStatus.OK);
    }

    // 로그인
    @PostMapping("login")
    public @ResponseBody ResponseEntity login(@RequestBody UserDto userDto) throws Exception {
        Map<String, Object> userMap = userService.login(userDto);
        return new ResponseEntity<>(userMap != null ? userMap : "fail", HttpStatus.OK);
    }

    // 아이디 찾기
    @PostMapping("findId")
    public @ResponseBody ResponseEntity findId(@RequestBody UserDto userDto) throws Exception {
        Long count = userService.checkEmail(userDto.getUsEmail());
        if (count == 1) {  // 존재하는 이메일인 경우 아이디 발송
            String usId = mailService.sendMail(userDto.getUsEmail(), "findId");
            return new ResponseEntity<>(usId, HttpStatus.OK);
        } else {  // 존재하지 않는 이메일인 경우
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    // 비밀번호 찾기 - 인증번호 이메일 발송
    @PostMapping("findPassword")
    public @ResponseBody ResponseEntity findPassword(@RequestBody UserDto userDto) throws Exception {
        Long count = userService.checkUser(userDto.getUsId(), userDto.getUsEmail());
        if (count == 1) {  // 존재하는 회원인 경우 인증번호 발송
            String authKey = mailService.sendMail(userDto.getUsEmail(), "findPassword");
            return new ResponseEntity<>(authKey, HttpStatus.OK);
        } else {  // 존재하지 않는 회원인 경우
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    // 비밀번호 재설정
    @PostMapping("updatePassword")
    public @ResponseBody ResponseEntity updatePassword(@RequestBody UserDto userDto) throws Exception {
        User user = userService.updatePassword(userDto.getUsId(), userDto.getUsEmail(), userDto.getUsPassword());
        return new ResponseEntity<>(user != null ? "success" : "fail", HttpStatus.OK);
    }

}