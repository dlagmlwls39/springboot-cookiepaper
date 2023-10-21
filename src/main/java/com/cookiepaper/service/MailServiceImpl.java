package com.cookiepaper.service;

import com.cookiepaper.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public MailServiceImpl(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    private final String authKey = createKey();  // 인증번호
    private final String username = "${spring.mail.username}";  // 메일 발신자
    private String usId = ""; // 회원 아이디

    // 인증번호 생성
    public static String createKey() {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111); // 범위: 111111 ~ 999999

        return authKey;
    }

    // 메일 생성
    public MimeMessage createMessage(String usEmail, String type) throws Exception {

        MimeMessage message = javaMailSender.createMimeMessage();

        String title = "";

        // 메일 제목
        if (type == "signUp") {
            title = "쿠키페이퍼 회원가입 인증번호";
        } else if (type == "findId") {
            title = "쿠키페이퍼 아이디 찾기";
        } else if (type == "findPassword") {
            title = "쿠키페이퍼 비밀번호 찾기 인증번호";
        }

        message.addRecipients(MimeMessage.RecipientType.TO, usEmail);  // 메일 수신자
        message.setSubject(title);  // 메일 제목

        String msg = "";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">" + title + "</h1>";
        //msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 인증번호를 회원가입 화면에서 입력해 주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += type == "findId" ? usId : authKey;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "UTF-8", "html"); // 내용, charset 타입, subtype
        message.setFrom(new InternetAddress(username, "쿠키페이퍼")); // 발신자 정보

        return message;
    }

    /*
        메일 발송
        usEmail는 수신자 이메일 주소
        MimeMessage 객체 안에 전송할 내용 담기
        bean으로 등록해둔 javaMailSender 객체를 사용하여 메일 발송
     */
    public String sendMail(String usEmail, String type) throws Exception {
        usId = userRepository.getByUsEmail(usEmail).getUsId();
        MimeMessage message = createMessage(usEmail, type);
        try {
            javaMailSender.send(message); // 메일 발송
        } catch (MailException e) {
            e.printStackTrace();
        }
        return type == "findId" ? usId : authKey; // 인증번호 반환
    }

}
