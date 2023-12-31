package com.cookiepaper.service;

import com.cookiepaper.dto.UserDto;
import com.cookiepaper.entity.Oven;
import com.cookiepaper.entity.User;
import com.cookiepaper.repository.OvenRepository;
import com.cookiepaper.repository.UserRepository;
import com.cookiepaper.token.JwtTokenProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OvenRepository ovenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, OvenRepository ovenRepository
                           , JwtTokenProvider jwtTokenProvider, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.ovenRepository = ovenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 아이디 존재 여부 확인
    @Override
    public Long checkId(String usId) throws Exception {
        Long userCnt = userRepository.countByUsId(usId);
        return userCnt;
    }

    // 이메일 존재 여부 확인
    @Override
    public Long checkEmail(String usEmail) throws Exception {
        Long userCnt = userRepository.countByUsEmail(usEmail);
        return userCnt;
    }

    // 회원가입
    @Override
    public User signUp(UserDto userDto) throws Exception {
        User newUser = User.builder()
                .usId(userDto.getUsId())
                .usPassword(bCryptPasswordEncoder.encode(userDto.getUsPassword()))  // 비밀번호 암호화
                .usNickname(userDto.getUsNickname())
                .usEmail(userDto.getUsEmail())
                .roles(Collections.singletonList("USER"))  // 역할 부여
                .build();

        System.out.println("newUser pw = " + newUser.getUsPassword());
        return userRepository.save(newUser);
    }

    // 로그인
    @Override
    @Transactional
    public Map<String, Object> login(UserDto userDto) throws Exception {
        User user = userRepository.findById(userDto.getUsId()).get();

        // 아이디가 존재하지 않는 경우
        if (user == null) {
            return null;
        }

        // 비밀번호가 일치하지 않는 경우
        if (!bCryptPasswordEncoder.matches(userDto.getUsPassword(), user.getUsPassword())) {
            //throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            return null;
        }

        // 로그인에 성공하면 id, roles로 토큰 생성 후 유저 정보와 함께 반환
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        ObjectMapper objectMapper = new ObjectMapper();

        userDto = new UserDto(user.getUsId(), user.getUsNickname(), user.getUsEmail());
        Oven oven = ovenRepository.getByUsId(user.getUsId());  // 해당 유저의 오븐 정보

        Map<String, Object> userWithAccessToken = objectMapper.convertValue(userDto, new TypeReference<Map<String, Object>>() {});
        userWithAccessToken.put("token", token);
        userWithAccessToken.put("oven", oven);

        return userWithAccessToken;
    }

    // 아이디, 이메일 일치 여부 확인
    @Override
    public Long checkUser(String usId, String usEmail) {
        Long userCnt = userRepository.countByUsIdAndUsEmail(usId, usEmail);
        return userCnt;
    }

    // 비밀번호 재설정
    @Override
    @Transactional
    public User updatePassword(String usId, String usEmail, String usPassword) throws Exception {
        User user = userRepository.getByUsIdAndUsEmail(usId, usEmail);
        user.setUsPassword(bCryptPasswordEncoder.encode(usPassword));

        System.out.println("user pw = " + user.getUsPassword());
        return userRepository.save(user);
    }

}