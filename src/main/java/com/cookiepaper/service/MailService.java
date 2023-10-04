package com.cookiepaper.service;

import javax.mail.internet.MimeMessage;

public interface MailService {

    // 회원가입 인증 메일 생성
    public MimeMessage createMessage(String usEmail) throws Exception;

    // 메일 발송
    public String sendMail(String usEmail) throws Exception;

}
