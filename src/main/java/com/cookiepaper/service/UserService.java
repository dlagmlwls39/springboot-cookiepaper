package com.cookiepaper.service;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.entity.User;

import java.util.Map;

public interface UserService {

    // 아이디 존재 여부 확인
    public Long checkId(String usId) throws Exception;

    // 이메일 존재 여부 확인
    public Long checkEmail(String usEmail) throws Exception;

    // 회원가입
    public User signUp(UserDto userDto) throws Exception;

    // 로그인
    public Map<String, Object> login(UserDto userDto) throws Exception;

    // 아이디, 이메일 일치 여부 확인
    public Long checkUser(String usId, String usEmail) throws Exception;

    // 비밀번호 재설정
    public User updatePassword(String usId, String usEmail, String usPassword) throws Exception;

}