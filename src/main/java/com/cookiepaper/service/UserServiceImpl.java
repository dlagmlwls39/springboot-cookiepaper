package com.cookiepaper.service;

import com.cookiepaper.entity.User;
import com.cookiepaper.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 아이디 중복 확인
    @Override
    public Long checkId(String usId) throws Exception {
        Long userCnt = this.userRepository.countByUsId(usId);
        return userCnt;
    }

    // 이메일 중복 확인
    @Override
    public Long checkEmail(String usEmail) throws Exception {
        Long userCnt = this.userRepository.countByUsEmail(usEmail);
        return userCnt;
    }

    // 회원가입
    @Override
    public User signUp(User newUser) throws Exception {
        newUser.hashPassword(bCryptPasswordEncoder);  // 비밀번호 암호화
        System.out.println("newUser pw = " + newUser.getUsPassword());
        return this.userRepository.save(newUser);
    }

}