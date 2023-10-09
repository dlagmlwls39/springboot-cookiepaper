package com.cookiepaper.service;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.entity.User;
import com.cookiepaper.repository.UserRepository;
import com.cookiepaper.token.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 아이디 중복 확인
    @Override
    public Long checkId(String usId) throws Exception {
        Long userCnt = userRepository.countByUsId(usId);
        return userCnt;
    }

    // 이메일 중복 확인
    @Override
    public Long checkEmail(String usEmail) throws Exception {
        Long userCnt = userRepository.countByUsEmail(usEmail);
        return userCnt;
    }

    // 회원가입
    @Override
    @Transactional
    public User signUp(UserDto userDto) throws Exception {
        User newUser = User.builder()
                .usId(userDto.getUsId())
                .usPassword(bCryptPasswordEncoder.encode(userDto.getUsPassword()))
                .usNickname(userDto.getUsNickname())
                .usEmail(userDto.getUsEmail())
                .build();  // 비밀번호 암호화

        System.out.println("newUser pw = " + newUser.getUsPassword());
        return userRepository.save(newUser);
    }

}