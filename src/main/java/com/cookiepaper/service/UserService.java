package com.cookiepaper.service;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.entity.User;

import java.util.Map;

public interface UserService {

    // 아이디 중복 확인
    public Long checkId(String usId) throws Exception;

    // 이메일 중복 확인
    public Long checkEmail(String usEmail) throws Exception;

    // 회원가입
    public User signUp(UserDto userDto) throws Exception;

    // 로그인
    public Map<String, Object> login(UserDto userDto) throws Exception;

}