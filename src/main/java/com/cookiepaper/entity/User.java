package com.cookiepaper.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@Table(name="tb_user")
public class User {

    @Id
    @Column(name = "us_id")
    private String usId;
    @Column(name = "us_password")
    private String usPassword;
    @Column(name = "us_nickname")
    private String usNickname;
    @Column(name = "us_email")
    private String usEmail;

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

    @Builder
    public User(String usId, String usPassword, String usNickname, String usEmail) {
        this.usId = usId;
        this.usPassword = usPassword;
        this.usNickname = usNickname;
        this.usEmail = usEmail;
    }
}
