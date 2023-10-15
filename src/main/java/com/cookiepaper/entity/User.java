package com.cookiepaper.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@Entity
@Data
@Getter @Setter
@ToString
@Table(name="tb_user")
public class User implements UserDetails {

    @Id
    @Column(name = "us_id")
    private String usId;
    @Column(name = "us_password")
    private String usPassword;
    @Column(name = "us_nickname")
    private String usNickname;
    @Column(name = "us_email")
    private String usEmail;
    @ElementCollection(fetch = FetchType.EAGER) // roles 컬렉션
    @Builder.Default
    @Column(name = "us_role")
    private List<String> roles = new ArrayList<>();

    public User() {
    }

    @Override
    public String getPassword() {
        return usPassword;
    }

    @Override
    public String getUsername() {
        return usId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 사용자의 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * 비밀번호 암호화
     * @param passwordEncoder 암호화할 인코더 클래스
     * @return 변경된 유저 Entity
     */
    public User hashPassword(PasswordEncoder passwordEncoder) {
        this.usPassword = passwordEncoder.encode(this.usPassword);
        return this;
    }

    /**
     * 비밀번호 확인
     * @param plainPassword 암호화 이전의 비밀번호
     * @param passwordEncoder 암호화에 사용된 클래스
     * @return true | false
     */
    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.usPassword);
    }

    public User(String usId, String usPassword, String usNickname, String usEmail, List<String> roles) {
        this.usId = usId;
        this.usPassword = usPassword;
        this.usNickname = usNickname;
        this.usEmail = usEmail;
        this.roles = roles;
    }

}
